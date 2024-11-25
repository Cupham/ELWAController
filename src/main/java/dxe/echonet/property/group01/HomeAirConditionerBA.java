package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerBA extends ELProperty {
	public HomeAirConditionerBA() {
		super(EPC.xBA, null, "humidity");

	}

	public HomeAirConditionerBA(byte[] edt) {
		super(EPC.xBA, edt, "humidity");
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
			obj.put(propertyName, edt[0]& 0xFF);
			return obj;
		}
	}

}
