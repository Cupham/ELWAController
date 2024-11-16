package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.RatePowerConsumptionClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditionerB8 extends ELProperty {
	public HomeAirConditionerB8() {
		super(EPC.xB8, null, "ratedPowerConsumption");

	}

	public HomeAirConditionerB8(byte[] edt) {
		super(EPC.xB8, edt, "ratedPowerConsumption");
	}

	@Override
	public byte[] edtFromString(String input) {
		RatePowerConsumptionClass power = new RatePowerConsumptionClass(input);
		return power.toByteArray();
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			RatePowerConsumptionClass power = new RatePowerConsumptionClass(edt);
			obj.put(propertyName,power.toJsonString());
			return obj;
		}
		
		
	}

}
