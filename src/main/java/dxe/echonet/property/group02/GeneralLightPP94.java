package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPP94 extends ELProperty {
	public GeneralLightPP94() {
		super(EPC.x94, null, "offTimerReservation");

	}

	public GeneralLightPP94(byte[] edt) {
		super(EPC.x94, edt, "offTimerReservation");
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
