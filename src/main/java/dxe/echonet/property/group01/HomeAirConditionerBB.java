package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerBB extends ELProperty {
	public HomeAirConditionerBB() {
		super(EPC.xBB, null, "roomTemperature");

	}

	public HomeAirConditionerBB(byte[] edt) {
		super(EPC.xBB, edt, "roomTemperature");
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
				obj.put(propertyName,edt[0]& 0xFF);
			}
			
			return obj;
		}
	}
	

}
