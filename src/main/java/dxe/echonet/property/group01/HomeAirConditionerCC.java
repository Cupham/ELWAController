package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerCC extends ELProperty {
	public HomeAirConditionerCC() {
		super(EPC.xCC, null, "specialFunction");

	}

	public HomeAirConditionerCC(byte[] edt) {
		super(EPC.xCC, edt, "specialFunction");
	}

	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.SpecialFunctionEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.SpecialFunctionEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
