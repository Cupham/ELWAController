package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB6 extends ELProperty {
	public HomeAirConditionerB6() {
		super(EPC.xB6, null, "targetTemperatureHeating");

	}

	public HomeAirConditionerB6(byte[] edt) {
		super(EPC.xB6, edt, "targetTemperatureHeating");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		if (input.get(propertyName).equals("undefined")) {
			return new byte[] { (byte) 0xFD };
		} else {
			int number = input.getInt(propertyName);
			return new byte[] { (byte) number };
		}
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			if (edt[0] == 0xFD) {
				obj.put(propertyName,"undefined");
			} else {
				obj.put(propertyName,edt[0]& 0xFF);
			}
			
			return obj;
		}
	}

}
