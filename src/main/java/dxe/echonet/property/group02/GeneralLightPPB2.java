package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPB2 extends ELProperty {
	public GeneralLightPPB2() {
		super(EPC.xB2, null, "lightLevelStep");

	}

	public GeneralLightPPB2(byte[] edt) {
		super(EPC.xB2, edt, "lightLevelStep");
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
