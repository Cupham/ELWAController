package dxe.echonet.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dxe.echonet.property.ELProperty;
import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Node;
import echowand.service.Service;
import utils.echonet.eConverter;

public abstract class ECHONETObject {
	@JsonIgnore
	private static final Logger logger = Logger.getLogger(ECHONETObject.class.getName());
	@JsonIgnore
	private String deviceID;
	public Timer timer;
	private EOJ eoj;
	/**
	 * Device IP
	 * 
	 */
	@JsonIgnore
	private Node node;
	/**
	 * Class group code
	 */
	@JsonIgnore
	private byte groupCode;
	/**
	 * Class code
	 */
	@JsonIgnore
	private byte classCode;
	/**
	 * Instance code
	 */
	@JsonIgnore
	private byte instanceCode;
	@JsonIgnore
	private ArrayList<EPC> gettableEPCList;
	@JsonIgnore
	private ArrayList<EPC> settableEPCList;
	@JsonIgnore
	private ArrayList<EPC> observableEPCList;
	@JsonIgnore
	private ArrayList<EPC> pollingEPCList;
	private ArrayList<EPC> notPollingEPCList;
	@JsonIgnore
	private Map<String, ELProperty> supportCommands;
	@JsonIgnore
	private Map<EPC, ELProperty> properties;
	@JsonIgnore
	public final Service service;

	public ECHONETObject(Node node, EOJ eoj, Service s) {
		this.node = node;
		this.eoj = eoj;
		this.deviceID = eConverter.ipToHex(node.getNodeInfo().toString()) + "-" + eoj.toString();
		gettableEPCList = new ArrayList<EPC>();
		settableEPCList = new ArrayList<EPC>();
		observableEPCList = new ArrayList<EPC>();
		pollingEPCList = new ArrayList<EPC>();
		supportCommands = new HashMap<String, ELProperty>();
		properties = new HashMap<EPC, ELProperty>();
		notPollingEPCList = new ArrayList<EPC>();
		notPollingEPCList.add(EPC.x82);
		notPollingEPCList.add(EPC.x83);
		notPollingEPCList.add(EPC.x8A);
		notPollingEPCList.add(EPC.x8B);
		notPollingEPCList.add(EPC.x8C);
		notPollingEPCList.add(EPC.x8D);
		notPollingEPCList.add(EPC.x8E);
		this.service = s;

	}

	public String toJsonString() {
		ELProperty pp97 = getProperties().get(EPC.x97);
		ELProperty pp98 = getProperties().get(EPC.x98);
		
		
		JSONObject json = new JSONObject();
		
		List<JSONObject> epcLists = new ArrayList<JSONObject>();
		if (pp97 != null && pp98 != null) {
			json.put("currentDateAndTime", pp98.toString() + " T " + pp97.toString());
		}
		ELProperty pp82 = getProperties().get(EPC.x82);
		if(pp82 == null) {
			JSONObject protocol = new JSONObject();
			protocol.put("type", "ECHONET Lite");
			protocol.put("version", "unknown");
			json.put("protocol", protocol);
		}
		for (EPC epc : getGettableEPCList()) {
			if (epc == EPC.x97 || epc == EPC.x98) {

			} else {
				if(getProperties().get(epc) != null)
					epcLists.add(getProperties().get(epc).edtToStringValue());
			}
			
		}
		if(epcLists.size() > 0) {
			for (JSONObject jsonObject : epcLists) {
	            for (String key : jsonObject.keySet()) {
	            	json.put(key, jsonObject.get(key));
	            }
	        }
		}
		System.out.println(json.toString());
		return json.toString();
	}
	
	public abstract void pollData();

	public abstract void initDevice();

	public ArrayList<EPC> getNotPollingEPCList() {
		return notPollingEPCList;
	}

	public void setNotPollingEPCList(ArrayList<EPC> notPollingEPCList) {
		this.notPollingEPCList = notPollingEPCList;
	}

	public Map<String, ELProperty> getSupportCommands() {
		return supportCommands;
	}

	public void setSupportCommands(Map<String, ELProperty> supportCommands) {
		this.supportCommands = supportCommands;
	}

	public Map<EPC, ELProperty> getProperties() {
		return properties;
	}

	public void updateProperties(ELProperty pp) {
		this.properties.put(pp.epc, pp);
	}

	public void setProperties(Map<EPC, ELProperty> properties) {
		this.properties = properties;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public EOJ getEoj() {
		return eoj;
	}

	public void setEoj(EOJ eoj) {
		this.eoj = eoj;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public byte getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(byte groupCode) {
		this.groupCode = groupCode;
	}

	public byte getClassCode() {
		return classCode;
	}

	public void setClassCode(byte classCode) {
		this.classCode = classCode;
	}

	public byte getInstanceCode() {
		return instanceCode;
	}

	public void setInstanceCode(byte instanceCode) {
		this.instanceCode = instanceCode;
	}

	public ArrayList<EPC> getGettableEPCList() {
		return gettableEPCList;
	}

	public void setGettableEPCList(ArrayList<EPC> gettableEPCList) {
		this.gettableEPCList = gettableEPCList;
	}

	public ArrayList<EPC> getSettableEPCList() {
		return settableEPCList;
	}

	public void setSettableEPCList(ArrayList<EPC> settableEPCList) {
		this.settableEPCList = settableEPCList;
	}

	public ArrayList<EPC> getObservableEPCList() {
		return observableEPCList;
	}

	public void setObservableEPCList(ArrayList<EPC> observableEPCList) {
		this.observableEPCList = observableEPCList;
	}

	public ArrayList<EPC> getPollingEPCList() {
		return pollingEPCList;
	}

	public void setPollingEPCList(ArrayList<EPC> pollingEPCList) {
		this.pollingEPCList = pollingEPCList;
	}

}
