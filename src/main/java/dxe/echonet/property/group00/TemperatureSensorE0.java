package dxe.echonet.property.group00;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class TemperatureSensorE0 extends ELProperty {
	public TemperatureSensorE0() {
		super(EPC.xE0, null, "value");

	}

	public TemperatureSensorE0(byte[] edt) {
		super(EPC.xE0, edt, "value");
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
			obj.put(propertyName,( this.edt[1] & 0xFF | ((this.edt[0] & 0xFF) << 8))*0.1);
			return obj;
		}
		
	}

}
