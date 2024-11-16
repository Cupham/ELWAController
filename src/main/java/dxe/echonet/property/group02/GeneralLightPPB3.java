package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPB3 extends ELProperty {
	public GeneralLightPPB3() {
		super(EPC.xB3, null, "lightColorLevelStep");

	}

	public GeneralLightPPB3(byte[] edt) {
		super(EPC.xB3, edt, "lightColorLevelStep");
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
