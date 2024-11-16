package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class AirCleanerE1 extends ELProperty {
	public AirCleanerE1() {
		super(EPC.xE1, null, "filterChangeNotice");

	}

	public AirCleanerE1(byte[] edt) {
		super(EPC.xE1, edt, "filterChangeNotice");
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
			if(edt[0] == (byte) 0x41) {
				obj.put(propertyName,true);
			} else {
				obj.put(propertyName,false);
			}
			return obj;
		}
	}

}
