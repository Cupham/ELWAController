package dxe.echonet.property.group00;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class IlluminanceSensorE1 extends ELProperty {
	public IlluminanceSensorE1() {
		super(EPC.xE1, null, "valueInKlux");

	}

	public IlluminanceSensorE1(byte[] edt) {
		super(EPC.xE1, edt, "valueInKlux");
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
			obj.put(propertyName, this.edt[1] & 0xFF | ((this.edt[0] & 0xFF) << 8));
			return obj;
		}
	}

}
