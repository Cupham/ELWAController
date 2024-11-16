package dxe.echonet.property;

import org.json.JSONObject;

import dxe.echonet.object.DateFromByteClass;
import echowand.common.EPC;

public class Property98 extends ELProperty {
	public Property98() {
		super(EPC.x98, null, "currentDate");

	}

	public Property98(byte[] edt) {
		super(EPC.x98, edt, "currentDate");
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
