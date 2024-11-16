package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPB0 extends ELProperty {
	public GeneralLightPPB0() {
		super(EPC.xB1, null, "lightColor");

	}

	public GeneralLightPPB0(byte[] edt) {
		super(EPC.xB1, edt, "lightColor");
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
