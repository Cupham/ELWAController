package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPP90 extends ELProperty {
	public GeneralLightPP90() {
		super(EPC.x90, null, "onTimerReservation");

	}

	public GeneralLightPP90(byte[] edt) {
		super(EPC.x90, edt, "onTimerReservation");
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
