package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;
import utils.echonet.eConverter;

public class Property9A extends ELProperty {
	public Property9A() {
		super(EPC.x9A, null, "hourMeter");

	}

	public Property9A(byte[] edt) {
		super(EPC.x9A, edt, "hourMeter");
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
			obj.put(propertyName, byteToCummalativeTime());
			return obj;
		}
	}

	private String byteToCummalativeTime() {
		byte[] timeArray = edt;
		String unit = "";
		switch (timeArray[0]) {
		case (byte) 0x41:
			unit = "seconds";
			break;
		case (byte) 0x42:
			unit = "months";
			break;
		case (byte) 0x43:
			unit = "hours";
			break;
		case (byte) 0x44:
			unit = "days";
			break;
		default:
			unit = "seconds";
			break;
		}
		byte valueArray[] = new byte[4];
		valueArray[0] = timeArray[1];
		valueArray[1] = timeArray[2];
		valueArray[2] = timeArray[3];
		valueArray[3] = timeArray[4];
		int timeSpan = eConverter.dataToInteger(valueArray);
		return timeSpan + " " + unit;
	}
}
