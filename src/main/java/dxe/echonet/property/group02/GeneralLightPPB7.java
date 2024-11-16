package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;
import utils.echonet.eConverter;

public class GeneralLightPPB7 extends ELProperty {
	public GeneralLightPPB7() {
		super(EPC.xB7, null, "lightLevelForMainLighting");

	}

	public GeneralLightPPB7(byte[] edt) {
		super(EPC.xB7, edt, "lightLevelForMainLighting");
	}

	@Override
	public byte[] edtFromString(String input) {
		int num = Integer.parseInt(input);
		return new byte[] { (byte) num };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,edt[0]);
			return obj;
		}
	}

}
