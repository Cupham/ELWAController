package dxe.echonet.property;

import org.json.JSONObject;

import dxe.echonet.object.DateFromByteClass;
import echowand.common.EPC;

public class Property8E extends ELProperty {
	public Property8E() {
		super(EPC.x8E, null, "productionDate");

	}

	public Property8E(byte[] edt) {
		super(EPC.x8E, edt, "productionDate");
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
			DateFromByteClass date = new DateFromByteClass(edt);
			obj.put(propertyName, date.toString());
			return obj;
		}
	}

}
