package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerC0 extends ELProperty {
	public HomeAirConditionerC0() {
		super(EPC.xC0, null, "ventilationFunction");

	}

	public HomeAirConditionerC0(byte[] edt) {
		super(EPC.xC0, edt, "ventilationFunction");
	}

	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.VentilationFunctionEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.VentilationFunctionEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
		
	}

}
