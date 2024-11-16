package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerA4 extends ELProperty {
	public HomeAirConditionerA4() {
		super(EPC.xA4, null, "airFlowDirectionVertical");

	}

	public HomeAirConditionerA4(byte[] edt) {
		super(EPC.xA4, edt, "airFlowDirectionVertical");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.AirFlowDirectionVerticalEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.AirFlowDirectionVerticalEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
	}

}
