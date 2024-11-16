package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property99 extends ELProperty {
	public Property99() {
		super(EPC.x99, null, "powerLimit");

	}

	public Property99(byte[] edt) {
		super(EPC.x99, edt, "powerLimit");
	}

	@Override
	public byte[] edtFromString(String input) {
		int num = Integer.parseInt(input);
		byte[] array = new byte[2];

		array[0] = (byte) (num >> 8);
		array[1] = (byte) (num & 0xFF);
		return array;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName, this.edt[1] & 0xFF | ((this.edt[0] & 0xFF) << 8));
			return obj;
		}
	}

}
