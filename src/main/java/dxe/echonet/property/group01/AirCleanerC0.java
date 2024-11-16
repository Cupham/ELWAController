package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class AirCleanerC0 extends ELProperty {
	public AirCleanerC0() {
		super(EPC.xC0, null, "airPollutionDetection");

	}

	public AirCleanerC0(byte[] edt) {
		super(EPC.xC0, edt, "airPollutionDetection");
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