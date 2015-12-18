package com.tangdengcheng.opt.xml.to.code;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




/**
 * Depend on json4j library
 * @author bitjjj
 *
 */
public class JsonToXML {

 private static StringBuilder result = new StringBuilder("<?xml version="+Viewstant.SHUANG_YIN_HAO+"1.0"+Viewstant.SHUANG_YIN_HAO+" encoding="+Viewstant.SHUANG_YIN_HAO+"utf-8"+Viewstant.SHUANG_YIN_HAO+"?>");
 private static String[] spacialChars = { "&", "<", ">", Viewstant.SHUANG_YIN_HAO, "'" };
 private static String[] validChars = { "&", "<", ">", Viewstant.SHUANG_YIN_HAO,"'" };

 /**
  * For specail char there are bugs using this way to format xml string
  * Don't suggest to use this method,just for debugging
  * @param input
  * @param indent
  * @return
  */
 public static String prettyFormat(String input, int indent) {
	 System.out.println(input);
	 FileService.saveStringToFile(Viewstant.PROJECT_FILE_PATH+"/json_to_xml.xml", input);
  try {
   Source xmlInput = new StreamSource(new StringReader(input));
   StringWriter stringWriter = new StringWriter();
   StreamResult xmlOutput = new StreamResult(stringWriter);
   TransformerFactory transformerFactory = TransformerFactory
     .newInstance();
   transformerFactory.setAttribute("indent-number", indent);
   Transformer transformer = transformerFactory.newTransformer();
   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
   transformer.transform(xmlInput, xmlOutput);
   return xmlOutput.getWriter().toString();
  } catch (Exception e) {
   throw new RuntimeException(e); 
  }
 }

 public static String toXml(String jsonString){
  try {
   JSONObject jsonObject = new JSONObject(jsonString);
   toXml(jsonObject);
  } catch (Exception e) {
   throw new RuntimeException(e); 
  }
  
  return result.toString();
 }
 
 private static void toXml(JSONObject json) throws Exception {

  Iterator<?> keyIter = json.keys();
  while (keyIter.hasNext()) {
   String key = (String) keyIter.next();
   Object jsonValue = json.get(key);
   if (jsonValue instanceof JSONArray) {
    JSONArray arrayValue = (JSONArray) jsonValue;
    for (int i = 0; i < arrayValue.length(); i++) {
     addBegin(key);
     Object arrItem = arrayValue.get(i);
     if (arrItem instanceof JSONObject) {
      toXml((JSONObject) arrItem);
     } else if (arrItem instanceof JSONArray) {
      String arrItemStr = "{" + key + ":"
        + ((JSONArray) arrItem).toString() + "}";
      toXml(new JSONObject(arrItemStr));
     } else {
      addContent(arrItem.toString());
     }
     addEnd(key);
    }
   } else {
    addBegin(key);
    if (jsonValue instanceof JSONObject) {
     toXml((JSONObject) jsonValue);
    } else {
     addContent(jsonValue.toString());
    }
    addEnd(key);
   }
  }
 }

 private static String replaceSpecialChar(String s) {
  for (int i = 0; i < spacialChars.length; i++) {
   s = s.replaceAll(spacialChars[i], validChars[i]);
  }
  return s;
 }

 private static void addContent(String s) {
//  result.append(replaceSpecialChar(s));
//  result.append(Viewstant.LINE_SEPARATOR);
 }

 private static void addBegin(String str) {
  result.append("<" + str + ">");
  result.append(Viewstant.LINE_SEPARATOR);
 }

 private static void addEnd(String str) {
  result.append("</" + str + ">");
  result.append(Viewstant.LINE_SEPARATOR);
 }

/**
  * @param args
  * @throws IOException
  * @throws JSONException
  */
 public static void main(String[] args){
//  String jsonString = FileService.readStringFromFile(Viewstant.PROJECT_FILE_PATH, "view_tree.json");
////  System.out.println(prettyFormat(toXml(jsonString),4));
//  String xml = toXml(jsonString);
//	 FileService.saveStringToFile(Viewstant.PROJECT_FILE_PATH+"/json_to_xml.xml", xml);
//  System.out.println(xml);
	 Class<? extends Object> asSubclass = JsonToXML.class.asSubclass(Object.class);
//	 boolean assignableFrom = JsonToXML.class.isAssignableFrom(Object.class);
//	 boolean assignableFrom = Object.class.isAssignableFrom(JsonToXMLEx.class);
//	 System.err.println("assignableFrom:"+assignableFrom);
 }

}

