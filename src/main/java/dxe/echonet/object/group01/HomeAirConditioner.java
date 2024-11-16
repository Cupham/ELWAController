package dxe.echonet.object.group01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.SerializationUtils;
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
import dxe.echonet.property.group01.HomeAirConditioner90;
import dxe.echonet.property.group01.HomeAirConditioner91;
import dxe.echonet.property.group01.HomeAirConditioner94;
import dxe.echonet.property.group01.HomeAirConditioner95;
import dxe.echonet.property.group01.HomeAirConditioner96;
import dxe.echonet.property.group01.HomeAirConditionerA0;
import dxe.echonet.property.group01.HomeAirConditionerA1;
import dxe.echonet.property.group01.HomeAirConditionerA3;
import dxe.echonet.property.group01.HomeAirConditionerA4;
import dxe.echonet.property.group01.HomeAirConditionerA5;
import dxe.echonet.property.group01.HomeAirConditionerAA;
import dxe.echonet.property.group01.HomeAirConditionerAB;
import dxe.echonet.property.group01.HomeAirConditionerB0;
import dxe.echonet.property.group01.HomeAirConditionerB1;
import dxe.echonet.property.group01.HomeAirConditionerB2;
import dxe.echonet.property.group01.HomeAirConditionerB3;
import dxe.echonet.property.group01.HomeAirConditionerB4;
import dxe.echonet.property.group01.HomeAirConditionerB5;
import dxe.echonet.property.group01.HomeAirConditionerB6;
import dxe.echonet.property.group01.HomeAirConditionerB7;
import dxe.echonet.property.group01.HomeAirConditionerB9;
import dxe.echonet.property.group01.HomeAirConditionerBA;
import dxe.echonet.property.group01.HomeAirConditionerBB;
import dxe.echonet.property.group01.HomeAirConditionerBC;
import dxe.echonet.property.group01.HomeAirConditionerBD;
import dxe.echonet.property.group01.HomeAirConditionerBE;
import dxe.echonet.property.group01.HomeAirConditionerBF;
import dxe.echonet.property.group01.HomeAirConditionerC0;
import dxe.echonet.property.group01.HomeAirConditionerC1;
import dxe.echonet.property.group01.HomeAirConditionerC2;
import dxe.echonet.property.group01.HomeAirConditionerC4;
import dxe.echonet.property.group01.HomeAirConditionerC6;
import dxe.echonet.property.group01.HomeAirConditionerC8;
import dxe.echonet.property.group01.HomeAirConditionerCA;
import dxe.echonet.property.group01.HomeAirConditionerCC;
import dxe.echonet.property.group01.HomeAirConditionerCE;
import dxe.echonet.property.group01.HomeAirConditionerCF;
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

public class HomeAirConditioner extends ECHONETObject {
	@JsonIgnore
	private static final Logger logger = Logger.getLogger(HomeAirConditioner.class.getName());

	public HomeAirConditioner(Node node, EOJ eoj, Service s) {
		super(node, eoj, s);
		String topic = String.format("%s/register", Controller.publishTopic);
		String payload = new JSONObject().put("deviceType", "homeAirConditioner").put("id", getDeviceID()).toString();
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
								MqttMessage msg = new MqttMessage(SerializationUtils.serialize((Serializable) pp.edtToStringValue()));
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
						return;
					}
					ELProperty pp = null;
					switch (resultData.getEPC()) {

					case x80:
						c += 1;
						pp = new Property80(resultData.toBytes());
						getProperties().put(EPC.x80, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x80, EDT: 0x%02X}=={OperationStatus:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));

