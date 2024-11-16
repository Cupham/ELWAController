package dxe.echonet.property.group01;

import org.json.JSONObject;

import dxe.echonet.object.TimerReservationClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class HomeAirConditioner94 extends ELProperty {
	public HomeAirConditioner94() {
		super(EPC.x94, null, "offTimerReservation");

	}

	public HomeAirConditioner94(byte[] edt) {
		super(EPC.x94, edt, "offTimerReservation");
	}

	@Override
	public byte[] edtFromString(String input) {
		TimerReservationClass timer = new TimerReservationClass(input);
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
