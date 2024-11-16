package dxe;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import dxe.echonet.object.ECHONETObject;
import dxe.echonet.property.ELProperty;
import echowand.common.Data;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.TooManyObjectsException;
import echowand.monitor.Monitor;
import echowand.monitor.MonitorListener;
import echowand.net.Inet4Subnet;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Core;
import echowand.service.Service;
import echowand.service.result.SetResult;
import echowand.util.Pair;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import utils.echonet.EOJ2DeviceObject;

@JsonInclude(Include.NON_NULL)
public class Controller {
	public static Core echonetCore;
	public static Service echonetService;
	public static ArrayList<ECHONETObject> devices;
	public static MqttClient mqttClient;
	public static String publishTopic = "/server/11223344";
	public static String subscribeTopic = "/client/11223344";

	public static boolean networkMonitor(String networkInterface) {
		boolean isSuccessed = false;
		if (echonetService == null) {

			NetworkInterface nif = null;
			try {
				nif = NetworkInterface.getByName(networkInterface);
			} catch (SocketException e1) {
				System.out.println(e1.toString());
			}
			try {
				// echonetCore = new Core(Inet4Subnet.startSubnet(nif));
				echonetCore = new Core(new Inet4Subnet());
			} catch (SubnetException e1) {
				System.out.println(e1.toString());
			}
			try {
				echonetCore.startService();
			} catch (TooManyObjectsException e1) {
				System.out.println(e1.toString());
			} catch (SubnetException e1) {
				System.out.println(e1.toString());
			}
			echonetService = new Service(echonetCore);
			Monitor monitor = new Monitor(echonetCore);
			monitor.addMonitorListener(new MonitorListener() {
				@Override
				public void detectEOJsJoined(Monitor monitor, Node node, List<EOJ> eojs) {

					System.out.println("INFO: initialEchonetInterface: detectEOJsJoined: " + node + " " + eojs);

					for (EOJ eoj : eojs) {
						if (eoj.isProfileObject()) {

						} else if (eoj.isDeviceObject()) {
							ECHONETObject obj = EOJ2DeviceObject.dataObjectFromCode(node, eoj, echonetService);
							if (obj != null) {
								obj.initDevice();
								obj.pollData();
								devices.add(obj);
							}

						}
					}

				}

				@Override
				public void detectEOJsExpired(Monitor monitor, Node node, List<EOJ> eojs) {

					System.out.println("INFO:initialEchonetInterface: detectEOJsExpired: " + node + " " + eojs);
				}
			});
			try {
				monitor.start();
			} catch (SubnetException e) {
				System.out.println("ERROR : CAN NOT START MONITORING INTERFACE!!!!!! " + e.toString());
				e.printStackTrace();
			}
			isSuccessed = true;
		}

		if (isSuccessed) {
			System.out.println("Initilized ECHONET API successfully!");
		}
		return isSuccessed;
	}

	// public void
	public static void main(String[] args) {
		devices = new ArrayList<ECHONETObject>();
		String networkInterface = "";
		String broker = "";
		echonetService = null;

		ArgumentParser parser = ArgumentParsers.newFor("EchonetNetworkMonitor").build().defaultHelp(true).description(
				"Monitoring ECHONET Lite network using network interface, -i to specific network interface name");
		parser.addArgument("-i", "--interface").help("network interface name");
		parser.addArgument("-b", "--broker").help("address of the broker");
		Namespace ns = null;
		try {
			ns = parser.parseArgs(args);
		} catch (ArgumentParserException e) {
			parser.handleError(e);
			System.out.println("can not parse input interface!!! " + e.toString());
			System.exit(1);
		}
		networkInterface = ns.getString("interface");
		broker = ns.getString("broker");

		// start main program
		if (!"".equals(networkInterface)) {
			System.out.println(
					String.format("Start to monitor ECHONET Lite network from the %s interface", networkInterface));
			networkMonitor(networkInterface);
		} else {
			System.out.println("Do nothing due to no network interface has not been choosen");
		}

		startMQTT(broker);

	}

	public static void startMQTT(String broker) {
		final int qos = 0;
		final String clientId = "Subscribe";

		try {
			mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mqttClient.setCallback(new MqttCallback() {

			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				System.out.println("Message " + new String(message.getPayload()).toString() + " topic " + topic);
				if (topic.contains(("/properties/"))) {
					handleSetRequest(topic, new String(message.getPayload()).toString());
				}

				if (topic.equals((String.format("%s/devices", Controller.subscribeTopic).toString()))) {
					for (ECHONETObject obj : devices) {
						System.out.println(obj.getDeviceID());
					}
				}

			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub

			}

			@Override
			public void connectionLost(Throwable cause) {
				// TODO Auto-generated method stub

			}
		});
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);

		System.out.println("Connecting to broker:" + broker);
		try {
			mqttClient.connect(connOpts);
			mqttClient.subscribe(String.format("%s/cmdreport", Controller.subscribeTopic), qos);
			mqttClient.subscribe(String.format("%s/devices", Controller.subscribeTopic), qos);
		} catch (MqttSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void handleSetRequest(String topic, String payload) {
		JSONObject json = new JSONObject();
		String deviceID = topic.split("/")[3].trim();
		String propertyName = topic.split("/")[5].trim();
		for (ECHONETObject dev : devices) {
			if (dev.getDeviceID().equals(deviceID)) {
				ELProperty pp = dev.getSupportCommands().get(propertyName);
				if (pp != null) {
					boolean rs = executeCommand(dev.getNode(), dev.getEoj(), pp.epc,
							new Data(pp.edtFromString(payload)));
					if (rs) {
						json.put("topic", topic);
						json.put("status", 200);

					} else {
						json.put("topic", topic);
						json.put("status", 500);
					}
					try {
						mqttClient.publish(String.format("%s/%s", publishTopic, "cmdreport"),
								new MqttMessage(json.toString().getBytes()));
					} catch (MqttPersistenceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public static boolean executeCommand(Node node, EOJ eoj, EPC epc, Data data) {
		boolean rs = false;
		SetResult result;
		try {
			result = echonetService.doSet(node, eoj, epc, data, 1000);
			result.join();
			rs = result.isDone();
		} catch (SubnetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
	}

	public static boolean executeCommand(Node node, EOJ eoj, List<Pair<EPC, Data>> epcs) {
		boolean rs = false;

		SetResult result;
		try {
			result = echonetService.doSet(node, eoj, epcs, 1000);
			result.join();
			rs = result.isDone();
		} catch (SubnetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
	}

}
