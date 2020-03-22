package com.tangdengcheng.www.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * time :2012-09-07 12:39 java base knowledge about properties
 *
 * @author Randy
 */
public class JavaBaseProperties {
  public static void main(String[] args) throws Exception {
    File propertiesFile = new File("JavaBaseProperties.properties");

    Properties properties = new Properties();
    properties.put("key1", "just a string value");
    properties.put("key2", "key2 value");
    properties.put("key3", "key3 value");
    properties.put("key4", "key4 value");
    savePropertiesToFile(properties, propertiesFile);
    readPropertiesFromFile(propertiesFile);
  }

  /**
   * get the key value from a properties file this is just print the key and value ,if you want do
   * something ,do something
   *
   * @param file the properties file
   */
  public static Properties readPropertiesFromFile(File file) {
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Properties properties = new Properties();
    try {

      properties.load(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    Set<String> set = properties.stringPropertyNames(); // get the all key
    // set
    for (String key : set) {
      System.out.println(key + ":" + properties.getProperty(key));
    }
    return properties;
  }

  /**
   * Properties can save any object , but if you want save it in file ,only string key-value
   *
   * @param file the properties save place
   */
  public static void savePropertiesToFile(Properties properties, File file) {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      properties.store(fileOutputStream, "save a string value and a student object ");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileOutputStream != null) {
        try {
          fileOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      properties.list(System.out);
    }
  }
}
