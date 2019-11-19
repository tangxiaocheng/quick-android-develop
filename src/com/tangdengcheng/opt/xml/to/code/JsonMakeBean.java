package com.tangdengcheng.opt.xml.to.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tangdengcheng.www.util.JsonUtil;

public class JsonMakeBean {

	Save2File systemOut;
	int jsonStringIndex = 0;
	int jsonObjectIndex = 0;
	int jsonArrayIndex = 0;
	private String javaJsonBeanClassPackageName;
	private  String sourceString;
	private String className;
	private Map<String, String> totalMap =new HashMap<String, String>();
	private Map<String, Map<String, String>> total =new HashMap<String, Map<String, String>>();

public JsonMakeBean( int jsonStringIndex,
			int jsonObjectIndex, int jsonArrayIndex,
			String javaJsonBeanClassPackageName, String sourceString,String className) {
		super();
		this.jsonStringIndex = jsonStringIndex;
		this.jsonObjectIndex = jsonObjectIndex;
		this.jsonArrayIndex = jsonArrayIndex;
		this.javaJsonBeanClassPackageName = javaJsonBeanClassPackageName;
		this.sourceString = sourceString;
		this.className = className;
		this.systemOut = new Save2File(this.className, "/Users/tangdengcheng/Documents/github_workplace/JavaBase/JavaUtil/src/com/tangdengcheng/opt/xml/to/code/", "java");
	}

public void make() {
	systemOut.println("package " + javaJsonBeanClassPackageName + ";");
	systemOut.println();
	systemOut.println("public class " + className + " {");
	loopJson(this.sourceString, "");
	
	for (Iterator<String> iterator = totalMap.keySet().iterator(); iterator.hasNext();) {
		String key = iterator.next();
		String value = totalMap.get(key);
		systemOut.println("	private"+" "+value+" "+key+" ;");
	}
	
	systemOut.println("}");
	systemOut.saveStringToFile();
	System.out.println("jsonStringIndex:" + this.jsonStringIndex
			+ " jsonObjectIndex:" + this.jsonObjectIndex + " jsonArrayIndex:"
			+ this.jsonArrayIndex);
}

public	static void main(String[] args) throws Exception {
	
		String string3 = ".json";
		String string2 = "test_json_bean";
		String jsonPath = "/Users/randy/quick-android-develop/file/";
		String jsonFile = jsonPath + string2 + string3;
		String tempSourceString = FileService.readStringFromFile(jsonFile);
		String tempSourceString2 =FileService.readStringFromFile(jsonPath, string2 + string3);
	
		System.err.println(tempSourceString);
		System.err.println(tempSourceString2);

//		JsonMakeBean jsonMakeBean = new JsonMakeBean ();
//		jsonMakeBean.make ();
	}

