package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.SelfCleaningMethodClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerCA extends ELProperty {
	public HomeAirConditionerCA() {
		super(EPC.xCA, null, "selfCleaningMethod");

	}

	public HomeAirConditionerCA(byte[] edt) {
		super(EPC.xCA, edt, "selfCleaningMethod");
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
			SelfCleaningMethodClass air = new SelfCleaningMethodClass(edt[0]);
			obj.put(propertyName,air.toJsonString());
			return obj;
		}


	}

}
