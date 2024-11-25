package dxe.echonet.property.group00;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HumanDetectionSensorB1 extends ELProperty {
	public HumanDetectionSensorB1() {
		super(EPC.xB1, null, "detection");

	}

	public HumanDetectionSensorB1(byte[] edt) {
		super(EPC.xB1, edt, "detection");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		boolean b = input.getBoolean(propertyName);
		if (b) {
			return new byte[] { 0x41 };
		} else {
			return new byte[] { 0x42 };
		}
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
