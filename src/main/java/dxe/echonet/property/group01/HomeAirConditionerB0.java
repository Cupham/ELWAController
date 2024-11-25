package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerB0 extends ELProperty {
	public HomeAirConditionerB0() {
		super(EPC.xB0, null, "operationMode");

	}

	public HomeAirConditionerB0(byte[] edt) {
		super(EPC.xB0, edt, "operationMode");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.OperationModeEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.OperationModeEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
		
	}

}
