/**
 * 文 件 名: Debug.java 版 权: Wuhan ArcherMind Technology All rights reserved 描 述: 打印日志工具类 修 改 人: 002300
 * 梅义 修改时间: 2012-10-30
 */
package tangxiaocheng.log;

import opt.xml.to.code.FileService;
import opt.xml.util.TextUtils;
import java.io.File;

/** 打印日志工具类 */
public class MyDebug {
  /** logContent.replace("\"{", "{").replace("}\"", "}").replace("\\", "") 是否打印日志 */
  public static final boolean isDebug = true;

  private static final String DEBUG_PATH = "/Users/tangdengcheng/Desktop/javalog/";

  public static void main(String[] args) {
    MyDebug.printE("ss", "ddddd");
    MyDebug.printI("ss", "ddddd");
//    saveStringToFile("111ddd111", "3532gfjjj errewtwere", "txt");
  }

  public static void printI(String tag, String content) {
    if (isDebug) {
      String log = getTraceInfo() + "  :  " + content;
      Log.i(tag, log);
    }
  }

  public static void printI(String content) {
    if (isDebug) {
      String log = getTraceInfo() + "  :  " + content;
      Log.i("", log);
    }
  }

  public static void printE(String tag, String content) {
    if (isDebug) {
      String log = getTraceInfo() + "  :  " + content;
      Log.e(tag, log);
    }
  }

  public static void printD(String tag, String content) {
    if (isDebug) {
      String log = getTraceInfo() + "  :  " + content;
      Log.d(tag, log);
    }
  }

  public static void printV(String tag, String content) {
    if (isDebug) {
      String log = getTraceInfo() + "  :  " + content;
      Log.v(tag, log);
    }
  }

  /** 获取堆栈信息 */
  private static String getTraceInfo() {
    StringBuffer sb = new StringBuffer();
    StackTraceElement[] stacks = new Throwable().getStackTrace();
    String className = stacks[2].getClassName();
    int index = className.lastIndexOf('.');
    if (index >= 0) {
      className = className.substring(index + 1);
    }
    String methodName = stacks[2].getMethodName();
    int lineNumber = stacks[2].getLineNumber();
    sb.append(className).append("->").append(methodName).append("()->").append(lineNumber);
    return sb.toString();
  }

  /**
   * 将相关日志信息写入本地文件，已解决log日志打印大字符串不完整的问题。 仅仅在debug可用的情况下写入。 如果文件已存在，则将文件删除在写入。相当于直接覆盖。
   *
   * @param log 要写的log
   * @param name 文件名
   */
  public static void saveStringToXml(String log, String name) {
    saveStringToFile(name, log, "xml");
  }

  /**
   * 保存日志到文本中
   *
   * @param name 文件名
   * @param log
   */
  public static void saveStringToTxt(String name, String log) {
    saveStringToFile(name, log, "txt");
  }

  /**
   * @param name 文件名称
   * @param log log 内容
   * @param suffix 文件后缀
   */
  public static void saveStringToFileBySuffix(String name, String log, String suffix, String path) {
    if (TextUtils.isEmpty(suffix)) {
      suffix = "txt";
    }
    if (TextUtils.isEmpty(log)) {
      return;
    }
    if (TextUtils.isEmpty(name)) {
      name = "log日志未命名";
    }
    FileService.saveStringToFile(path, log, name + "." + suffix);
  }
  /**
   * @param name 文件名称
   * @param log log 内容
   * @param suffix 文件后缀
   */
  public static void saveStringToFile(String name, String log, String suffix) {
    saveStringToFileBySuffix(name, log, suffix, DEBUG_PATH);
  }

  public static void checkDir(String dirName) {
    File dir = new File(dirName);
    if (!dir.exists()) {
      dir.mkdirs();
    }
  }
}
