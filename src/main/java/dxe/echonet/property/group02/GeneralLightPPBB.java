package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPBB extends ELProperty {
	public GeneralLightPPBB() {
		super(EPC.xBB, null, "lightColorForMainLighting");

	}

	public GeneralLightPPBB(byte[] edt) {
		super(EPC.xBB, edt, "lightColorForMainLighting");
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
