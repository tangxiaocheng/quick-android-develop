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


    @Test
    public void generate_activity_phone_number() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
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
                        "activity_phone_number",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    @Test
    public void generate_activity_phone_number_exist() {
        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_phone_number_exist",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_cpp_pin_education.xml
    @Test
    public void generate_activity_cpp_pin_education() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cpp_pin_education",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_cpp_create_pin.xml
    @Test
    public void generate_activity_cpp_create_pin() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cpp_create_pin",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_safe_word_education.xml
    @Test
    public void generate_activity_safe_word_education() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_safe_word_education",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_set_up_safe_word.xml
    @Test
    public void generate_activity_set_up_safe_word() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_set_up_safe_word",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_cpp_verification_code.xml
    @Test
    public void generate_activity_cpp_verification_code() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cpp_verification_code",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_cpp_set_home_name.xml
    @Test
    public void generate_activity_cpp_set_home_name() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cpp_set_home_name",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }

    //activity_cam_plus_pro_setup.xml
    @Test
    public void generate_activity_cam_plus_pro_setup() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cam_plus_pro_setup",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }


    //activity_cpp_security_cameras.xml.xml
    @Test
    public void generate_activity_cpp_security_cameras() {

        System.out.println("run... ");
        System.out.println(Viewstant.PROJECT_FILE_PATH);

        String xmlJson = "";
        String androidAppPath = "/Users/randytang/WyzeAndroidCppTask/WyzePluginHmsAndroid";
        String targetActivityPackageName = "com.wyze.cpp.setup";
        String appPackageName = "com.wyze.hms";

        NewActivityByXml makeAndroidXml2Code =
                new NewActivityByXml(
                        androidAppPath,
                        true,
                        true,
                        targetActivityPackageName,
                        "activity_cpp_security_cameras",
                        appPackageName,
                        xmlJson, false);
        makeAndroidXml2Code.makeOnCreateCode();
    }
}