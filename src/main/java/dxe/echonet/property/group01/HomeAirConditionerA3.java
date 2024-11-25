package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerA3 extends ELProperty {
	public HomeAirConditionerA3() {
		super(EPC.xA3, null, "automaticSwingAirFlow");

	}

	public HomeAirConditionerA3(byte[] edt) {
		super(EPC.xA3, edt, "automaticSwingAirFlow");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.SwingAirFlowEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.SwingAirFlowEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
