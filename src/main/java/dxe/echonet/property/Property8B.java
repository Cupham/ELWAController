package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property8B extends ELProperty {
	public Property8B() {
		super(EPC.x8B, null, "businessFacilityCode");

	}

	public Property8B(byte[] edt) {
		super(EPC.x8B, edt, "businessFacilityCode");
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
