package android.build.code;

import opt.xml.to.code.NewActivityByXml;
import opt.xml.to.code.Viewstant;

public class BuildCode {
  public static void main(String[] args) {

    System.out.println("run... ");
    System.out.println(Viewstant.PROJECT_FILE_PATH);

    String xmlJson = "view_tree.json";

    String androidAppPath = "/Users/randy/workspace/Smartreading/app";
    String targetActivityPackageName = "com.ganluyuan.test";
    String appPackageName = "tdc.com.smartreading";

    NewActivityByXml makeAndroidXml2Code =
        new NewActivityByXml(
            androidAppPath, true, true, targetActivityPackageName, "activity_joke", appPackageName, xmlJson);
    makeAndroidXml2Code.setBaseActivityName("Activity");
    makeAndroidXml2Code.makeOnCreateCode();
  }
}
