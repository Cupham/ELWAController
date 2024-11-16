package dxe.echonet.property.group02;

import org.json.JSONObject;

import dxe.echonet.object.SpecifiableLevelClass;
import dxe.echonet.property.ELProperty;
import echowand.common.EPC;

public class GeneralLightPPB4 extends ELProperty {
	public GeneralLightPPB4() {
		super(EPC.xB4, null, "maximumSpecifiableLevel");

	}

	public GeneralLightPPB4(byte[] edt) {
		super(EPC.xB4, edt, "maximumSpecifiableLevel");
	}

	@Override
	public byte[] edtFromString(String input) {
		SpecifiableLevelClass levelClass = new SpecifiableLevelClass(input);
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
