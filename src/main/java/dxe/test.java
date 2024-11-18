package dxe;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {

	public static void main(String[] args) {
		List<JSONObject> objs = new ArrayList<JSONObject>();
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject obj4 = new JSONObject();
		obj4.put("en",true);
		obj4.put("ja", 32);
		obj2.put("installationLocation",obj4);
		
		obj1.put("datda",obj4);
		objs.add(obj1);
		objs.add(obj2);
		JSONObject mergedJson = new JSONObject();
        for (JSONObject jsonObject : objs) {
            for (String key : jsonObject.keySet()) {
                mergedJson.put(key, jsonObject.get(key));
            }
        }

        // Convert to JSON string
        String jsonString = mergedJson.toString();
        System.out.println(jsonString); //
        System.out.println(obj4.get("en")); 
		
	}

	public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
        JSONObject mergedJSON = new JSONObject();
        try {
            // getNames(): Get an array of field names from a JSONObject.
            mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
            for (String crunchifyKey : JSONObject.getNames(json2)) {
                // get(): Get the value object associated with a key.
                mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
            }
        } catch (JSONException e) {
            // RunttimeException: Constructs a new runtime exception with the specified detail message.
            // The cause is not initialized, and may subsequently be initialized by a call to initCause.
            throw new RuntimeException("JSON Exception" + e);
        }
        return mergedJSON;
    }
}
