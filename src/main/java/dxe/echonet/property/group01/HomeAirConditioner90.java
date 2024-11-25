package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.TimerReservationClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditioner90 extends ELProperty {
	public HomeAirConditioner90() {
		super(EPC.x90, null, "onTimerReservation");

	}

	public HomeAirConditioner90(byte[] edt) {
		super(EPC.x90, edt, "onTimerReservation");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		TimerReservationClass timer = new TimerReservationClass(input.getString(propertyName));
		return new byte[] { timer.value };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			TimerReservationClass time = new TimerReservationClass(edt[0]);
			obj.put(propertyName,time.toJsonString());
			return obj;
		}
	}

}
