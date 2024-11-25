package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerBF extends ELProperty {
	public HomeAirConditionerBF() {
		super(EPC.xBF, null, "relativeTemperature");

	}

	public HomeAirConditionerBF(byte[] edt) {
		super(EPC.xBF, edt, "relativeTemperature");
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
			if (edt[0] == 0x7E) {
				obj.put(propertyName,"unmeasurable");
			} else {
				obj.put(propertyName,edt[0] & 0xFF);
			}
			
			return obj;
		}
	}

}
