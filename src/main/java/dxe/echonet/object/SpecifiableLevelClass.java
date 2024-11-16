package dxe.echonet.object;

import org.json.JSONObject;

public class SpecifiableLevelClass {

	public SpecifiableLevelClass(byte lightLevelByte, byte colorByte) {
		this.lightLevel = lightLevelByte;
		this.color = colorByte;
	}

	public SpecifiableLevelClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		if (json.get("lightLevel").equals("notLightLevel")) {
			this.lightLevel = (byte) 0x00;
		} else {
			int l = json.getInt("lightLevel");
			this.lightLevel = (byte) l;
		}
		if (json.get("color").equals("notColor")) {
			this.color = (byte) 0x00;
		} else {
			int c = json.getInt("color");
			this.color = (byte) c;
		}

	}

	public JSONObject toJsonString() {
		int l = Byte.toUnsignedInt(lightLevel);
		int c = Byte.toUnsignedInt(color);

		return new JSONObject().put("lightLevel", l > 0 ? l : "notLightLevel").put("color", c > 0 ? 0 : "notColor");

	}

	public byte lightLevel;
	public byte color;

}
