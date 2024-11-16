package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.TimeFromByteClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditioner91 extends ELProperty {
	public HomeAirConditioner91() {
		super(EPC.x91, null, "timeOfOnTimer");

	}

	public HomeAirConditioner91(byte[] edt) {
		super(EPC.x91, edt, "timeOfOnTimer");
	}

	@Override
	public byte[] edtFromString(String input) {
		TimeFromByteClass time = new TimeFromByteClass(input);
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
