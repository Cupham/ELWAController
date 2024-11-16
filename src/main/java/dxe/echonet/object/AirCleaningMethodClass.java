package dxe.echonet.object;

import org.json.JSONObject;

public class AirCleaningMethodClass {

	public AirCleaningMethodClass(byte v) {
		if (v == 0x00) {
			equippedElectronic = false;
			equippedClusterIon = false;
		}
		if (v == 0x01) {
			equippedElectronic = true;
			equippedClusterIon = false;
		}
		if (v == 0x10) {
			equippedElectronic = false;
			equippedClusterIon = true;
		}
		if (v == 0x11) {
			equippedElectronic = true;
			equippedClusterIon = true;
		}
	}

	public AirCleaningMethodClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		boolean electronic = json.getBoolean("equippedElectronic");
		boolean clusterIon = json.getBoolean("equippedClusterIon");
		if (electronic) {
			equippedElectronic = true;
		} else {
			equippedElectronic = false;
		}
		if (clusterIon) {
			equippedClusterIon = true;
		} else {
			equippedClusterIon = false;
		}

	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		if (equippedElectronic) {
			obj.put("equippedElectronic", true);
		} else {
			obj.put("equippedElectronic", false);
		}

		if (equippedClusterIon) {
			obj.put("equippedClusterIon", true);
		} else {
			obj.put("equippedClusterIon", false);
		}
		return obj;

	}

	public boolean equippedElectronic;
	public boolean equippedClusterIon;

}
