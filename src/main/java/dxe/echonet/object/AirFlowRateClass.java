package dxe.echonet.object;

public class AirFlowRateClass {

	public AirFlowRateClass(byte a) {
		this.airFLow = a;
	}

	public AirFlowRateClass(String jsonStr) {
		if (jsonStr.equals("auto")) {
			this.airFLow = (byte) 0x41;
		} else {
			int l = Integer.parseInt(jsonStr);
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
