package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property82 extends ELProperty {
	public Property82() {
		super(EPC.x82, null, "protocol");

	}

	public Property82(byte[] edt) {
		super(EPC.x82, edt, "protocol");
	}

	@Override
	public byte[] edtFromString(String input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			JSONObject protocol = new JSONObject();
			protocol.put("type", "ECHONET Lite");
			protocol.put("version", versionFromBytes());
			obj.put(propertyName, protocol);
			return obj;
		}
		
		

	}
	private String versionFromBytes() {
		if(edt.length != 4) {
			return "N/A";
		} else {
			return String.format("Release_%c_rev%d", (char )edt[2], edt[3]).toString();
		}
		
	}

}
