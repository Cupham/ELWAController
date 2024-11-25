package dxe.echonet.property;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import echowand.common.EPC;

public class Property81 extends ELProperty {
	public Property81() {
		super(EPC.x81, null, "installationLocation");

	}

	public Property81(byte[] edt) {
		super(EPC.x81, edt, "installationLocation");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return installationLocationToByte(input.getString(propertyName));
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			obj.put(propertyName,installationLocationToString(edt));
			return obj;
		}
		

	}

	private String installationLocationToString(byte[] input) {
		String rs = "";
		if (input.length == 1) {
			String bitStr = String.format("%8s", Integer.toBinaryString(input[0] & 0xFF)).replace(' ', '0');
			if (bitStr.equals("11111111")) {
				rs = "Installation location indefinite";
			} else if (bitStr.equals("00000000")) {
				rs = "Installation location not specified";
			} else if (bitStr.equals("00000001")) {
				rs = "Position information";
			} else if (bitStr.substring(1, 5).equals("0000")) {
				rs = "Reserved for future use";
			} else if (bitStr.substring(0, 1).equals("1")) {
				rs = "Free definition";
			} else {
				String locNameByte = bitStr.substring(1, 5);
				String locNumByte = bitStr.substring(5, 8);
				String locationNum;
				String locationName;
				if (locNumByte.equals("000")) {
					locationNum = "Location number has not been specified";
				} else {
					locationNum = String.valueOf(Integer.parseInt(locNumByte, 2));
				}
				switch (locNameByte) {
				case "0001":
					locationName = "Living room";
					break;
				case "0010":
					locationName = "Dining room";
					break;
				case "0011":
					locationName = "Kitchen";
					break;
				case "0100":
					locationName = "Bathroom";
					break;
				case "0101":
					locationName = "Lavatory";
					break;
				case "0110":
					locationName = "Washroom/changing room";
					break;
				case "0111":
					locationName = "Passageway";
					break;
				case "1000":
					locationName = "Room";
					break;
				case "1001":
					locationName = "Stairway";
					break;
				case "1010":
					locationName = "Front door";
					break;
				case "1011":
					locationName = "Storeroom";
					break;
				case "1100":
					locationName = "Garden/perimeter";
					break;
				case "1101":
					locationName = "Garage";
					break;
				case "1110":
					locationName = "Veranda/balcony";
					break;
				default:
					locationName = "Other";
					break;
				}
				rs = locationName + " " + locationNum;
			}
		} else if (input.length == 17) {
			rs = new String(input, StandardCharsets.UTF_8);
		} else {

		}

		return rs;

	}

	private byte[] installationLocationToByte(String inputData) {
		byte[] rs = new byte[17];
		String regex = "\\d+";
		String input = inputData.toLowerCase();
		if (input.equals("installation location indefinite")) {
			rs[0] = (byte) 0xFF;
		} else if (input.equals("installation location not specified")) {
			rs[0] = (byte) 0x00;
		} else if (input.equals("position information")) {
			rs[0] = (byte) 0x01;
		} else if (input.equals("reserved for future use")) {
			rs[0] = (byte) 0x02;
		} else if (input.equals("free definition")) {
			rs[0] = (byte) 0x10;
		} else if (input.contains("living room")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("living room", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x08);
		} else if (input.contains("dining room")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("dining room", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x10);
		} else if (input.contains("kitchen")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("kitchen", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x18);
		} else if (input.contains("bathroom")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("bathroom", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x20);
		} else if (input.contains("lavatory")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("bathroom", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x28);
		} else if (input.contains("washroom/changing room")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("washroom/changing room", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x30);
		} else if (input.contains("passageway")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("passageway", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x38);
		} else if (input.contains("room")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("room", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x40);
		} else if (input.contains("stairway")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("stairway", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x48);
		} else if (input.contains("front door")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("front door", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x50);
		} else if (input.contains("storeroom")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("storeroom", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x58);
		} else if (input.contains("garden/perimeter")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("garden/perimeter", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x60);
		} else if (input.contains("garage")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("garage", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x68);
		} else if (input.contains("veranda/balcony")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("veranda/balcony", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x70);
		} else if (input.contains("other")) {
			int locationNumber = 0;
			String restOfString = new String(input).replace("other", "").trim();
			if (restOfString.matches(regex)) {
				locationNumber = Integer.parseInt(restOfString);
			}
			rs[0] = (byte) (locationNumber + 0x78);
		} else {
			rs = inputData.getBytes(Charset.forName("UTF-8"));
		}
		return rs;

	}

}
