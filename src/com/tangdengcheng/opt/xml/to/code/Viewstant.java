package com.tangdengcheng.opt.xml.to.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Viewstant {

  public static final String VIEW = "View";
  public static final String BUTTON = "Button";
  public static final String CHECK_BOX = "CheckBox";
  public static final String EDIT_TEXT = "EditText";
  public static final String GRID_VIEW = "GridView";
  public static final String IMAGE_VIEW = "ImageView";
  public static final String TOGGLE_BUTTON = "ToggleButton";
  public static final String LINEAR_LAYOUT = "LinearLayout";
  public static final String WEB_VIEW = "WebView";
  public static final String RELATIVE_LAYOUT = "RelativeLayout";
  public static final String SCROLL_VIEW = "ScrollView";
  public static final String RADIO_BUTTON = "RadioButton";
  public static final String RADIO_GROUP = "RadioGroup";
  public static final String TEXT_VIEW = "TextView";
  public static final String LIST_VIEW = "ListView";
  public static final String EXPANDABLE_LIST_VIEW = "ExpandableListView";

  public static final String ACTIVITY = "Activity";

  static class Import {

    public static final String IMPORT_ANDROID_WEBKIT = "import android.webkit.";
    public static final String IMPORT_ANDROID_WIDGET = "import android.widget.";
    public static final String IMPORT_ANDROID_VIEW = "import android.view.";
  }

  static final Map<String, String> ANDROID_VIEW_MAP =
      new HashMap<String, String>() {

        /** */
        private static final long serialVersionUID = 949320914180190522L;

        {
          put(VIEW, Import.IMPORT_ANDROID_VIEW + VIEW + ";");
          put(BUTTON, Import.IMPORT_ANDROID_WIDGET + BUTTON + ";");
          put(GRID_VIEW, Import.IMPORT_ANDROID_WIDGET + GRID_VIEW + ";");
          put(IMAGE_VIEW, Import.IMPORT_ANDROID_WIDGET + IMAGE_VIEW + ";");
          put(LINEAR_LAYOUT, Import.IMPORT_ANDROID_WIDGET + LINEAR_LAYOUT + ";");
          put(RELATIVE_LAYOUT, Import.IMPORT_ANDROID_WIDGET + RELATIVE_LAYOUT + ";");
          put(SCROLL_VIEW, Import.IMPORT_ANDROID_WIDGET + SCROLL_VIEW + ";");
          put(TEXT_VIEW, Import.IMPORT_ANDROID_WIDGET + TEXT_VIEW + ";");
          put(LIST_VIEW, Import.IMPORT_ANDROID_WIDGET + LIST_VIEW + ";");
          put(EXPANDABLE_LIST_VIEW, Import.IMPORT_ANDROID_WIDGET + EXPANDABLE_LIST_VIEW + ";");
          put(CHECK_BOX, Import.IMPORT_ANDROID_WIDGET + CHECK_BOX + ";");
          put(EDIT_TEXT, Import.IMPORT_ANDROID_WIDGET + EDIT_TEXT + ";");
          put(RADIO_GROUP, Import.IMPORT_ANDROID_WIDGET + RADIO_GROUP + ";");
          put(RADIO_BUTTON, Import.IMPORT_ANDROID_WIDGET + RADIO_BUTTON + ";");
          put(TOGGLE_BUTTON, Import.IMPORT_ANDROID_WIDGET + TOGGLE_BUTTON + ";");

          put(WEB_VIEW, Import.IMPORT_ANDROID_WEBKIT + WEB_VIEW + ";");
        }
      };

  public static void main(String[] args) {
    String path =
        "/Users/tangdengcheng/Documents/github_workplace/JavaBase/JavaUtil/src/com/tangdengcheng/opt/xml/to/code/";
    for (Iterator<String> iterator = ANDROID_VIEW_MAP.keySet().iterator(); iterator.hasNext(); ) {
      String type = iterator.next();
      //			String fileAbsoultPath = path + type + ".xml";
      //			File file = new File(fileAbsoultPath);
      //			if (!file.exists()) {
      //				FileService.saveStringToFile(fileAbsoultPath, ""+type);
      //			}
      String tt = getLitterCase(type);
      System.err.println(tt);
    }
  }

  public static String getViewByTt(String jianXie) {
    for (Iterator<String> iterator = ANDROID_VIEW_MAP.keySet().iterator(); iterator.hasNext(); ) {
      String type = iterator.next();
      String tt = getLitterCase(type);
      if (tt.equalsIgnoreCase(jianXie)) {
        return type;
      }
    }
    return null;
  }

  public static String getLitterCase(String type) {
    StringBuffer buffer = new StringBuffer(2);
    char[] charArray = type.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char ch = charArray[i];
      if (Character.isUpperCase(ch)) {
        buffer.append(ch);
      }
    }
    return buffer.toString().toLowerCase();
  }

  public static final String SHUANG_YIN_HAO = "\"";
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  public static final String ANDROID_XML_HEAD =
      " xmlns:android="
          + SHUANG_YIN_HAO
          + "http://schemas.android.com/apk/res/android"
          + SHUANG_YIN_HAO
          //			+ Viewstant.LINE_SEPARATOR
          //			+ "        xmlns:tools=" + SHUANG_YIN_HAO
          //			+ "http://schemas.android.com/tools" + SHUANG_YIN_HAO
          + "";
  public static final String PROJECT_FILE_PATH = System.getProperty("user.dir") + "/file";
  public static final String VERTICAL = "vertical";
  public static final String HORIZONTAL = "horizontal";
}
