package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPBE extends ELProperty {
	public GeneralLightPPBE() {
		super(EPC.xBE, null, "lightColorLevelStepForNightLighting");

	}

	public GeneralLightPPBE(byte[] edt) {
		super(EPC.xBE, edt, "lightColorLevelStepForNightLighting");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		int data = input.getInt(propertyName);
		return new byte[] { (byte) data };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,String.format("0x%02X", edt[0]));
			return obj;
		}
	}

}
