package dxe.echonet.property;

import org.json.JSONObject;

import echowand.common.EPC;

public abstract class ELProperty {
	public EPC epc;
	public byte[] edt;
	public String propertyName;

	public ELProperty(EPC epc, byte[] edt, String propertyName) {
		this.edt = edt;
		this.epc = epc;
		this.propertyName = propertyName;
	}

	public abstract byte[] edtFromString(JSONObject input);

	public abstract JSONObject edtToStringValue();

}
