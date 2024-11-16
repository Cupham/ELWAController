package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.ComponentsOperationStatusClass;
import dxe.echonet.object.SelfCleaningMethodClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerCD extends ELProperty {
	public HomeAirConditionerCD() {
		super(EPC.xCD, null, "componentsOperationStatus");

	}

	public HomeAirConditionerCD(byte[] edt) {
		super(EPC.xCD, edt, "componentsOperationStatus");
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
			ComponentsOperationStatusClass com = new ComponentsOperationStatusClass(edt[0]);
			obj.put(propertyName,com.toJsonString());
			return obj;
		}
	}

}
