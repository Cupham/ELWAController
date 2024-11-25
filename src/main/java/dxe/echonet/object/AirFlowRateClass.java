package dxe.echonet.object;

import org.json.JSONObject;

public class AirFlowRateClass {

	public AirFlowRateClass(byte a) {
		this.airFLow = a;
	}

	public AirFlowRateClass(JSONObject jsonStr, String key) {
		
		if (jsonStr.get(key).equals("auto")) {
			this.airFLow = (byte) 0x41;
		} else {
			int l = jsonStr.getInt(key);
			this.airFLow = (byte) (l + (int) 0x30);
		}

	}

	public Object toJsonString() {
		if (this.airFLow == 0x41) {
			return "auto";
		} else {
			return (this.airFLow - 0x30);
		}

	}

	public byte airFLow;

}
