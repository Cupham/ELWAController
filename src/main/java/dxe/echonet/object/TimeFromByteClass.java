package dxe.echonet.object;

public class TimeFromByteClass {
	public TimeFromByteClass(byte[] data) {

		if (data.length == 2) {
			this.hour = data[0];
			this.minute = data[1];
		}
	}

	public TimeFromByteClass(String input) {
		this.hour = (byte) Integer.parseInt(input.split(":")[0]);
		this.minute = (byte) Integer.parseInt(input.split(":")[1]);
	}

	@Override
	public String toString() {
		return String.format("%d:%d", (int) hour, (int) minute).toString();
	}

	public byte hour;
	public byte minute;

}
