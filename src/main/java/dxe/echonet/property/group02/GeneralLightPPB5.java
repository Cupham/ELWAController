package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.object.SpecifiableLevelClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPB5 extends ELProperty {
	public GeneralLightPPB5() {
		super(EPC.xB5, null, "maximumSettableLevelForNightLighting");

	}

	public GeneralLightPPB5(byte[] edt) {
		super(EPC.xB5, edt, "maximumSettableLevelForNightLighting");
	}

	@Override
	public byte[] edtFromString(JSONObject input) {
		SpecifiableLevelClass levelClass = new SpecifiableLevelClass(input.getString(propertyName));
		return new byte[] { levelClass.lightLevel, levelClass.color };
	}

	@Override
	public JSONObject edtToStringValue() {
		if(edt == null ) {
			return null;
		} else {
			JSONObject obj = new JSONObject();
			SpecifiableLevelClass levelClass = new SpecifiableLevelClass(edt[0], edt[1]);
			obj.put(propertyName,levelClass.toJsonString());
			return obj;
		}
		
	}

}
