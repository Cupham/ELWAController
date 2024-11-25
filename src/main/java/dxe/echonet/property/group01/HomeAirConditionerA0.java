package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.AirFlowRateClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerA0 extends ELProperty {
	public HomeAirConditionerA0() {
		super(EPC.xA0, null, "airFlowLevel");

	}

	public HomeAirConditionerA0(byte[] edt) {
		super(EPC.xA0, edt, "airFlowLevel");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		AirFlowRateClass airFlow = new AirFlowRateClass(input, propertyName);
		return new byte[] { airFlow.airFLow };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			AirFlowRateClass airFlow = new AirFlowRateClass(edt[0]);
			obj.put(propertyName,airFlow.toJsonString());
			return obj;
		}
	}

}
