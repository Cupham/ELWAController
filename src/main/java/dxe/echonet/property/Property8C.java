package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property8C extends ELProperty {
	public Property8C() {
		super(EPC.x8C, null, "productCode");

	}

	public Property8C(byte[] edt) {
		super(EPC.x8C, edt, "productCode");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
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
