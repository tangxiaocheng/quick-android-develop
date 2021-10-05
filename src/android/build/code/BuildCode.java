package android.build.code;

import opt.xml.to.code.NewActivityByXml;
import opt.xml.to.code.Viewstant;

public class BuildCode {
    public static void main(String[] args) {
        generateDevicesActivity();
    }

    public static void generateDevicesActivity() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "view_tree.json";

        String androidAppPath = "/Users/randytang/AndroidStudioProjects/QuickDemoForWyze/app";
        String targetActivityPackageName = "com.wyze.app.ui";
        String appPackageName = "com.wyze.app";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_devices",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    public static void generateEventsActivity() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "event_view_tree.json";

        String androidAppPath = "/Users/randytang/AndroidStudioProjects/QuickDemoForWyze/app";
        String targetActivityPackageName = "com.wyze.app.ui.events";
        String appPackageName = "com.wyze.app";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_events",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }
}
