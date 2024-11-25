package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerC2 extends ELProperty {
	public HomeAirConditionerC2() {
		super(EPC.xC2, null, "ventilationAirFlowLevel");

	}

	public HomeAirConditionerC2(byte[] edt) {
		super(EPC.xC2, edt, "ventilationAirFlowLevel");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		byte[] rs;
		if (input.get(propertyName).equals("auto")) {
			rs = new byte[] { 0x41 };
		} else {
			int val = input.getInt(propertyName);
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
