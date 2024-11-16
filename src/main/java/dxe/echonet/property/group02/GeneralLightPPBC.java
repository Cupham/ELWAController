package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPBC extends ELProperty {
	public GeneralLightPPBC() {
		super(EPC.xBC, null, "lightColorLevelStepForMainLighting");

	}

	public GeneralLightPPBC(byte[] edt) {
		super(EPC.xBC, edt, "lightColorLevelStepForMainLighting");
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
