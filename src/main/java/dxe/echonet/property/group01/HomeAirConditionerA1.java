package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerA1 extends ELProperty {
	public HomeAirConditionerA1() {
		super(EPC.xA1, null, "automaticControlAirFlowDirection");

	}

	public HomeAirConditionerA1(byte[] edt) {
		super(EPC.xA1, edt, "automaticControlAirFlowDirection");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.AirFlowDirectionEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.AirFlowDirectionEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
