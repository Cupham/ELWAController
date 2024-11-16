package dxe.echonet.object;

public class DateFromByteClass {
	public DateFromByteClass(byte[] data) {

		if (data.length == 4) {
			this.day = data[3];
			this.month = data[2];
			this.year = new byte[2];
			this.year[0] = data[0];
			this.year[1] = data[1];
		}
	}

	@Override
	public String toString() {
		int year = this.year[1] & 0xFF | ((this.year[0] & 0xFF) << 8);
		return String.format("%d-%d-%d", year, (int) month, (int) day).toString();
	}

	public byte day;
	public byte month;
	public byte[] year;
}
