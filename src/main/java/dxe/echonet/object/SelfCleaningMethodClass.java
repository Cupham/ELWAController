package dxe.echonet.object;

import org.json.JSONObject;

public class SelfCleaningMethodClass {

	public SelfCleaningMethodClass(byte v) {
		if (v == 0x00) {
			equippedOzone = false;
			equippedDrying = false;
		}
		if (v == 0x01) {
			equippedOzone = true;
			equippedDrying = false;
		}
		if (v == 0x10) {
			equippedOzone = false;
			equippedDrying = true;
		}
		if (v == 0x11) {
			equippedOzone = true;
			equippedDrying = true;
		}
	}

	public SelfCleaningMethodClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		boolean electronic = json.getBoolean("equippedOzone");
		boolean clusterIon = json.getBoolean("equippedDrying");
		if (electronic) {
			equippedOzone = true;
		} else {
			equippedOzone = false;
		}
		if (clusterIon) {
			equippedDrying = true;
		} else {
			equippedDrying = false;
		}

	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		if (equippedOzone) {
			obj.put("equippedOzone", true);
		} else {
			obj.put("equippedOzone", false);
		}

		if (equippedDrying) {
			obj.put("equippedDrying", true);
		} else {
			obj.put("equippedDrying", false);
		}
		return obj;

	}

	public boolean equippedOzone;
	public boolean equippedDrying;

}
