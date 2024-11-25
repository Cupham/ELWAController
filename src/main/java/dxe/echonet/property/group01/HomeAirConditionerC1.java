package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerC1 extends ELProperty {
	public HomeAirConditionerC1() {
		super(EPC.xC1, null, "humidifierFunction");

	}

	public HomeAirConditionerC1(byte[] edt) {
		super(EPC.xC1, edt, "humidifierFunction");
	}

	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.HumidifierFunctionEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.HumidifierFunctionEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
	}

}
