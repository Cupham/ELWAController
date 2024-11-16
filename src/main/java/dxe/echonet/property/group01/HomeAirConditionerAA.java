package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerAA extends ELProperty {
	public HomeAirConditionerAA() {
		super(EPC.xAA, null, "specialState");

	}

	public HomeAirConditionerAA(byte[] edt) {
		super(EPC.xAA, edt, "specialState");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.SpecialStateEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.SpecialStateEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
		
	}

}
