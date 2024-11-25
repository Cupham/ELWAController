package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPBF extends ELProperty {
	public GeneralLightPPBF() {
		super(EPC.xBF, null, "autoMode");

	}

	public GeneralLightPPBF(byte[] edt) {
		super(EPC.xBF, edt, "autoMode");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return new byte[] { eConverter.LightAutoModeEnum.valueOf(input.getString(propertyName)).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.LightAutoModeEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
