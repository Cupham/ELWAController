package dxe.echonet.object.group00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dxe.Controller;
import dxe.echonet.object.ECHONETObject;
import dxe.echonet.property.ELProperty;
import dxe.echonet.property.Property80;
import dxe.echonet.property.Property81;
import dxe.echonet.property.Property82;
import dxe.echonet.property.Property83;
import dxe.echonet.property.Property84;
import dxe.echonet.property.Property85;
import dxe.echonet.property.Property86;
import dxe.echonet.property.Property87;
import dxe.echonet.property.Property88;
import dxe.echonet.property.Property89;
import dxe.echonet.property.Property8A;
import dxe.echonet.property.Property8B;
import dxe.echonet.property.Property8C;
import dxe.echonet.property.Property8D;
import dxe.echonet.property.Property8E;
import dxe.echonet.property.Property8F;
import dxe.echonet.property.Property97;
import dxe.echonet.property.Property98;
import dxe.echonet.property.Property99;
import dxe.echonet.property.Property9A;
import dxe.echonet.property.group00.HumanDetectionSensorB0;
import dxe.echonet.property.group00.HumanDetectionSensorB1;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.net.SubnetException;
import echowand.service.Service;
import echowand.service.result.GetListener;
import echowand.service.result.GetResult;
import echowand.service.result.ObserveListener;
import echowand.service.result.ObserveResult;
import echowand.service.result.ResultData;
import echowand.service.result.ResultFrame;
import utils.echonet.SampleConstants;
import utils.echonet.eConverter;

public class HumanDetectionSensor extends ECHONETObject {
	@JsonIgnore
	private static final Logger logger = Logger.getLogger(HumanDetectionSensor.class.getName());

