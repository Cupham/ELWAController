package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPB6 extends ELProperty {
	public GeneralLightPPB6() {
		super(EPC.xB6, null, "operationMode");

	}

	public GeneralLightPPB6(byte[] edt) {
		super(EPC.xB6, edt, "operationMode");
	}

	@Override
	public byte[] edtFromString(String input) {
		return new byte[] { eConverter.LightModeEnum.valueOf(input).code() };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,eConverter.LightModeEnum.fromCode(edt[0]).toString());
			return obj;
		}
	}

}
