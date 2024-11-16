package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerCE extends ELProperty {
	public HomeAirConditionerCE() {
		super(EPC.xCE, null, "thermostatOverride");

	}

	public HomeAirConditionerCE(byte[] edt) {
		super(EPC.xCE, edt, "thermostatOverride");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.ThermostatOverrideEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.ThermostatOverrideEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
	}

}
