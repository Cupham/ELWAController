package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property84 extends ELProperty {
	public Property84() {
		super(EPC.x84, null, "instantaneousElectricPowerConsumption");

	}

	public Property84(byte[] edt) {
		super(EPC.x84, edt, "instantaneousElectricPowerConsumption");
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
			obj.put(propertyName,this.edt[1] & 0xFF | ((this.edt[0] & 0xFF) << 8));
			return obj;
		}
	}

}
