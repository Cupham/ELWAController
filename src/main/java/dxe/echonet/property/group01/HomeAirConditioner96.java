package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditioner96 extends ELProperty {
	public HomeAirConditioner96() {
		super(EPC.x96, null, "relativeTimeOfOffTimer");

	}

	public HomeAirConditioner96(byte[] edt) {
		super(EPC.x96, edt, "relativeTimeOfOffTimer");
	}

	@Override
	public byte[] edtFromString(String input) {
		int totalTime = Integer.parseInt(input);
		int hour = totalTime / 60;
		int minute = totalTime % 60;
		return new byte[] { (byte) hour, (byte) minute };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			
			obj.put(propertyName,edt[0] * 60 + edt[1]);
			return obj;
		}
	}

}
