package dxe.echonet.property.group00;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class IlluminanceSensorE0 extends ELProperty {
	public IlluminanceSensorE0() {
		super(EPC.xE0, null, "valueInLux");

	}

	public IlluminanceSensorE0(byte[] edt) {
		super(EPC.xE0, edt, "valueInLux");
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
			obj.put(propertyName, this.edt[1] & 0xFF | ((this.edt[0] & 0xFF) << 8));
			return obj;
		}
	}

}
