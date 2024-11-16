package dxe.echonet.object;

public class ELDateTimeClass {
	public ELDateTimeClass(DateFromByteClass date, TimeFromByteClass time) {
		this.date = date;
		this.time = time;
	}

	@Override
	public String toString() {

		return date.toString() + " " + time.toString();
	}

	public DateFromByteClass date;
	public TimeFromByteClass time;
}
