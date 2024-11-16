package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPP91 extends ELProperty {
	public GeneralLightPP91() {
		super(EPC.x91, null, "onTimerTime");

	}

	public GeneralLightPP91(byte[] edt) {
		super(EPC.x91, edt, "onTimerTime");
	}

	@Override
	public byte[] edtFromString(String input) {
		int hour = Integer.parseInt(input.split(":")[0]);
		int minute = Integer.parseInt(input.split(":")[1]);
		return new byte[] { (byte) hour, (byte) minute };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null && edt.length != 2) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			
				obj.put(propertyName,String.format("%d:%d", Byte.toUnsignedInt(edt[0]), Byte.toUnsignedInt(edt[1])));

			return obj;
		}
	}

}
