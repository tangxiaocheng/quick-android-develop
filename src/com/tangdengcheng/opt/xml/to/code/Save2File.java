package com.tangdengcheng.opt.xml.to.code;

import tangxiaocheng.log.MyDebug;

public class Save2File {
  private StringBuffer stringBuffer = new StringBuffer();
  private String className;
  private String classPath;
  private String fileSuffix;

  Save2File(String name, String classPath, String fileSuffix) {
    super();
    this.className = name;
    this.classPath = classPath;
    this.fileSuffix = fileSuffix;
  }

  void println() {
    System.out.println();
    stringBuffer.append(Viewstant.LINE_SEPARATOR);
  }

  void println(String string) {
    stringBuffer.append(string);
    stringBuffer.append(Viewstant.LINE_SEPARATOR);
    System.out.println(string);
  }

  public String getString() {
    return stringBuffer.toString();
  }

  public void setStringBuffer(StringBuffer stringBuffer) {
    this.stringBuffer = stringBuffer;
  }

  public void saveStringToFile() {
    MyDebug.saveStringToFileBySuffix(className, getString(), fileSuffix, classPath);
  }
}