	public HumanDetectionSensor(Node node, EOJ eoj, Service s) {
		super(node, eoj, s);
		String topic = String.format("%s/register", Controller.publishTopic);
		String payload = new JSONObject().put("deviceType", "humanDetectionSensor").put("id", getDeviceID()).toString();
		MqttMessage msg = new MqttMessage(payload.getBytes());
		try {
			Controller.mqttClient.publish(topic, msg);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void pollData() {
		timer = new Timer(true);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (getPollingEPCList().size() > 0) {
					polling();
				}
			}
		}, SampleConstants.getDelayInterval(), SampleConstants.getRefreshInterval());

	}

	private void polling() {
		try {
			this.service.doGet(this.getNode(), this.getEoj(), this.getPollingEPCList(), 5000, new GetListener() {

				@Override
				public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						return;
					}
					ELProperty pp = getProperties().get(resultData.getEPC());
					if (pp != null) {
						if (!Arrays.equals(pp.edt, resultData.toBytes())) {
							pp.edt = resultData.toBytes();
							try {
								String topic = String.format("%s/%s/properties/%s", Controller.publishTopic,
										getDeviceID(), pp.propertyName);
								MqttMessage msg = new MqttMessage(pp.edtToStringValue().get(pp.propertyName).toString().getBytes());
								Controller.mqttClient.publish(topic, msg);
							} catch (MqttPersistenceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MqttException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							logger.info(String.format("Property Changed Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={%s:%s}",
									getNode().getNodeInfo().toString(), getEoj().toString(), pp.epc.toString(),
									resultData.toBytes()[0], pp.propertyName, pp.edtToStringValue()));
						}
					}
				}
			});
		} catch (SubnetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getGettableProperties() {
		
		try {
			this.service.doGet(this.getNode(), this.getEoj(), this.getGettableEPCList(), 5000, new GetListener() {
				int c = 0;

				@Override
				public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						System.out.println("\n\n\nNothing\n\n\n\n");
						return;
					}
					switch (resultData.getEPC()) {
					case x80:
						c += 1;
						ELProperty pp80 = new Property80(resultData.toBytes());
						getProperties().put(EPC.x80, pp80);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp80.edtToStringValue()));

						break;
					case x81:
						c += 1;
						ELProperty pp81 = new Property81(resultData.toBytes());
						getProperties().put(EPC.x81, pp81);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x81, EDT: 0x%02X}=={InstallationLocation:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp81.edtToStringValue()));
						break;
					case x82:
						c += 1;
						ELProperty pp82 = new Property82(resultData.toBytes());
						getProperties().put(EPC.x82, pp82);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x82, EDT: 0x%02X}=={Version Information:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp82.edtToStringValue()));
						break;
					case x83:
						c += 1;
						ELProperty pp83 = new Property83(resultData.toBytes());
						getProperties().put(EPC.x83, pp83);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x83, EDT: 0x%02X}=={Identification Number:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp83.edtToStringValue()));
						break;
					case x84:
						c += 1;
						ELProperty pp84 = new Property84(resultData.toBytes());
						getProperties().put(EPC.x84, pp84);
						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:0x84, EDT: 0x%02X}=={Instantaneous Power Consumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp84.edtToStringValue()));
						break;
					case x85:
						c += 1;
						ELProperty pp85 = new Property85(resultData.toBytes());
						getProperties().put(EPC.x85, pp85);
						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:0x85, EDT: 0x%02X}=={Cumulative Power Consumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp85.edtToStringValue()));
						break;
					case x86:
						c += 1;
						ELProperty pp86 = new Property86(resultData.toBytes());
						getProperties().put(EPC.x86, pp86);
						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:0x86, EDT: 0x%02X}=={Manufacturer Fault Code:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(),
										resultData.toBytes()[0], pp86.edtToStringValue()));
						break;
					case x87:
						c += 1;
						ELProperty pp87 = new Property87(resultData.toBytes());
						getProperties().put(EPC.x87, pp87);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x87, EDT: 0x%02X}=={Current Limit Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp87.edtToStringValue()));
						break;
					case x88:
						c += 1;
						ELProperty pp88 = new Property88(resultData.toBytes());
						getProperties().put(EPC.x88, pp88);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x88, EDT: 0x%02X}=={Fault Status:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp88.edtToStringValue()));
						break;
					case x89:

						c += 1;
						ELProperty pp89 = new Property89(resultData.toBytes());
						getProperties().put(EPC.x89, pp89);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x89, EDT: 0x%02X}=={Fault Description:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp89.edtToStringValue()));
						break;
					case x8A:
						c += 1;

						ELProperty pp8a = new Property8A(resultData.toBytes());
						getProperties().put(EPC.x8A, pp8a);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8A, EDT: 0x%02X}=={Manufacturer Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8a.edtToStringValue()));
						break;
					case x8B:
						c += 1;
						ELProperty pp8b = new Property8B(resultData.toBytes());
						getProperties().put(EPC.x8B, pp8b);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8B, EDT: 0x%02X}=={Business Facility Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8b.edtToStringValue()));
						break;
					case x8C:
						c += 1;
						ELProperty pp8c = new Property8C(resultData.toBytes());
						getProperties().put(EPC.x8C, pp8c);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8C, EDT: 0x%02X}=={Product Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8c.edtToStringValue()));
						break;
					case x8D:
						c += 1;
						ELProperty pp8d = new Property8D(resultData.toBytes());
						getProperties().put(EPC.x8D, pp8d);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8D, EDT: 0x%02X}=={Product Number:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8d.edtToStringValue()));
						break;
					case x8E:
						c += 1;
						ELProperty pp8e = new Property8E(resultData.toBytes());
						getProperties().put(EPC.x8E, pp8e);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8E, EDT: 0x%02X}=={Production Date:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8e.edtToStringValue()));
						break;
					case x8F:
						c += 1;
						ELProperty pp8f = new Property8F(resultData.toBytes());
						getProperties().put(EPC.x8F, pp8f);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8F, EDT: 0x%02X}=={PowerSaving Mode:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp8f.edtToStringValue()));
						break;
					case x97:
						c += 1;
						ELProperty pp97 = new Property97(resultData.toBytes());
						getProperties().put(EPC.x97, pp97);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x97, EDT: 0x%02X}=={Current Date Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp97.edtToStringValue()));
						break;
					case x98:
						c += 1;
						ELProperty pp98 = new Property98(resultData.toBytes());
						getProperties().put(EPC.x98, pp98);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x98, EDT: 0x%02X}=={Current Date Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp98.edtToStringValue()));
						break;
					case x99:
						c += 1;
						ELProperty pp99 = new Property99(resultData.toBytes());
						getProperties().put(EPC.x99, pp99);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x99, EDT: 0x%02X}=={Power Limit:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp99.edtToStringValue()));
						break;
					case x9A:
						c += 1;
						ELProperty pp9a = new Property9A(resultData.toBytes());
						getProperties().put(EPC.x9A, pp9a);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x9A, EDT: 0x%02X}=={Up Time:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp9a.edtToStringValue()));
						break;

					case xB0:
						c += 1;
						ELProperty ppb0 = new HumanDetectionSensorB0(resultData.toBytes());
						getProperties().put(EPC.xB0, ppb0);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0xB0, EDT: 0x%02X}=={HumanDetection Level:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								ppb0.edtToStringValue()));
						break;
					case xB1:
						c += 1;
						ELProperty ppb1 = new HumanDetectionSensorB1(resultData.toBytes());
						getProperties().put(EPC.xB1, ppb1);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0xB1, EDT: 0x%02X}=={detection: %s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								ppb1.edtToStringValue()));
						break;

					default:
						break;
					}
					if (c == getGettableEPCList().size()) {
						try {
							String tosend = toJsonString();
							String topic = String.format("%s/%s/properties", Controller.publishTopic, getDeviceID());
							MqttMessage msg = new MqttMessage(tosend.getBytes());
							Controller.mqttClient.publish(topic, msg);
						} catch (MqttPersistenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MqttException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			});
		} catch (SubnetException ex) {
			logger.log(Level.SEVERE, ex.toString());
		}
	}

	private void getSupportedCommands() {

		for (EPC epc : getSettableEPCList()) {
			ELProperty pp = null;
			switch (epc) {
			case x80:
				pp = new Property80();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x81:
				pp = new Property81();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x87:
				pp = new Property87();
				getSupportCommands().put(pp.propertyName, pp);
				break;

			case x8F:
				pp = new Property8F();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x98:
				pp = new Property98();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x99:
				pp = new Property99();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB0:
				pp = new HumanDetectionSensorB0();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			default:
				break;
			}
			if (pp != null) {
				try {
					Controller.mqttClient.subscribe(String.format("%s/%s/properties/%s", Controller.subscribeTopic,
							this.getDeviceID(), pp.propertyName).toString());
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void observeData() {

		try {
			service.doObserve(getNode(), getEoj(), getObservableEPCList(), new ObserveListener() {
				@Override
				public void receive(ObserveResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {

						return;
					}
					ELProperty pp = getProperties().get(resultData.getEPC());
					if (pp != null) {
						pp.edt = resultData.toBytes();
						try {
							String topic = String.format("%s/%s/properties/%s", Controller.publishTopic, getDeviceID(),
									pp.propertyName);
							MqttMessage msg = new MqttMessage(pp.edtToStringValue().get(pp.propertyName).toString().getBytes());
							Controller.mqttClient.publish(topic, msg);
						} catch (MqttPersistenceException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MqttException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.info(String.format("Property Changed Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={%s:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), pp.epc.toString(),
								resultData.toBytes()[0], pp.propertyName, pp.edtToStringValue()));
					}

				}
			});
		} catch (SubnetException ex) {
			logger.log(Level.SEVERE, ex.toString());

		}

	}

	@Override
	public void initDevice() {

		ArrayList<EPC> epcs = new ArrayList<EPC>();
		// get property map
		epcs.add(EPC.x9F);
		// set property map
		epcs.add(EPC.x9E);
		// inf property map
		epcs.add(EPC.x9D);
		try {
			this.service.doGet(this.getNode(), this.getEoj(), epcs, 5000, new GetListener() {
				@Override
				public void receive(GetResult result, ResultFrame resultFrame, ResultData resultData) {
					if (resultData.isEmpty()) {
						return;
					}
					switch (resultData.getEPC()) {
					case x9F:
						setGettableEPCList(eConverter.getEPCListFromByte(resultData.getActualData().toString(), false));
						getGettableProperties();
						break;
					case x9E:
						setSettableEPCList(eConverter.getEPCListFromByte(resultData.getActualData().toString(), false));
						getSupportedCommands();
						break;
					case x9D:
						setObservableEPCList(
								eConverter.getEPCListFromByte(resultData.getActualData().toString(), false));
						observeData();
						break;
					default:
						break;

					}
					if (getGettableEPCList().size() > 0 && getSettableEPCList().size() > 0
							&& getObservableEPCList().size() > 0) {
						@SuppressWarnings("unchecked")
						ArrayList<EPC> pollingEPCList = (ArrayList<EPC>) getGettableEPCList().clone();
						for (EPC e : getNotPollingEPCList()) {
							pollingEPCList.remove(e);
						}
						setPollingEPCList(pollingEPCList);
					}
				}

			});

		} catch (SubnetException ex) {
			logger.log(Level.SEVERE, ex.toString());
		}
	}

}
