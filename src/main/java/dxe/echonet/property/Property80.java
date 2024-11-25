package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property80 extends ELProperty {
	public Property80() {
		super(EPC.x80, null, "operationStatus");

	}

	public Property80(byte[] edt) {
		super(EPC.x80, edt, "operationStatus");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		boolean b = input.getBoolean(propertyName);
		if (b) {
			return new byte[] { 0x30 };
		} else {
			return new byte[] { 0x31 };
		}
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			if (this.edt[0] == (byte) 0x30) {
				obj.put(this.propertyName, true);
			} else  {
				obj.put(this.propertyName, false);
			} 
			return obj;
		}
		
	}

}