	public void loopJson(String sourceString, String jsonKey) {
		try {
			if (JsonUtil.isJsonString(sourceString)) {
				jsonStringIndex++;
				if (JsonUtil.isJsonObject(sourceString)) {
					jsonObjectIndex++;
					JSONObject jsonObject = new JSONObject(sourceString);
					System.out.println("jsonObject:" + jsonObject);
					for (@SuppressWarnings("unchecked")
					Iterator<String> iterator = jsonObject.keys(); iterator
							.hasNext();) {
						String key = iterator.next();
						String value = jsonObject.get(key).toString();
						System.out.println("key:" + key + " --->  value:"+ value);
						if (!JsonUtil.isJsonString(value)) {
							
						}
						loopJson1(value, key);
					}
				} else if (JsonUtil.isJsonArray(sourceString)) {
					jsonArrayIndex++;
					JSONArray jsonArray = new JSONArray(sourceString);
					for (int i = 0; i < jsonArray.length(); i++) {
						String jsonArrayChild = jsonArray.get(i).toString();
						System.out.println("jsonArrayChild index--------" + i
								+ ":" + jsonArrayChild);
						loopJson(jsonArrayChild, jsonKey);
					}
				} else {
					System.out.println("other json");
				}
			} else {
				System.out.println("---not json obj--->" + " key:" + jsonKey+ " value:" + sourceString);
				totalMap.put("array_"+jsonArrayIndex+"_"+"_"+jsonKey, "String");
				System.out.println("jsonStringIndex:" + jsonStringIndex
						+ " jsonObjectIndex:" + jsonObjectIndex
						+ " jsonArrayIndex:" + jsonArrayIndex);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void loopJson1(String sourceString, String jsonKey) {
		try {
			if (JsonUtil.isJsonString(sourceString)) {
				jsonStringIndex++;
				if (JsonUtil.isJsonObject(sourceString)) {
					jsonObjectIndex++;
					JSONObject jsonObject = new JSONObject(sourceString);
					System.out.println("jsonObject:" + jsonObject);
					for (@SuppressWarnings("unchecked")
					Iterator<String> iterator = jsonObject.keys(); iterator
							.hasNext();) {
						String key = iterator.next();
						String value = jsonObject.get(key).toString();
						System.out.println("key:" + key + " --->  value:"+ value);
						loopJson(value, key);
					}
				} else if (JsonUtil.isJsonArray(sourceString)) {
					jsonArrayIndex++;
					JSONArray jsonArray = new JSONArray(sourceString);
					for (int i = 0; i < jsonArray.length(); i++) {
						String jsonArrayChild = jsonArray.get(i).toString();
						System.out.println("jsonArrayChild index--------" + i
								+ ":" + jsonArrayChild);
						loopJson(jsonArrayChild, jsonKey);
					}
				} else {
					System.out.println("other json");
				}
			} else {
				System.out.println("---not json obj--->" + " key:" + jsonKey+ " value:" + sourceString);
				totalMap.put("array_"+jsonArrayIndex+"_"+"_"+jsonKey, "String");
				System.out.println("jsonStringIndex:" + jsonStringIndex
						+ " jsonObjectIndex:" + jsonObjectIndex
						+ " jsonArrayIndex:" + jsonArrayIndex);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void loopJson2(String sourceString, String jsonKey) {
		try {
			if (JsonUtil.isJsonString(sourceString)) {
				jsonStringIndex++;
				if (JsonUtil.isJsonObject(sourceString)) {
					jsonObjectIndex++;
					JSONObject jsonObject = new JSONObject(sourceString);
					System.out.println("jsonObject:" + jsonObject);
					for (@SuppressWarnings("unchecked")
					Iterator<String> iterator = jsonObject.keys(); iterator
							.hasNext();) {
						String key = iterator.next();
						String value = jsonObject.get(key).toString();
						System.out.println("key:" + key + " --->  value:"+ value);
						loopJson2(value, key);
					}
				} else if (JsonUtil.isJsonArray(sourceString)) {
					jsonArrayIndex++;
					JSONArray jsonArray = new JSONArray(sourceString);
					for (int i = 0; i < jsonArray.length(); i++) {
						String jsonArrayChild = jsonArray.get(i).toString();
						System.out.println("jsonArrayChild index--------" + i
								+ ":" + jsonArrayChild);
						loopJson(jsonArrayChild, jsonKey);
					}
				} else {
					System.out.println("other json");
				}
			} else {
				System.out.println("---not json obj--->" + " key:" + jsonKey+ " value:" + sourceString);
//				totalMap.put("array_"+jsonArrayIndex+"_"+"_"+jsonKey, "String");
				System.out.println("jsonStringIndex:" + jsonStringIndex
						+ " jsonObjectIndex:" + jsonObjectIndex
						+ " jsonArrayIndex:" + jsonArrayIndex);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void loopJson3(String sourceString, String jsonKey) {
		try {
			if (JsonUtil.isJsonString(sourceString)) {
				jsonStringIndex++;
				if (JsonUtil.isJsonObject(sourceString)) {
					jsonObjectIndex++;
					JSONObject jsonObject = new JSONObject(sourceString);
					System.out.println("jsonObject:" + jsonObject);
					for (@SuppressWarnings("unchecked")
					Iterator<String> iterator = jsonObject.keys(); iterator
							.hasNext();) {
						String key = iterator.next();
						String value = jsonObject.get(key).toString();
						System.out.println("key:" + key + " --->  value:"+ value);
						loopJson3(value, key);
					}
				} else if (JsonUtil.isJsonArray(sourceString)) {
					jsonArrayIndex++;
					JSONArray jsonArray = new JSONArray(sourceString);
					for (int i = 0; i < jsonArray.length(); i++) {
						String jsonArrayChild = jsonArray.get(i).toString();
						System.out.println("jsonArrayChild index--------" + i
								+ ":" + jsonArrayChild);
						loopJson(jsonArrayChild, jsonKey);
					}
				} else {
					System.out.println("other json");
				}
			} else {
				System.out.println("---not json obj--->" + " key:" + jsonKey+ " value:" + sourceString);
				totalMap.put("array_"+jsonArrayIndex+"_"+"_"+jsonKey, "String");
				System.out.println("jsonStringIndex:" + jsonStringIndex
						+ " jsonObjectIndex:" + jsonObjectIndex
						+ " jsonArrayIndex:" + jsonArrayIndex);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
