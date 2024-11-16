package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPB1 extends ELProperty {
	public GeneralLightPPB1() {
		super(EPC.xB1, null, "lightColor");

	}

	public GeneralLightPPB1(byte[] edt) {
		super(EPC.xB1, edt, "lightColor");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.LightColorEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.LightColorEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
