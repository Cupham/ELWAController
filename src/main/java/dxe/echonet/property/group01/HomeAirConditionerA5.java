package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerA5 extends ELProperty {
	public HomeAirConditionerA5() {
		super(EPC.xA5, null, "airFlowDirectionHorizontal");

	}

	public HomeAirConditionerA5(byte[] edt) {
		super(EPC.xA5, edt, "airFlowDirectionHorizontal");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.AirFlowDirectionHorizontalEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.AirFlowDirectionHorizontalEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
		
	}

}
