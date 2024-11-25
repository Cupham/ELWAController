package dxe.echonet.property.group00;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HumanDetectionSensorB0 extends ELProperty {
	public HumanDetectionSensorB0() {
		super(EPC.xB0, null, "thresholdLevel");

	}

	public HumanDetectionSensorB0(byte[] edt) {
		super(EPC.xB0, edt, "thresholdLevel");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		int value = input.getInt(propertyName);
		return new byte[] { (byte) value};
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,edt[0] - (byte) 0x30);
			return obj;
		}
	}

}
