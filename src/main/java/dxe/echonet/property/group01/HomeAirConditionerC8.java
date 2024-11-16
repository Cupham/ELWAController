package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.AirRefreshMethodClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerC8 extends ELProperty {
	public HomeAirConditionerC8() {
		super(EPC.xC8, null, "airRefreshMethod");

	}

	public HomeAirConditionerC8(byte[] edt) {
		super(EPC.xC8, edt, "airRefreshMethod");
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
			AirRefreshMethodClass air = new AirRefreshMethodClass(edt[0]);
			obj.put(propertyName,air.toJsonString());
			return obj;
		}

	}

}
