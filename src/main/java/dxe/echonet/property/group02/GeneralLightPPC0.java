package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.object.RGBClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPC0 extends ELProperty {
	public GeneralLightPPC0() {
		super(EPC.xC0, null, "rgb");

	}

	public GeneralLightPPC0(byte[] edt) {
		super(EPC.xC0, edt, "rgb");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		RGBClass rgb = new RGBClass(input.getString(propertyName));

		return new byte[] { rgb.red, rgb.green, rgb.blue };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null && edt.length != 3) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			RGBClass rgb = new RGBClass(edt[0], edt[1], edt[2]);
			obj.put(propertyName,rgb.toJsonString());
			return obj;
		}
	}

}
