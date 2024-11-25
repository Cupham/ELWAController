package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property87 extends ELProperty {
	public Property87() {
		super(EPC.x87, null, "currentLimit");

	}

	public Property87(byte[] edt) {
		super(EPC.x87, edt, "currentLimit");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		int value = input.getInt(propertyName);
		return new byte[] { (byte) value };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,edt[0]& 0xFF);
			return obj;
		}
		
	}

}
