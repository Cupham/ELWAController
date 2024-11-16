package dxe.echonet.object;

import org.json.JSONObject;

public class AirRefreshMethodClass {

	public AirRefreshMethodClass(byte v) {
		if (v == 0x00) {
			equippedMinusIon = false;
			equippedClusterIon = false;
		}
		if (v == 0x01) {
			equippedMinusIon = true;
			equippedClusterIon = false;
		}
		if (v == 0x10) {
			equippedMinusIon = false;
			equippedClusterIon = true;
		}
		if (v == 0x11) {
			equippedMinusIon = true;
			equippedClusterIon = true;
		}
	}

	public AirRefreshMethodClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		boolean electronic = json.getBoolean("equippedMinusIon");
		boolean clusterIon = json.getBoolean("equippedClusterIon");
		if (electronic) {
			equippedMinusIon = true;
		} else {
			equippedMinusIon = false;
		}
		if (clusterIon) {
			equippedClusterIon = true;
		} else {
			equippedClusterIon = false;
		}

	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		if (equippedMinusIon) {
			obj.put("equippedMinusIon", true);
		} else {
			obj.put("equippedMinusIon", false);
		}

		if (equippedClusterIon) {
			obj.put("equippedClusterIon", true);
		} else {
			obj.put("equippedClusterIon", false);
		}
		return obj;

	}

	public boolean equippedMinusIon;
	public boolean equippedClusterIon;

}
