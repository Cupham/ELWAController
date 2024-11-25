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
	public byte[] edtFromString(JSONObject input) {
		int num = input.getInt(propertyName);
		return new byte[] { (byte) num };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			
				obj.put(propertyName,edt[0]& 0xFF);

			return obj;
		}

	}

}
