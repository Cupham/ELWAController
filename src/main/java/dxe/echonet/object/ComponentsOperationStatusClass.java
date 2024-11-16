package dxe.echonet.object;

import org.json.JSONObject;

public class ComponentsOperationStatusClass {

	public ComponentsOperationStatusClass(byte v) {
		if (v == 0x00) {
			compressor = false;
			thermostat = false;
		}
		if (v == 0x01) {
			compressor = true;
			thermostat = false;
		}
		if (v == 0x10) {
			compressor = false;
			thermostat = true;
		}
		if (v == 0x11) {
			compressor = true;
			thermostat = true;
		}
	}

	public ComponentsOperationStatusClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		boolean compressor = json.getBoolean("compressor");
		boolean thermostat = json.getBoolean("thermostat");
		if (compressor) {
			compressor = true;
		} else {
			compressor = false;
		}
		if (thermostat) {
			thermostat = true;
		} else {
			thermostat = false;
		}

	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		if (compressor) {
			obj.put("compressor", true);
		} else {
			obj.put("compressor", false);
		}

		if (thermostat) {
			obj.put("thermostat", true);
		} else {
			obj.put("thermostat", false);
		}
		return obj;

	}

	public boolean compressor;
	public boolean thermostat;

}
