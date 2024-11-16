package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerC4 extends ELProperty {
	public HomeAirConditionerC4() {
		super(EPC.xC4, null, "humidificationLevel");

	}

	public HomeAirConditionerC4(byte[] edt) {
		super(EPC.xC4, edt, "humidificationLevel");
	}

	@Override
	public byte[] edtFromString(String input) {
		byte[] rs;
		if (input.equals("auto")) {
			rs = new byte[] { 0x41 };
		} else {
			int val = Integer.parseInt(input);
			rs = new byte[] { (byte) (0x30 + val) };
		}
		return rs;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			if (edt[0] == 0x41) {
				obj.put(propertyName,"auto");
			} else {
				obj.put(propertyName,edt[0] - (byte) 0x30);
			}
			
			return obj;
		}
		
	}

}
