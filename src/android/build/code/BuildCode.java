package android.build.code;

import com.tangdengcheng.opt.xml.to.code.NewActivityByXml;
import com.tangdengcheng.opt.xml.to.code.Viewstant;

public class BuildCode {
  public static void main(String[] args) {

    System.out.println("run... ");
    System.err.println(Viewstant.PROJECT_FILE_PATH);

    String xmlJson = "view_tree.json";

    String projectPath_ = "/Users/randy/workspace/Smartreading/app";
    String classPackageName_ = "com.ganluyuan.test";
    String appPackage = "tdc.com.smartreading";

    NewActivityByXml makeAndroidXml2Code =
        new NewActivityByXml(
            projectPath_, true, true, classPackageName_, "activity_hello", appPackage, xmlJson);
    makeAndroidXml2Code.setBaseActivityName("Activity");
    makeAndroidXml2Code.makeOnCreateCode();
  }
}
