package dxe.echonet.object;

import org.json.JSONObject;

public class RatePowerConsumptionClass {
	public RatePowerConsumptionClass(byte[] edt) {
		if (edt.length == 8) {
			cooling = ((edt[0] & 0xFF) << 8) | (edt[1] & 0xFF);
			heating = ((edt[2] & 0xFF) << 8) | (edt[3] & 0xFF);
			dehumidifying = ((edt[4] & 0xFF) << 8) | (edt[5] & 0xFF);
			circulation = ((edt[6] & 0xFF) << 8) | (edt[7] & 0xFF);
		}
	}

	public RatePowerConsumptionClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if (json.get("cooling").equals("unsupported")) {
			this.cooling = 65534;
		} else {
			this.cooling = json.getInt("cooling");
		}
		if (json.get("heating").equals("unsupported")) {
			this.heating = 65534;
		} else {
			this.heating = json.getInt("heating");
		}
		if (json.get("dehumidifying").equals("unsupported")) {
			this.dehumidifying = 65534;
		} else {
			this.dehumidifying = json.getInt("dehumidifying");
		}
		if (json.get("circulation").equals("unsupported")) {
			this.circulation = 65534;
		} else {
			this.circulation = json.getInt("circulation");
		}

	}

	public JSONObject toJsonString() {
		JSONObject obj = new JSONObject();
		if (this.cooling == 65534) {
			obj.put("cooling", "unsupported");
		} else {
			obj.put("cooling", this.cooling);
		}
		if (this.heating == 65534) {
			obj.put("heating", "unsupported");
		} else {
			obj.put("heating", this.heating);
		}
		if (this.dehumidifying == 65534) {
			obj.put("dehumidifying", "unsupported");
		} else {
			obj.put("dehumidifying", this.dehumidifying);
		}
		if (this.circulation == 65534) {
			obj.put("circulation", "unsupported");
		} else {
			obj.put("circulation", this.circulation);
		}
		return obj;

	}

	public byte[] toByteArray() {
		byte[] rs = new byte[8];
		rs[0] = (byte) ((this.cooling >> 8) & 0xFF);
		rs[1] = (byte) (this.cooling & 0xFF);
		rs[2] = (byte) ((this.heating >> 8) & 0xFF);
		rs[3] = (byte) (this.heating & 0xFF);
		rs[4] = (byte) ((this.dehumidifying >> 8) & 0xFF);
		rs[5] = (byte) (this.dehumidifying & 0xFF);
		rs[6] = (byte) ((this.circulation >> 8) & 0xFF);
		rs[7] = (byte) (this.circulation & 0xFF);

		return rs;

	}

	public int cooling;
	public int heating;
	public int dehumidifying;
	public int circulation;

}
