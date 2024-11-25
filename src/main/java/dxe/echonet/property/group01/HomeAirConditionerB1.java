package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB1 extends ELProperty {
	public HomeAirConditionerB1() {
		super(EPC.xB1, null, "automaticTemperatureControl");

	}

	public HomeAirConditionerB1(byte[] edt) {
		super(EPC.xB1, edt, "automaticTemperatureControl");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		boolean b = input.getBoolean(propertyName);
		if (b) {
			return new byte[] { 0x41 };
		} else {
			return new byte[] { 0x42 };
		}
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			if(edt[0] == (byte) 0x41) {
				obj.put(propertyName,true);
			} else {
				obj.put(propertyName,false);
			}
			return obj;
		}
		
	}

}
