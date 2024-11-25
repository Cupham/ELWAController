package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class AirCleanerC1 extends ELProperty {
	public AirCleanerC1() {
		super(EPC.xC1, null, "smokeDetection");

	}

	public AirCleanerC1(byte[] edt) {
		super(EPC.xC1, edt, "smokeDetection");
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
			if(edt[0] == (byte) 0x41) {
				obj.put(propertyName,true);
			} else {
				obj.put(propertyName,false);
			}
			return obj;
		}
		
	}

}
