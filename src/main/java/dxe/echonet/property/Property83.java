package dxe.echonet.property;

import java.math.BigInteger;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property83 extends ELProperty {
	public Property83() {
		super(EPC.x83, null, "id");

	}

	public Property83(byte[] edt) {
		super(EPC.x83, edt, "id");
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
			
			obj.put(propertyName, idFromByte());
			return obj;
		}
		

	}

	private String idFromByte() {
		byte[] data = edt;
		byte firstByte = data[0];
		String comProtocol = "";
		String number = "";
		byte realData[] = new byte[data.length - 1];
		for (int i = 1; i < data.length; i++) {
			realData[i - 1] = data[i];
		}
		if (firstByte == (byte) 0xFE) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;

		} else if (firstByte == (byte) 0xFF) {
			for (int i = 0; i < realData.length; i++) {
				number += realData[i];
			}
			return number;
		} else {

			switch (firstByte) {
			case (byte) 0x11:
			case (byte) 0x12:
			case (byte) 0x13:
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
			case (byte) 0x1E:
			case (byte) 0x1F:
				comProtocol = "Power line Communication Protocol a and d systems";
				break;
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
			case (byte) 0x3C:
			case (byte) 0x3D:
			case (byte) 0x3E:
			case (byte) 0x3F:
				comProtocol = "Low-Power Radio Communication Protocol";
				break;
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
				comProtocol = "Extended HBS";
				break;
			case (byte) 0x51:
			case (byte) 0x52:
			case (byte) 0x53:
			case (byte) 0x54:
			case (byte) 0x55:
			case (byte) 0x56:
			case (byte) 0x57:
			case (byte) 0x58:
			case (byte) 0x59:
			case (byte) 0x5A:
			case (byte) 0x5B:
			case (byte) 0x5C:
			case (byte) 0x5D:
			case (byte) 0x5E:
			case (byte) 0x5F:
				comProtocol = "IrDA";
				break;
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
			case (byte) 0x6F:
				comProtocol = "LonTalk";
				break;
			case (byte) 0x71:
			case (byte) 0x72:
			case (byte) 0x73:
			case (byte) 0x74:
			case (byte) 0x75:
			case (byte) 0x76:
			case (byte) 0x77:
			case (byte) 0x78:
			case (byte) 0x79:
			case (byte) 0x7A:
			case (byte) 0x7B:
			case (byte) 0x7C:
			case (byte) 0x7D:
			case (byte) 0x7E:
			case (byte) 0x7F:
				comProtocol = "Bluetooth";
				break;
			case (byte) 0x81:
			case (byte) 0x82:
			case (byte) 0x83:
			case (byte) 0x84:
			case (byte) 0x85:
			case (byte) 0x86:
			case (byte) 0x87:
			case (byte) 0x88:
			case (byte) 0x89:
			case (byte) 0x8A:
			case (byte) 0x8B:
			case (byte) 0x8C:
			case (byte) 0x8D:
			case (byte) 0x8E:
			case (byte) 0x8F:
				comProtocol = "Ethernet";
				break;
			case (byte) 0x91:
			case (byte) 0x92:
			case (byte) 0x93:
			case (byte) 0x94:
			case (byte) 0x95:
			case (byte) 0x96:
			case (byte) 0x97:
			case (byte) 0x98:
			case (byte) 0x99:
			case (byte) 0x9A:
			case (byte) 0x9B:
			case (byte) 0x9C:
			case (byte) 0x9D:
			case (byte) 0x9E:
			case (byte) 0x9F:
				comProtocol = "IEEE802.11/11b";
				break;
			case (byte) 0xA1:
				comProtocol = "Power line Communication Protocol c systems";
				break;
			case (byte) 0xB1:
				comProtocol = "IPv6/Ethernet";
				break;
			case (byte) 0xB2:
				comProtocol = "IPv6/6LoWPAN";
				break;
			default:
				comProtocol = "Undefined";
				break;

			}
			number = new BigInteger(realData).toString() + "";
			return comProtocol + ((number.trim().length() > 1) ? " " + number : "");
		}
	}
}
