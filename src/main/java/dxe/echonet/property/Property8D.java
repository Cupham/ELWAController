package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property8D extends ELProperty {
	public Property8D() {
		super(EPC.x8D, null, "serialNumber");

	}

	public Property8D(byte[] edt) {
		super(EPC.x8D, edt, "serialNumber");
	}

	@Override
	public byte[] edtFromString(String input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			
			obj.put(propertyName, new String(edt));
			return obj;
		}

	}

}
