package opt.xml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * time :2012-09-07 12:39 java base knowledge about ObjectInputStream and ObjectOutputStream
 *
 * @author Randy
 */
public class JavaBaseObjectStream {

  public static void main(String[] args) {
    File objectFile = new File("JavaBaseProperties.dat");
    Student jack = new Student("Jack", 23, "北京朝阳区", "111111111");
    Student randy = new Student("Randy", 25, "北京海淀", "222222222222");
    Student tdc = new Student("tdc", 26, "北京", "333333333");
    Student[] students = new Student[] {jack, randy, tdc};
    saveObjectToFile(objectFile, students);
    readObjectFromFile(objectFile);
  }

  /**
   * read object from a file
   *
   * @param objectFile the place that object has saved
   * @return a object array
   */
  public static Object[] readObjectFromFile(File objectFile) {
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;
    Object[] objects = null;
    try {
      fileInputStream = new FileInputStream(objectFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      objectInputStream = new ObjectInputStream(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      objects = (Object[]) objectInputStream.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (objectInputStream != null) {
        try {
          objectInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    if (objects != null && objects.length > 0) {
      for (Student student : (Student[]) objects) {
        System.out.println(student.toString());
      }
    }
    return objects;
  }

  /**
   * save object to a file
   *
   * @param objectFile the object save place
   * @param objects the one that need saved
   */
  public static void saveObjectToFile(File objectFile, Object[] objects) {
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    if (!objectFile.exists()) {
      try {
        objectFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      fileOutputStream = new FileOutputStream(objectFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      objectOutputStream.writeObject(objects);
      objectOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (objectOutputStream != null) {
        try {
          objectOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fileOutputStream != null) {
        try {
          fileOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
