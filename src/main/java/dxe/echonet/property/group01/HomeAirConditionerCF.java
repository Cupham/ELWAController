package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class HomeAirConditionerCF extends ELProperty {
	public HomeAirConditionerCF() {
		super(EPC.xCF, null, "airPurification");

	}

	public HomeAirConditionerCF(byte[] edt) {
		super(EPC.xCF, edt, "airPurification");
	}

	@Override
	public byte[] edtFromString(String input) {
		if (input.equals("on")) {
			return new byte[] { 0x41 };
		} else {
			return new byte[] { 0x42 };
		}
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			if (edt[0] == 0x41) {
				obj.put(propertyName,"on"); 
			} else {
				obj.put(propertyName,"off"); 
			}
			
			return obj;
		}
	}

}
