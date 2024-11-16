package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerBC extends ELProperty {
	public HomeAirConditionerBC() {
		super(EPC.xBC, null, "temperatureUserRemoteControl");

	}

	public HomeAirConditionerBC(byte[] edt) {
		super(EPC.xBC, edt, "temperatureUserRemoteControl");
	}

	@Override
	public byte[] edtFromString(String input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName, edt[0]);
			return obj;
		}
	}

}
