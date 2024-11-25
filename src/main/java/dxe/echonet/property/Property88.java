package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property88 extends ELProperty {
	public Property88() {
		super(EPC.x88, null, "faultStatus");

	}

	public Property88(byte[] edt) {
		super(EPC.x88, edt, "faultStatus");
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
