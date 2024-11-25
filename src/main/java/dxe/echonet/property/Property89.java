package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property89 extends ELProperty {
	public Property89() {
		super(EPC.x89, null, "faultDescription");

	}

	public Property89(byte[] edt) {
		super(EPC.x89, edt, "faultDescription");
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
			obj.put(propertyName, faultDescriptionFromByte());
			return obj;
		}

	}

	private String faultDescriptionFromByte() {
		String rs = "";
		byte[] allData = edt;
		if (allData[1] == (byte) 0x03) {
			if (allData[0] == (byte) 0xE9) {
				rs = "repairLocationUnkown";
			} else if (allData[0] == (byte) 0xFF) {
				rs = "fault";
			} else {
				rs = "userDefinable2";
			}
		} else if (allData[1] != (byte) 0x00) {
			switch (allData[0]) {
			case (byte) 0x00:
				rs = "noFault";
				break;
			case (byte) 0x01:
				rs = "trunOffOrUnplug";
				break;
			case (byte) 0x02:
				rs = "resetButton";
				break;
			case (byte) 0x03:
				rs = "setIncorrectly";
				break;
			case (byte) 0x04:
				rs = "supply";
				break;
			case (byte) 0x05:
				rs = "cleaning";
				break;
			case (byte) 0x06:
				rs = "changingBattery";
				break;
			case (byte) 0x07:
				rs = "recoverOperationNoRequired";
				break;
			case (byte) 0x09:
				rs = "userDefinable1";
				break;
			case (byte) 0x0A:
			case (byte) 0x0B:
			case (byte) 0x0C:
			case (byte) 0x0D:
			case (byte) 0x0E:
			case (byte) 0x0F:
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
				rs = "abnormalEventOrSafety";
				break;
			case (byte) 0x14:
			case (byte) 0x15:
			case (byte) 0x16:
			case (byte) 0x17:
			case (byte) 0x18:
			case (byte) 0x19:
			case (byte) 0x1A:
			case (byte) 0x1B:
			case (byte) 0x1C:
			case (byte) 0x1D:
				rs = "switch";
				break;
			case (byte) 0x1E:
			case (byte) 0x1F:
			case (byte) 0x21:
			case (byte) 0x22:
			case (byte) 0x23:
			case (byte) 0x24:
			case (byte) 0x25:
			case (byte) 0x26:
			case (byte) 0x27:
			case (byte) 0x28:
			case (byte) 0x29:
			case (byte) 0x2A:
			case (byte) 0x2B:
			case (byte) 0x2C:
			case (byte) 0x2D:
			case (byte) 0x2E:
			case (byte) 0x2F:
			case (byte) 0x31:
			case (byte) 0x32:
			case (byte) 0x33:
			case (byte) 0x34:
			case (byte) 0x35:
			case (byte) 0x36:
			case (byte) 0x37:
			case (byte) 0x38:
			case (byte) 0x39:
			case (byte) 0x3A:
			case (byte) 0x3B:
				rs = "sensorSystem";
				break;
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
			case (byte) 0x41:
			case (byte) 0x42:
			case (byte) 0x43:
			case (byte) 0x44:
			case (byte) 0x45:
			case (byte) 0x46:
			case (byte) 0x47:
			case (byte) 0x48:
			case (byte) 0x49:
			case (byte) 0x4A:
			case (byte) 0x4B:
			case (byte) 0x4C:
			case (byte) 0x4D:
			case (byte) 0x4E:
			case (byte) 0x4F:
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
				rs = "component";
				break;
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
			case (byte) 0x61:
			case (byte) 0x62:
			case (byte) 0x63:
			case (byte) 0x64:
			case (byte) 0x65:
			case (byte) 0x66:
			case (byte) 0x67:
			case (byte) 0x68:
			case (byte) 0x69:
			case (byte) 0x6A:
			case (byte) 0x6B:
			case (byte) 0x6C:
			case (byte) 0x6D:
			case (byte) 0x6E:
				rs = "controlCircuitBoard";
				break;
			default:
				rs = "userDefinable2";
				break;
			}
		}

		return rs;
	}
}
