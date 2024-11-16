package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB4 extends ELProperty {
	public HomeAirConditionerB4() {
		super(EPC.xB4, null, "relativeHumidityDehumidifying");

	}

	public HomeAirConditionerB4(byte[] edt) {
		super(EPC.xB4, edt, "relativeHumidityDehumidifying");
	}

	@Override
	public byte[] edtFromString(String input) {
		int number = Integer.parseInt(input);
		return new byte[] { (byte) number };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,edt[0]);
			return obj;
		}
	}

}
