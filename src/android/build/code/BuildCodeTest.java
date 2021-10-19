package android.build.code;

import org.junit.Test;

import opt.xml.to.code.NewActivityByXml;
import opt.xml.to.code.Viewstant;


public class BuildCodeTest {
    @Test
    public void generateDevicesActivity() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "view_tree.json";

        String androidAppPath = "/Users/randytang/AndroidStudioProjects/QuickDemoForWyze/app";
        String targetActivityPackageName = "com.wyze.app.ui.devices";
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

    @Test
    public void generateEventsActivity() {
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

    @Test
    public void generateCamPlusProSetUp() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "activity_cam_plus_pro_setup.json";

        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzeAppMainAndroid/app";
        String targetActivityPackageName = "com.wyze.cpp.randy.activity";
        String appPackageName = "com.hualai";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    @Test
    public void generateCamPlusProSetUpForHmsLibrary() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "activity_cam_plus_phone.json";

        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.abt.activity";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    @Test
    public void generateCamPlusProSetUpForEvent() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "activity_your_information.json";


//        cpp_title_bar_all.xml


        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }
}