package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPBA extends ELProperty {
	public GeneralLightPPBA() {
		super(EPC.xBA, null, "lightLevelStepForNightLighting");

	}

	public GeneralLightPPBA(byte[] edt) {
		super(EPC.xBA, edt, "lightLevelStepForNightLighting");
	}

	@Override
	public byte[] edtFromString(String input) {
		int data = Integer.decode(input);
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
