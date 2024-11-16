package dxe.echonet.object;

import org.json.JSONObject;

public class TimerReservationClass {

	public TimerReservationClass(byte v) {
		this.value = v;
	}

	public TimerReservationClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		boolean timeBase = json.getBoolean("timeBased");
		boolean relativeTimeBase = json.getBoolean("relativeTimeBased");
		if (timeBase) {
			if (relativeTimeBase)
				this.value = 0x41;
			else
				this.value = 0x43;
		} else {
			if (relativeTimeBase)
				this.value = 0x44;
			else
				this.value = 0x42;
		}
	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		switch (this.value) {
		case 0x41:
			obj.put("timeBased", true);
			obj.put("relativeTimeBased", true);
			break;
		case 0x42:
			obj.put("timeBased", false);
			obj.put("relativeTimeBased", false);
			break;
		case 0x43:
			obj.put("timeBased", true);
			obj.put("relativeTimeBased", false);
			break;
		case 0x44:
			obj.put("timeBased", false);
			obj.put("relativeTimeBased", true);
			break;
		default:
			break;
		}
		return obj;

	}

	public byte value;

}
