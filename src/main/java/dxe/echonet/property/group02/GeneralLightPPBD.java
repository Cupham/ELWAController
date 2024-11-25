package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPBD extends ELProperty {
	public GeneralLightPPBD() {
		super(EPC.xBD, null, "lightColorLevelForNightLighting");

	}

	public GeneralLightPPBD(byte[] edt) {
		super(EPC.xBD, edt, "lightColorLevelForNightLighting");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.LightColorEnum.valueOf(input.getString(propertyName)).code() };
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
