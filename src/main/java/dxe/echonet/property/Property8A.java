package dxe.echonet.property;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import echowand.common.EPC;

public class Property8A extends ELProperty {
	public Property8A() {
		super(EPC.x8A, null, "manufacturer");

	}

	public Property8A(byte[] edt) {
		super(EPC.x8A, edt, "manufacturer");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		return null;
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			
			return manufacturerFromBytes();
		}

	}
	private JSONObject manufacturerFromBytes() {
		JSONObject mf = new JSONObject();
		String ja = "N/A";
		String en = "N/A";
		String code = String.format("%02X%02X%02X", edt[0], edt[1], edt[2]).toString();
		mf.put("code", code);
		ClassLoader loader = Property8A.class.getClassLoader();
		InputStream is = loader.getResourceAsStream("manufacturer.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Manufacturer[] manufacturer = objectMapper.readValue(is, Manufacturer[].class);
			for (Manufacturer m : manufacturer) {
				if (m.code.equals(code)) {
					ja = m.company;
					en = m.companyEn;

				}
			}
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject description = new JSONObject();
		description.put("ja", ja);
		description.put("en", en);
		mf.put("descriptions", description);
		
		
		return new JSONObject().put(propertyName, mf);
		
	}
	private static class Manufacturer {
		public String code;
		public String company;
		public String companyEn;

		@SuppressWarnings("unused")
		public Manufacturer() {

		}

	}

}
