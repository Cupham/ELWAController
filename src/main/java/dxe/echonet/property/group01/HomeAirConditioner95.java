package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.TimeFromByteClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditioner95 extends ELProperty {
	public HomeAirConditioner95() {
		super(EPC.x95, null, "timeOfOffTimer");

	}

	public HomeAirConditioner95(byte[] edt) {
		super(EPC.x95, edt, "timeOfOffTimer");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		TimeFromByteClass time = new TimeFromByteClass(input.getString(propertyName));
		return new byte[] { time.hour, time.minute };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			TimeFromByteClass time = new TimeFromByteClass(edt);
			obj.put(propertyName,time.toString());
			return obj;
		}
	}

}
