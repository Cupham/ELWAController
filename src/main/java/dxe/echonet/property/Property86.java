package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property86 extends ELProperty {
	public Property86() {
		super(EPC.x86, null, "manufacturerFaultCode");

	}

	public Property86(byte[] edt) {
		super(EPC.x86, edt, "manufacturerFaultCode");
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
			obj.put(propertyName,faultCodeFromByte(edt));
			return obj;
		}
		
	}

	private String faultCodeFromByte(byte[] data) {
		int dataSize = data.length;

		if (dataSize <= 5) {
			return "Invalid";
		}

		int size = (0x000000ff) & data[0];
		if (size < 1) {
			return "Invalid";
		}
		byte manuCode[] = new byte[3];
		manuCode[0] = data[1];
		manuCode[1] = data[2];
		manuCode[2] = data[3];
		String manCode = new String(manuCode);
		size = (dataSize < size) ? dataSize : size;
		byte stringFault[] = new byte[size];
		int j = 0;

		for (int i = 4; (i < dataSize && j < size); i++) {
			stringFault[j++] = data[i];
		}
		String fault = new String(stringFault);
		String faultCode = String.format("%s - %s", manCode, fault);
		return faultCode;
	}
}
