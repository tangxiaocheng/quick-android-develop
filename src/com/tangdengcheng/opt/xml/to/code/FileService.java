package com.tangdengcheng.opt.xml.to.code;

import com.tangdengcheng.www.util.TextUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import tangxiaocheng.log.MyDebug;

public class FileService {
  /**
   * 读取文件内容
   *
   * @param filename 文件名称
   * @return 文件内容
   * @throws Exception
   */

  /**
   * BufferedReader readLine()读取，使用stringBuffer逐行读取
   *
   * @param fileAbsolutePath
   * @return
   */
  public static String readStringFromFile(String fileAbsolutePath) {
    BufferedReader bufferedReader = null;
    StringBuffer stringBuffer = new StringBuffer();
    try {
      bufferedReader = new BufferedReader(new FileReader(fileAbsolutePath));
      String readLineString;
      while ((readLineString = bufferedReader.readLine()) != null) {
        stringBuffer.append(readLineString);
        stringBuffer.append(Viewstant.LINE_SEPARATOR);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != bufferedReader) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return stringBuffer.toString();
  }

  public static String readStringFromFileAddXmlHead(String fileAbsolutePath, String xmlHeadString) {
    BufferedReader bufferedReader = null;
    StringBuffer stringBuffer = new StringBuffer();
    try {
      bufferedReader = new BufferedReader(new FileReader(fileAbsolutePath));
      String readLineString;
      int i = 0;
      while ((readLineString = bufferedReader.readLine()) != null) {
        i++;
        if (i == 2) {
          stringBuffer.append(xmlHeadString);
          stringBuffer.append(Viewstant.LINE_SEPARATOR);
        }
        stringBuffer.append(readLineString);
        if (i != 1) {
          stringBuffer.append(Viewstant.LINE_SEPARATOR);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != bufferedReader) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return stringBuffer.toString();
  }

  /**
   * 使用byte 缓存数组读取，保持原有文本格式
   *
   * @param jsonFileName json 文件名
   * @return 对用json文件的数据
   */
  public static synchronized String readStringFromFile(String path, String jsonFileName) {

    return readStringFromFile_(path + "/" + jsonFileName);
  }

  public static synchronized String readStringFromFile_(String file) {
    FileInputStream inStream;
    byte[] data = null;
    try {
      File logFile = new File(file);
      if (logFile != null && logFile.exists()) {
        inStream = new FileInputStream(logFile);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 定义一个字节数组
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
          outStream.write(buffer, 0, len);
        }
        data = outStream.toByteArray();
        outStream.close();
        inStream.close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (data == null) {
      return "";
    }
    return new String(data);
  }

  /**
   * @param jsonData json 数据
   * @param jsonFileName 文件名
   */
  public static synchronized void saveStringToFile(
      String path, String jsonData, String jsonFileName) {
    String fileAbsoultPath = path + "/" + jsonFileName;
    checkDir(path);
    saveStringToFile(fileAbsoultPath, jsonData);
  }

  public static synchronized void saveStringToFile(String fileAbsoultPath, String jsonData) {
    System.out.println("saveStringToFile:" + fileAbsoultPath);
    if (TextUtils.isEmpty(jsonData)) {
      try {
        File logFile = new File(fileAbsoultPath);
        if (logFile != null && logFile.exists()) {
          logFile.delete();
        }
      } catch (Exception e) {
      }
      return;
    }
    try {
      FileWriter fw;
      File logFile = new File(fileAbsoultPath);
      if (logFile != null && logFile.exists()) {
        logFile.delete();
      } else {
        logFile.createNewFile();
      }
      fw = new FileWriter(fileAbsoultPath, true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(jsonData);
      bw.close();
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static synchronized void saveObject2File(
      String path, Serializable serializableObject, String fileName) {
    try {
      checkDir(path);
      File file = new File(path, fileName);
      if (null == serializableObject) {
        if (file != null && file.exists()) {
          file.delete();
        }
        return;
      }
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(serializableObject);
      oos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static synchronized Serializable readObjectFromFile(String path, String fileName) {
    Serializable object = null;
    try {
      File file = new File(path, fileName);
      if (file.exists()) {
        System.out.println(path);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        object = (Serializable) ois.readObject();
        ois.close();
        fis.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return object;
  }
  /**
   * 检查文件，如果文件不存在，则新建
   *
   * @param dirName
   */
  public static void checkDir(String dirName) {
    File dir = new File(dirName);
    if (null != dir && !dir.exists()) {
      if (!dir.mkdirs()) {
        if (MyDebug.isDebug) {}
      }
    }
  }
  /**
   * 将文件转换成byte数组
   *
   * @return
   */
  public static byte[] getByteByFile(String pathString) {

    Path path = Paths.get(pathString);
    byte[] data = null;
    try {
      data = Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }

  public static void main(String[] args) {
    String pathString = "/Users/tangdengcheng/Desktop/常用色值.png";
    //		String pathString = "/Users/tangdengcheng/tangdengcheng-java/src/log4j.properties";
    System.err.println(new String(getByteByFile(pathString)));
  }
}
