package dxe.echonet.property;

import org.json.JSONObject;

import dxe.echonet.object.TimeFromByteClass;
import echowand.common.EPC;

public class Property97 extends ELProperty {
	public Property97() {
		super(EPC.x97, null, "currentTime");

	}

	public Property97(byte[] edt) {
		super(EPC.x97, edt, "currentTime");
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
			TimeFromByteClass time = new TimeFromByteClass(edt);
			obj.put(propertyName, time.toString());
			return obj;
		}

	}

}
