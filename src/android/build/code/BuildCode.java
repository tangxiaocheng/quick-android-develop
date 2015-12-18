package android.build.code;

import com.tangdengcheng.opt.xml.to.code.NewActivityByXml;
import com.tangdengcheng.opt.xml.to.code.Viewstant;

public class BuildCode {
	public static void main(String[] args) {

		System.err.println(Viewstant.PROJECT_FILE_PATH);

		String xmlJson = "view_tree.json";

		String projectPath_ = "/Users/tangdengcheng/Documents/github_workplace/GanLuYuan";
		String classPackageName_ = "com.ganluyuan";
		String appPackage = "com.ganluyuan.android";

		NewActivityByXml makeAndroidXml2Code = new NewActivityByXml(
				projectPath_, true, true, classPackageName_, "activity_hello",
				appPackage, true, xmlJson);
		makeAndroidXml2Code.setBaseActivityName("Activity");
		makeAndroidXml2Code.makeOnCreateCode();

	}
}
