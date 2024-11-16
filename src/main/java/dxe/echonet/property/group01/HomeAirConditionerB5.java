package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB5 extends ELProperty {
	public HomeAirConditionerB5() {
		super(EPC.xB5, null, "targetTemperatureCooling");

	}

	public HomeAirConditionerB5(byte[] edt) {
		super(EPC.xB5, edt, "targetTemperatureCooling");
	}

	@Override
	public byte[] edtFromString(String input) {
		if (input.equals("undefined")) {
			return new byte[] { (byte) 0xFD };
		} else {
			int number = Integer.parseInt(input);
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
				obj.put(propertyName,edt[0]);
			}
			
			return obj;
		}
	}

}