						break;
					case x81:
						c += 1;
						pp = new Property81(resultData.toBytes());
						getProperties().put(EPC.x81, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x81, EDT: 0x%02X}=={InstallationLocation:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x82:
						c += 1;
						pp = new Property82(resultData.toBytes());
						getProperties().put(EPC.x82, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x82, EDT: 0x%02X}=={Version Information:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x83:
						c += 1;
						pp = new Property83(resultData.toBytes());
						getProperties().put(EPC.x83, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x83, EDT: 0x%02X}=={Identification Number:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x84:
						c += 1;
						pp = new Property84(resultData.toBytes());
						getProperties().put(EPC.x84, pp);
						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:0x84, EDT: 0x%02X}=={Instantaneous Power Consumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x85:
						c += 1;
						pp = new Property85(resultData.toBytes());
						getProperties().put(EPC.x85, pp);
						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:0x85, EDT: 0x%02X}=={Cumulative Power Consumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x86:
						c += 1;
						pp = new Property86(resultData.toBytes());
						getProperties().put(EPC.x86, pp);
						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:0x86, EDT: 0x%02X}=={Manufacturer Fault Code:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(),
										resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case x87:
						c += 1;
						pp = new Property87(resultData.toBytes());
						getProperties().put(EPC.x87, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x87, EDT: 0x%02X}=={Current Limit Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x88:
						c += 1;
						pp = new Property88(resultData.toBytes());
						getProperties().put(EPC.x88, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x88, EDT: 0x%02X}=={Fault Status:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x89:

						c += 1;
						pp = new Property89(resultData.toBytes());
						getProperties().put(EPC.x89, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x89, EDT: 0x%02X}=={Fault Description:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8A:
						c += 1;

						pp = new Property8A(resultData.toBytes());
						getProperties().put(EPC.x8A, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8A, EDT: 0x%02X}=={Manufacturer Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8B:
						c += 1;
						pp = new Property8B(resultData.toBytes());
						getProperties().put(EPC.x8B, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8B, EDT: 0x%02X}=={Business Facility Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8C:
						c += 1;
						pp = new Property8C(resultData.toBytes());
						getProperties().put(EPC.x8C, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8C, EDT: 0x%02X}=={Product Code:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8D:
						c += 1;
						pp = new Property8D(resultData.toBytes());
						getProperties().put(EPC.x8D, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8D, EDT: 0x%02X}=={Product Number:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8E:
						c += 1;
						pp = new Property8E(resultData.toBytes());
						getProperties().put(EPC.x8E, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8E, EDT: 0x%02X}=={Production Date:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x8F:
						c += 1;
						pp = new Property8F(resultData.toBytes());
						getProperties().put(EPC.x8F, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x8F, EDT: 0x%02X}=={PowerSaving Mode:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x97:
						c += 1;
						pp = new Property97(resultData.toBytes());
						getProperties().put(EPC.x97, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x97, EDT: 0x%02X}=={Current Date Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x98:
						c += 1;
						pp = new Property98(resultData.toBytes());
						getProperties().put(EPC.x98, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x98, EDT: 0x%02X}=={Current Date Setting:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x99:
						c += 1;
						pp = new Property99(resultData.toBytes());
						getProperties().put(EPC.x99, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x99, EDT: 0x%02X}=={Power Limit:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x9A:
						c += 1;
						pp = new Property9A(resultData.toBytes());
						getProperties().put(EPC.x9A, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x9A, EDT: 0x%02X}=={Up Time:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;

					case x90:
						c += 1;
						pp = new HomeAirConditioner90(resultData.toBytes());
						getProperties().put(EPC.x90, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x90, EDT: 0x%02X}=={OnTimerReservation :%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x91:
						c += 1;
						pp = new HomeAirConditioner91(resultData.toBytes());
						getProperties().put(EPC.x91, pp);
						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x91, EDT: 0x%02X}=={onTimerTime:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								resultData.toBytes()[1], pp.edtToStringValue()));
						break;
					case x94:
						c += 1;
						pp = new HomeAirConditioner94(resultData.toBytes());
						getProperties().put(EPC.x94, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x94, EDT: 0x%02X}=={OffTimerReservation :%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								pp.edtToStringValue()));
						break;
					case x95:
						c += 1;
						pp = new HomeAirConditioner95(resultData.toBytes());
						getProperties().put(EPC.x95, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:0x91, EDT: 0x%02X}=={TimeOfOffTimer:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.toBytes()[0],
								resultData.toBytes()[1], pp.edtToStringValue()));
						break;
					case x96:
						c += 1;
						pp = new HomeAirConditioner96(resultData.toBytes());
						getProperties().put(EPC.x96, pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={relativeTimeOfOffTimer:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;

					case xA0:
						c += 1;
						pp = new HomeAirConditionerA0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airFlowLevel:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xA1:
						c += 1;
						pp = new HomeAirConditionerA1(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={automaticControlAirFlowDirection:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xA3:
						c += 1;
						pp = new HomeAirConditionerA0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={automaticSwingAirFlow:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xA4:
						c += 1;
						pp = new HomeAirConditionerA4(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airFlowDirectionVertical:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xA5:
						c += 1;
						pp = new HomeAirConditionerA5(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airFlowDirectionHorizontal:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
										resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xAA:
						c += 1;
						pp = new HomeAirConditionerAA(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={specialState:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xAB:
						c += 1;
						pp = new HomeAirConditionerAB(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={nonPriorityState:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB0:
						c += 1;
						pp = new HomeAirConditionerB0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={operationMode:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB1:
						c += 1;
						pp = new HomeAirConditionerB1(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={automaticTemperatureControl:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
										resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB2:
						c += 1;
						pp = new HomeAirConditionerB2(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={highspeedOperation:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB3:
						c += 1;
						pp = new HomeAirConditionerB3(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={targetTemperature:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB4:
						c += 1;
						pp = new HomeAirConditionerB4(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={relativeHumidityDehumidifying:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB5:
						c += 1;
						pp = new HomeAirConditionerB5(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={targetTemperatureCooling:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB6:
						c += 1;
						pp = new HomeAirConditionerB6(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={targetTemperatureHeating:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB7:
						c += 1;
						pp = new HomeAirConditionerB7(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format(
								"Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={targetTemperatureDehumidifying:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB8:
						c += 1;
						pp = new HomeAirConditionerB0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={ratedPowerConsumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xB9:
						c += 1;
						pp = new HomeAirConditionerB9(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={currentConsumption:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBA:
						c += 1;
						pp = new HomeAirConditionerBA(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={humidity:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBB:
						c += 1;
						pp = new HomeAirConditionerBB(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={roomTemperature:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBC:
						c += 1;
						pp = new HomeAirConditionerBC(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={temperatureUserRemoteControl:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
										resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBD:
						c += 1;
						pp = new HomeAirConditionerBD(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airFlowTemperature:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBE:
						c += 1;
						pp = new HomeAirConditionerBE(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={outdoorTemperature:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xBF:
						c += 1;
						pp = new HomeAirConditionerBF(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={relativeTemperature:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC0:
						c += 1;
						pp = new HomeAirConditionerC0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={ventilationFunction:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC1:
						c += 1;
						pp = new HomeAirConditionerC1(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={humidifierFunction:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC2:
						c += 1;
						pp = new HomeAirConditionerC2(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={ventilationAirFlowLevel:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC4:
						c += 1;
						pp = new HomeAirConditionerC4(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={humidificationLevel:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC6:
						c += 1;
						pp = new HomeAirConditionerC6(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airCleaningMethod:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xC8:
						c += 1;
						pp = new HomeAirConditionerC8(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airRefreshMethod:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xCA:
						c += 1;
						pp = new HomeAirConditionerCA(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={selfCleaningMethod:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xCC:
						c += 1;
						pp = new HomeAirConditionerCC(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={specialFunction:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xCD:
						c += 1;
						pp = new HomeAirConditionerC0(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(
								String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={componentsOperationStatus:%s}",
										getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
										resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xCE:
						c += 1;
						pp = new HomeAirConditionerCE(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={thermostatOverride:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
						break;
					case xCF:
						c += 1;
						pp = new HomeAirConditionerCF(resultData.toBytes());
						getProperties().put(resultData.getEPC(), pp);

						logger.info(String.format("Node:%s@EOJ:%s {EPC:%s, EDT: 0x%02X}=={airPurification:%s}",
								getNode().getNodeInfo().toString(), getEoj().toString(), resultData.getEPC(),
								resultData.toBytes()[0], pp.edtToStringValue()));
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
			case x90:
				pp = new HomeAirConditioner90();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x91:
				pp = new HomeAirConditioner91();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case x94:
				pp = new HomeAirConditioner94();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xA0:
				pp = new HomeAirConditionerA0();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xA1:
				pp = new HomeAirConditionerA1();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xA3:
				pp = new HomeAirConditionerA3();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xA4:
				pp = new HomeAirConditionerA4();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xA5:
				pp = new HomeAirConditionerA5();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB0:
				pp = new HomeAirConditionerB0();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB1:
				pp = new HomeAirConditionerB1();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB2:
				pp = new HomeAirConditionerB2();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB3:
				pp = new HomeAirConditionerB3();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB4:
				pp = new HomeAirConditionerB4();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB5:
				pp = new HomeAirConditionerB5();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB6:
				pp = new HomeAirConditionerB6();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xB7:
				pp = new HomeAirConditionerB7();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xBF:
				pp = new HomeAirConditionerB7();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xC0:
				pp = new HomeAirConditionerC0();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xC1:
				pp = new HomeAirConditionerC1();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xC2:
				pp = new HomeAirConditionerC2();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xC4:
				pp = new HomeAirConditionerC4();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xCC:
				pp = new HomeAirConditionerCC();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xCE:
				pp = new HomeAirConditionerC0();
				getSupportCommands().put(pp.propertyName, pp);
				break;
			case xCF:
				pp = new HomeAirConditionerC0();
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
							String topic = String.format("%s/%s/properties/%", Controller.publishTopic, getDeviceID(),
									pp.propertyName);
							MqttMessage msg = new MqttMessage(SerializationUtils.serialize((Serializable) pp.edtToStringValue()));
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
