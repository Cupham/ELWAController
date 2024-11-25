package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.AirCleaningMethodClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerC6 extends ELProperty {
	public HomeAirConditionerC6() {
		super(EPC.xC6, null, "airCleaningMethod");

	}

	public HomeAirConditionerC6(byte[] edt) {
		super(EPC.xC6, edt, "airCleaningMethod");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			AirCleaningMethodClass air = new AirCleaningMethodClass(edt[0]);
			obj.put(propertyName,air.toJsonString());
			return obj;
		}

	}

}
