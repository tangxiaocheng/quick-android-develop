package com.tangdengcheng.opt.xml.to.code;

import com.tangdengcheng.www.util.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import tangxiaocheng.log.StringUtil;

public class XmlModel {

  /** "#" */
  private static final String X_X = "#";
  /** "@" */
  public static final String S_S = "@";

  private static final boolean isAddCommonTitle = true;
  private boolean displayTotalIdName = false;
  private String xmlTag;
  private String listViewTag;
  private StringBuffer stringBuffer = new StringBuffer();
  private boolean addAndroidHead = true;
  private String json;
  private String xmlAbsoultPath;
  private String xmlPath;

  public XmlModel(String xmlJsonName, String xmlAbsoultPath, String xmlPath) {
    super();
    if (TextUtils.isEmpty(xmlJsonName)) {
      return;
    }
    this.xmlPath = xmlPath;
    this.xmlAbsoultPath = xmlAbsoultPath;
    json = FileService.readStringFromFile(Viewstant.PROJECT_FILE_PATH, xmlJsonName);
  }

  public XmlModel(JSONObject json, String xmlPath) {
    super();
    xmlAbsoultPath = xmlPath;
    this.json = json.toString();
  }

  public void initXml() {
    if (TextUtils.isEmpty(json)) {
      return;
    }
    JSONObject jsonObject;
    try {
      jsonObject = new JSONObject(json);
      optJson(jsonObject);
      //			String fileAbsoultPath = Viewstant.PROJECT_PATH+"/test.xml";
      FileService.saveStringToFile(xmlAbsoultPath, stringBuffer.toString());
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void optJson(JSONObject jsonObject) {
    for (@SuppressWarnings("unchecked") Iterator<String> iterator = jsonObject.keys();
        iterator.hasNext(); ) {
      String key = iterator.next();

      String viewType;
      String viewId;
      String llo = null;
      if (key.contains(S_S)) {
        String[] split = key.split(S_S);
        viewType = StringUtil.getFirstToUpperCase(split[0]);
        String factViewType = Viewstant.getViewByTt(viewType);
        if (viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.LINEAR_LAYOUT))
            || viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.RADIO_GROUP))) {
          if (split[1].contains(X_X)) {
            String[] llsplit = split[1].split(X_X);
            viewId = llsplit[0] + "" + (displayTotalIdName ? factViewType : viewType);
            llo = "h".equals(llsplit[1]) ? Viewstant.HORIZONTAL : Viewstant.VERTICAL;
          } else {
            viewId = split[1] + "" + (displayTotalIdName ? factViewType : viewType);
            llo = Viewstant.HORIZONTAL;
          }
        } else {
          viewId = split[1] + "" + (displayTotalIdName ? factViewType : viewType);
        }
      } else if (key.contains(X_X)) {

        String[] split = key.split(X_X);
        viewType = StringUtil.getFirstToUpperCase(split[0]);
        if (viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.LINEAR_LAYOUT))
            || viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.RADIO_GROUP))) {
          llo = "h".equals(split[1]) ? Viewstant.HORIZONTAL : Viewstant.VERTICAL;
        }
        viewId = split[0] + NewActivityByXml.FAKE_TAG;
      } else {
        viewType = StringUtil.getFirstToUpperCase(key);
        viewId = key + NewActivityByXml.FAKE_TAG;
      }
      String factViewType = Viewstant.getViewByTt(viewType);
      System.out.println(
          "viewType:"
              + viewType
              + " viewId:"
              + viewId
              + " llo:"
              + llo
              + " factViewType:"
              + factViewType);
      String viewTypeSource;
      if (addAndroidHead) {
        viewTypeSource =
            FileService.readStringFromFileAddXmlHead(
                Viewstant.PROJECT_FILE_PATH + "/" + factViewType + ".xml",
                Viewstant.ANDROID_XML_HEAD);

        addAndroidHead = !addAndroidHead;
      } else {
        viewTypeSource =
            FileService.readStringFromFile(Viewstant.PROJECT_FILE_PATH, factViewType + ".xml");
      }
      if (viewId.contains(NewActivityByXml.FAKE_TAG)) {
        String idLine =
            "        android:id="
                + Viewstant.SHUANG_YIN_HAO
                + "@+id/"
                + factViewType
                + "Id"
                + Viewstant.SHUANG_YIN_HAO
                + Viewstant.LINE_SEPARATOR;
        System.err.println(
            "viewId:" + viewId + "  ----    " + "idLine：" + idLine); // TODO将包含_fake的key替换成空格
        viewTypeSource = viewTypeSource.replace(idLine, "");
      } else {
        viewTypeSource = viewTypeSource.replace(factViewType + "Id", viewId);
      }
      if (viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.LINEAR_LAYOUT))
          || viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.RADIO_GROUP))) {
        if (null != llo) {
          viewTypeSource = viewTypeSource.replace("ORIENTATION", llo);
        }
      } else if (viewType.equalsIgnoreCase(Viewstant.getLitterCase(Viewstant.VIEW))) {

      }

      if (Viewstant.LIST_VIEW.equals(factViewType)
          || Viewstant.GRID_VIEW.equals(
              factViewType)) { // 如果实际view是listview，则将直接添加控件xml文件，将对应的JSONObject处理成 ListView
                               // item的xml元素
        stringBuffer.append(viewTypeSource).append(Viewstant.LINE_SEPARATOR);

        Object optJson = jsonObject.opt(key);
        if (null != optJson && optJson instanceof JSONObject) {
          String listItemXmlName = tangxiaocheng.log.StringUtil.getXmlName(viewId) + "_item";

          System.out.println("listview id = " + viewId);
          System.out.println(listItemXmlName);
          System.out.println();
          System.out.println();
          System.out.println();
          System.out.println();
          JSONObject new_name = (JSONObject) optJson;
          String itemXmlPath = xmlPath + "" + listItemXmlName + ".xml";
          System.err.println(itemXmlPath);
          XmlModel item = new XmlModel(new_name, itemXmlPath);
          item.initXml();
          //					optJsonListItem(new_name);
        }

      } else {
        Object optJson = jsonObject.opt(key);

        boolean isFatherLayout = optJson instanceof JSONObject;

        String target = "</" + factViewType + ">";
        boolean contains = viewTypeSource.contains(target);
        if (isFatherLayout) {
          if (contains) {
            viewTypeSource = viewTypeSource.replace(target, "");
          }
        }
        if (isFatherLayout) {
          stringBuffer.append(viewTypeSource).append(Viewstant.LINE_SEPARATOR);
          JSONObject new_name = (JSONObject) optJson;
          optJson(new_name);
          System.err.println(
              "----    optJson instanceof JSONObject ---->"
                  + "key:"
                  + key
                  + " ---> "
                  + new_name.toString());
        } else {
          System.err.println(
              "---- !! optJson instanceof JSONObject ---->"
                  + "key:"
                  + key
                  + " ---> "
                  + optJson.toString());
        }

        if (contains && isFatherLayout) {
          stringBuffer.append("    " + target).append(Viewstant.LINE_SEPARATOR);
        } else {
          stringBuffer.append(viewTypeSource).append(Viewstant.LINE_SEPARATOR);
        }
      }
    }
  }

  private void optJsonListItem(JSONObject new_name) {
    // TODO Auto-generated method stub

  }

  public String getXmlTag() {
    return xmlTag;
  }

  public void setXmlTag(String xmlTag) {
    this.xmlTag = xmlTag;
  }

  public void setListViewTag(String listViewTag) {
    this.listViewTag = listViewTag;
  }

  public void getListViewRoot() {}

  public static String getSubStringByTag(String string, String tag) {
    String listViewRootString = null;
    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(tag) && string.contains(tag)) {
      int indexOf = string.indexOf(tag);
      listViewRootString = string.substring(0, indexOf);
    }
    return listViewRootString;
  }
}
