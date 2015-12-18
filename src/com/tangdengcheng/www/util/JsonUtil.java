package com.tangdengcheng.www.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

	public  static boolean isJsonArray(String test) {
		try {
			new JSONArray(test);
		} catch (JSONException ex) {
			return false;
		}
		return true;
	}

	public  static boolean isJsonObject(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			return false;
		}
		return true;
	}

	public  static boolean isJsonString(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}

}
