package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property85 extends ELProperty {
	public Property85() {
		super(EPC.x85, null, "consumedCumulativeElectricEnergy");

	}

	public Property85(byte[] edt) {
		super(EPC.x85, edt, "consumedCumulativeElectricEnergy");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			long rs = this.edt[3] & 0xFF | ((this.edt[2] & 0xFF) << 8) | ((this.edt[1] & 0xFF) << 16)
					| ((this.edt[0] & 0xFF) << 24);
			obj.put(propertyName,rs * 0.001);
			return obj;
		}
		

	}

}
