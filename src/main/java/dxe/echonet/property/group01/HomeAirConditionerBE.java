package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerBE extends ELProperty {
	public HomeAirConditionerBE() {
		super(EPC.xBE, null, "outdoorTemperature");

	}

	public HomeAirConditionerBE(byte[] edt) {
		super(EPC.xBE, edt, "outdoorTemperature");
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
			if (edt[0] == 0x7E) {
				obj.put(propertyName,"unmeasurable");
			} else {
				obj.put(propertyName,edt[0]);
			}
			
			return obj;
		}
	}

}
