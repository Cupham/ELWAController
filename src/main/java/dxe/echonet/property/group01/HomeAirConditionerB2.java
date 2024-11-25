package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerB2 extends ELProperty {
	public HomeAirConditionerB2() {
		super(EPC.xB2, null, "highspeedOperation");

	}

	public HomeAirConditionerB2(byte[] edt) {
		super(EPC.xB2, edt, "highspeedOperation");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.HighSpeedOperationEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.HighSpeedOperationEnum.fromCode(edt[0]).toString());
			return obj;
		}
		
		
	}

}
