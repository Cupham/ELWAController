package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property8F extends ELProperty {
	public Property8F() {
		super(EPC.x8F, null, "powerSaving");

	}

	public Property8F(byte[] edt) {
		super(EPC.x8F, edt, "powerSaving");
	}

	@Override
	public byte[] edtFromString(String input) {

		Boolean b = Boolean.valueOf(input);
		if (b.booleanValue()) {
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
