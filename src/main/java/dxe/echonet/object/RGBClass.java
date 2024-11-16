package dxe.echonet.object;

import org.json.JSONObject;

public class RGBClass {

	public RGBClass(byte r, byte g, byte b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	public RGBClass(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);

		this.red = (byte) json.getInt("red");
		this.green = (byte) json.getInt("green");
		this.blue = (byte) json.getInt("blue");

	}

	public JSONObject toJsonString() {

		return new JSONObject().put("red", Byte.toUnsignedInt(red)).put("green", Byte.toUnsignedInt(green))
				.put("blue", Byte.toUnsignedInt(blue));

	}

	public byte red;
	public byte green;
	public byte blue;

}
