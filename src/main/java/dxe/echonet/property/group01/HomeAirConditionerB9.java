package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB9 extends ELProperty {
	public HomeAirConditionerB9() {
		super(EPC.xB9, null, "currentConsumption");

	}

	public HomeAirConditionerB9(byte[] edt) {
		super(EPC.xB9, edt, "currentConsumption");
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
			obj.put(propertyName, (this.edt[1] & 0xFF | (this.edt[0] & 0xFF) << 8) * 0.1);
			return obj;
		}
	}

}
