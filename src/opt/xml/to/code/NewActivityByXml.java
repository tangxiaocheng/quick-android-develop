package opt.xml.to.code;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import opt.xml.util.TextUtils;
import tangxiaocheng.log.StringUtil;

public class NewActivityByXml {

    public static final String FAKE_TAG = "_fake";
    private static final String NULL_STRING_2 = "	"; // "		"

    private static final String NULL_STRING_1 = "	"; // "	"
    private static final String XML_SUFFIX = ".xml";
    private static final String VIEW_HOLDER_CLASS_NAME = "ViewHolder";
    private static final String CONVERT_VIEW = "convertView";
    public static final String ON_WYZE_ITEM_CLICK_LISTENER = "OnWyzeItemClickListener";
    private  String fileSuffix = "java";
    private  String END_SYNTAX = ";" ;
    private boolean initMapSuccessful = true;
    private String classPath;
    private String xmlPath;
    private String xmlName;
    //	private   String projectPath ;
    private boolean isMakeOnClickListenerForActivity = false;
    private boolean isMakeSetText = true;
    private String classPackageName;
    private String appPackageName;
    private Save2File activitySystemOut;
    private String activityName;
    private LinkedHashMap<String, String> idViewMap;
    private List<String> viewList;
    private String manifestPath;
    private String baseActivityName;
    private String projectPath;
    private XmlModel xmlModel;
    private boolean isSetOnItemClick = false;
    private boolean hasSwipeRefreshLayout = false;

    public NewActivityByXml(
            String projectPath,
            boolean isMakeOnClickListenerForActivity,
            boolean isMakeSetText,
            String classPackageName,
            String layoutFileName,
            String appPackageName,
            String xmlJson, boolean kotlin) {
        super();
        if (kotlin) {
            END_SYNTAX = "";
            fileSuffix = "kt";
        } else {
            END_SYNTAX = ";";
            fileSuffix = "java";
        }
        this.projectPath = projectPath;
        if (TextUtils.isEmpty(layoutFileName)) {
            System.err.println("XML_NAME==null return");
            layoutFileName = xmlJson.substring(0,xmlJson.length()-5);
        } else {
            // TODO
        }
        this.isMakeOnClickListenerForActivity = isMakeOnClickListenerForActivity;
        this.isMakeSetText = isMakeSetText;
        this.classPackageName = classPackageName;
        this.appPackageName = appPackageName;
        this.xmlName = layoutFileName;

        activityName = StringUtil.getActivityName(layoutFileName.replace("activity_", "") + "_activity");

        manifestPath = projectPath + "/src/main/" + "AndroidManifest.xml";
        xmlPath = projectPath + "/src/main/res/layout/";
        classPath = projectPath + "/src/main/java/";

        activitySystemOut =
                new Save2File(activityName, classPath + classPackageName.replace(".", "/") + "/", fileSuffix);
        String xmlAbsolutePath = xmlPath + xmlName + ".xml";

        if (!TextUtils.isEmpty(xmlJson)) {
            this.xmlModel = new XmlModel(xmlJson, xmlAbsolutePath, xmlPath);
            xmlModel.initXml();
        } else {

            StringBuilder buffer = new StringBuilder();

            if (!new File(xmlAbsolutePath).exists()) {
                String fileAbsolutePath = Viewstant.PROJECT_FILE_PATH + "/LinearLayoutTop.xml";
                String readStringFromFile =
                        FileService.readStringFromFileAddXmlHead(fileAbsolutePath, Viewstant.ANDROID_XML_HEAD);
                buffer.append(readStringFromFile);
                System.out.println(buffer.toString());
                FileService.saveStringToFile(xmlAbsolutePath, buffer.toString());
            }
        }
        initMap(xmlAbsolutePath);
    }

    /**
     * 根据ID在Activity onCreate 方法中生成findViewById的方法以及对应的成员变量
     */
    public void makeOnCreateCode() {

        activitySystemOut =
                new Save2File(activityName, classPath + classPackageName.replace(".", "/") + "/", fileSuffix);

        if (!initMapSuccessful) {
            System.out.println("initMapSuccessFull fail , please check");
            return;
        }

        if (!xmlName.contains("activity")) {
            System.err.println("//请检查是否是生成activity的代码..");
            System.err.println();
        }

        activitySystemOut.println("package " + classPackageName + END_SYNTAX);
        activitySystemOut.println();

        boolean needSetOnClickListener = false;
        for (String view : viewList) {
            needSetOnClickListener = isNeedSetOnClickListener(view);
            if (needSetOnClickListener) {
                activitySystemOut.println("import android.view.View.OnClickListener" + END_SYNTAX);
                if (!viewList.contains(Viewstant.VIEW)) {
                    viewList.add(Viewstant.VIEW);
                }
                break;
            }
        }

        if (TextUtils.isEmpty(baseActivityName)
                || baseActivityName.equals(Viewstant.ACTIVITY)) { // TODO 添加默认的 base activity （完整路径）
            baseActivityName = "AppCompatActivity";
            activitySystemOut.println("import androidx.appcompat.app.AppCompatActivity" + END_SYNTAX);
        } else {
            // TODO 导入baseActivity的包。需要设置
        }

        for (String string : viewList) {
            if (Viewstant.ANDROID_VIEW_MAP.containsKey(string)) {
                activitySystemOut.println(Viewstant.ANDROID_VIEW_MAP.get(string)+END_SYNTAX);
            } else {
                System.err.println("//请添加" + string);
            }
        }
        activitySystemOut.println();

        activitySystemOut.println("import " + appPackageName + ".R" + END_SYNTAX);
        activitySystemOut.println("import android.os.Bundle" + END_SYNTAX);

        String listViewId = null;

        if (viewList.contains(Viewstant.LIST_VIEW)
                || viewList.contains(Viewstant.GRID_VIEW)
                || viewList.contains(Viewstant.RECYCLER_VIEW)
        ) { // 新建adapter.java 文件，import adapter 和 model 类
            for (String id : idViewMap.keySet()) {
                String string = idViewMap.get(id);
                if (Viewstant.LIST_VIEW.equals(string)
                        || Viewstant.GRID_VIEW.equals(string)
                        || Viewstant.RECYCLER_VIEW.equals(string)
                        || string.contains(Viewstant.GRID_VIEW)
                        || string.contains(Viewstant.RECYCLER_VIEW)
                        || string.contains(Viewstant.LIST_VIEW)) {
                    listViewId = id;
                    break; // 仅仅取第一个LIST_VIEW或者GRID_VIEW控件
                }
            }
        }
        String adapterClassName = null;
        String modelClassName = null;
        String itemXmlName = null;
        if (!TextUtils.isEmpty(listViewId)) {

            activitySystemOut.println("import java.util.ArrayList" + END_SYNTAX);
            activitySystemOut.println("import java.util.List" + END_SYNTAX);
            activitySystemOut.println("import android.widget.ImageView" + END_SYNTAX);
            activitySystemOut.println("import android.widget.TextView" + END_SYNTAX);

            activitySystemOut.println("import androidx.recyclerview.widget.GridLayoutManager" + END_SYNTAX);
            adapterClassName = StringUtil.changeFirstToUpper(listViewId) + "Adapter";
            modelClassName = StringUtil.changeFirstToUpper(listViewId) + "Model";
            itemXmlName = StringUtil.getXmlName(listViewId) + "_item";
            System.err.println(
                    activityName + "-----" + adapterClassName + "----" + modelClassName + "-----" + itemXmlName);
            String adapterPackageName = classPackageName.replace("activity", "adapter");
            String modelPackageName = classPackageName.replace("activity", "model");

            System.err.println(adapterPackageName + "---" + modelPackageName);

            activitySystemOut.println("import " + adapterPackageName + "." + adapterClassName + END_SYNTAX);
            activitySystemOut.println("import " + modelPackageName + "." + modelClassName + END_SYNTAX);
            isSetOnItemClick = viewList.contains("RecyclerView")||viewList.contains("ListView");
            if (isSetOnItemClick){
                activitySystemOut.println("import " + modelPackageName + "." + ON_WYZE_ITEM_CLICK_LISTENER + END_SYNTAX);
            }


            // 判断xml文件是否存在，如已经存在，则不在生产 ListView item xml 文件

            String pathname = xmlPath + itemXmlName + ".xml";

            File item_xml = new File(pathname);
            if (item_xml.exists()) {
                System.out.println(
                        "ListView item : -------------------------------------> " + item_xml.getAbsolutePath());
            } else {
                Save2File xmlSystemOut = new Save2File(itemXmlName, xmlPath, "xml");
                xmlSystemOut.println(
                        "<RelativeLayout xmlns:android="
                                + Viewstant.SHUANG_YIN_HAO
                                + "http://schemas.android.com/apk/res/android"
                                + Viewstant.SHUANG_YIN_HAO);
                xmlSystemOut.println(
                        "    xmlns:tools="
                                + Viewstant.SHUANG_YIN_HAO
                                + "http://schemas.android.com/tools"
                                + Viewstant.SHUANG_YIN_HAO);
                xmlSystemOut.println(
                        "    android:layout_width="
                                + Viewstant.SHUANG_YIN_HAO
                                + "match_parent"
                                + Viewstant.SHUANG_YIN_HAO);
                xmlSystemOut.println(
                        "    android:layout_height="
                                + Viewstant.SHUANG_YIN_HAO
                                + "wrap_content"
                                + Viewstant.SHUANG_YIN_HAO
                                + " >");
                xmlSystemOut.println("</RelativeLayout>");
                xmlSystemOut.saveStringToFile();
            }

            NewActivityByXml adapterByXml =
                    new NewActivityByXml(
                            projectPath,
                            needSetOnClickListener,
                            needSetOnClickListener,
                            modelClassName,
                            itemXmlName,
                            adapterPackageName,
                            null, false);

            String listItemFindViewCode = adapterByXml.makeGetViewCode(xmlPath, itemXmlName, ".xml", modelClassName);

            createModelClass(modelClassName, modelPackageName);

            // adapterSystemOut 需要更具xml文件生产adapter
            Save2File adapterSystemOut =
                    new Save2File(
                            adapterClassName, classPath + adapterPackageName.replace(".", "/") + "/", fileSuffix);

            adapterSystemOut.println("package " + adapterPackageName + END_SYNTAX);
            adapterSystemOut.println();
            adapterSystemOut.println("import java.util.List" + END_SYNTAX);
            adapterSystemOut.println();
            adapterSystemOut.println("import android.content.Context" + END_SYNTAX);
            adapterSystemOut.println("import androidx.annotation.NonNull" + END_SYNTAX);
            adapterSystemOut.println("import android.view.LayoutInflater" + END_SYNTAX);
            adapterSystemOut.println("import android.view.View" + END_SYNTAX);
            adapterSystemOut.println("import android.view.ViewGroup" + END_SYNTAX);
            adapterSystemOut.println("import android.widget.AdapterView" + END_SYNTAX);
            adapterSystemOut.println("import androidx.recyclerview.widget.RecyclerView" + END_SYNTAX);
            adapterSystemOut.println("import android.widget.Button" + END_SYNTAX);

            adapterSystemOut.println();
            adapterSystemOut.println("import android.widget.ImageView" + END_SYNTAX);
            adapterSystemOut.println("import android.widget.CheckBox" + END_SYNTAX);
            adapterSystemOut.println("import android.widget.TextView" + END_SYNTAX);
            adapterSystemOut.println("import " + appPackageName + ".R" + END_SYNTAX);
            adapterSystemOut.println("import " + modelPackageName + "." + modelClassName + END_SYNTAX);


            if(isSetOnItemClick){
                adapterSystemOut.println("import " + modelPackageName + "." + ON_WYZE_ITEM_CLICK_LISTENER + END_SYNTAX);
            }
            if (isSetOnItemClick) {
                Save2File wyzwClickInterface =
                        new Save2File(
                                ON_WYZE_ITEM_CLICK_LISTENER, classPath + modelPackageName.replace(".", "/") + "/", fileSuffix);
                wyzwClickInterface.println("package " + modelPackageName + END_SYNTAX);
                wyzwClickInterface.println();
                wyzwClickInterface.println("public interface " + ON_WYZE_ITEM_CLICK_LISTENER + "<T>"  + "{");
                wyzwClickInterface.println("");
                wyzwClickInterface.println(NULL_STRING_1+ "void onItemClicked(T t, int position)" + END_SYNTAX);
                wyzwClickInterface.println("");

                wyzwClickInterface.println("}");
                wyzwClickInterface.saveStringToFile();
            }
            adapterSystemOut.println();
            adapterSystemOut.println(
                    "public class "
                            + adapterClassName
                            + " extends RecyclerView.Adapter<" +adapterClassName+".ViewHolder"+ ">  " +
                            " {");
            adapterSystemOut.println();
            adapterSystemOut.println(NULL_STRING_2 + "private List" +
                    "<" + modelClassName + ">" +
                    "adapterList" + END_SYNTAX);
            final String onItemClickListenerStr = Introspector.decapitalize(ON_WYZE_ITEM_CLICK_LISTENER);
            adapterSystemOut.println(NULL_STRING_2 + "private " + ON_WYZE_ITEM_CLICK_LISTENER + "" + "<" + modelClassName + ">" +
                    " " + onItemClickListenerStr + END_SYNTAX);
            adapterSystemOut.println();

            adapterSystemOut.println(
                    NULL_STRING_2
                            + "public "
                            + adapterClassName
                            + "(" + ON_WYZE_ITEM_CLICK_LISTENER + "" + "<" + modelClassName + ">" +
                            " " + onItemClickListenerStr + ",List<"
                            + modelClassName
                            + "> adapterList"
                            + ") {");
            adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "this." + onItemClickListenerStr + " = "+onItemClickListenerStr+ END_SYNTAX);
            adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "this.adapterList = adapterList" + END_SYNTAX);

            adapterSystemOut.println(NULL_STRING_2 + "}");
            adapterSystemOut.println();

            adapterSystemOut.println(NULL_STRING_2 + "@Override");
            adapterSystemOut.println(NULL_STRING_2 + "public int getItemCount() {");
            adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "return adapterList.size()" + END_SYNTAX);
            adapterSystemOut.println(NULL_STRING_2 + "}");


            adapterSystemOut.println();
            adapterSystemOut.println(NULL_STRING_2 + "@NonNull");
            adapterSystemOut.println(NULL_STRING_2 + "@Override");
            adapterSystemOut.println(NULL_STRING_2 + "public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {");
            adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "View view = LayoutInflater.from(parent.getContext()).inflate(R.layout." + itemXmlName + ", parent, false)" + END_SYNTAX);
            adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "return new ViewHolder(view)" + END_SYNTAX);
            adapterSystemOut.println(NULL_STRING_2 + "}");
            adapterSystemOut.println();


            adapterSystemOut.println();
            adapterSystemOut.println(NULL_STRING_2 + "@Override");
            adapterSystemOut.println(NULL_STRING_2 + "public void onBindViewHolder(@NonNull ViewHolder holder, int position) {");
          adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "final " + modelClassName + " itemModel = adapterList.get(position)" + END_SYNTAX);
          adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "holder.bindData(itemModel)" + END_SYNTAX);
          adapterSystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "holder.itemView.setOnClickListener(v -> " + onItemClickListenerStr + ".onItemClicked(itemModel, position))" + END_SYNTAX);


            adapterSystemOut.println(NULL_STRING_2 + "}");

            adapterSystemOut.println(listItemFindViewCode);

            adapterSystemOut.saveStringToFile();
        }


        hasSwipeRefreshLayout = viewList.contains("SwipeRefreshLayout");

        activitySystemOut.println();
        String firstClassLineString =
                "public  class "
                        + activityName
                        + " extends  "
                        + baseActivityName
                        + (needSetOnClickListener ? " implements OnClickListener" : "")
                        + (isSetOnItemClick ? ", " + ON_WYZE_ITEM_CLICK_LISTENER + "<" +modelClassName+ ">" : "")
                        + (hasSwipeRefreshLayout ? ", SwipeRefreshLayout.OnRefreshListener" : "")
                        + " {";
        activitySystemOut.println(firstClassLineString);

        for (String id : idViewMap.keySet()) {
            activitySystemOut.println(NULL_STRING_1 + "private " + idViewMap.get(id) + " " + id + END_SYNTAX);
        }
        activitySystemOut.println();

        activitySystemOut.println(NULL_STRING_1 + "@Override");
        activitySystemOut.println(
                NULL_STRING_2 + "protected void onCreate(Bundle savedInstanceState) {");
        activitySystemOut.println(
                NULL_STRING_2 + NULL_STRING_1 + "super.onCreate(savedInstanceState)" + END_SYNTAX);
        activitySystemOut.println(
                NULL_STRING_2 + NULL_STRING_1 + "initView(R.layout." + xmlName + ")" + END_SYNTAX);

        activitySystemOut.println(NULL_STRING_2 + "}");

        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_1 + "private void initView(" + "int layoutId" + "){");
        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_1+NULL_STRING_1+"setContentView(layoutId)" + END_SYNTAX);
        activitySystemOut.println();

        for (Iterator<String> iterator = idViewMap.keySet().iterator(); iterator.hasNext(); ) {
            String id = iterator.next();
            String string = idViewMap.get(id);
            activitySystemOut.println(
                    NULL_STRING_2+NULL_STRING_1 + id + " = findViewById(R.id." + id + ")" + END_SYNTAX);
            if (Viewstant.LIST_VIEW.equals(string)
                    || Viewstant.GRID_VIEW.equals(string)
                    || Viewstant.RECYCLER_VIEW.equals(string)
                    || string.contains(Viewstant.GRID_VIEW)
                    || string.contains(Viewstant.RECYCLER_VIEW)
                    || string.contains(Viewstant.LIST_VIEW)) {
                activitySystemOut.println();
                activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ "List<" + modelClassName + "> adapterList =new ArrayList<" + modelClassName + ">()" + END_SYNTAX);

                activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ "for (int i = 0; i < 30; i++) {");
                activitySystemOut.println(NULL_STRING_2+NULL_STRING_1+NULL_STRING_1 + " adapterList.add(new " +modelClassName +
                        "(\"ds\" + i))" + END_SYNTAX);
                activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + "}");

                activitySystemOut.println(
                        NULL_STRING_2+NULL_STRING_1
                                + adapterClassName
                                + " adapter = new "
                                + adapterClassName
                                + "(this,adapterList)" + END_SYNTAX);
                activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + id + ".setAdapter(" + "adapter" + ")" + END_SYNTAX);
                activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + id + ".setLayoutManager(new GridLayoutManager(this,1))" + END_SYNTAX);


            }
        }
        List<String> listenerList = new ArrayList<String>();
        if (isMakeOnClickListenerForActivity && needSetOnClickListener) { // 是否开启监听&&存在监听控件
            activitySystemOut.println();
            for (String key : idViewMap.keySet()) {
                String string = idViewMap.get(key);
                if (isNeedSetOnClickListener(string)) {
                    activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + key + ".setOnClickListener(this)" + END_SYNTAX);
                    listenerList.add(key);
                }
            }
        }
        if (isMakeSetText) {
            activitySystemOut.println();
            for (String key : idViewMap.keySet()) {
                if (Viewstant.TEXT_VIEW.equals(idViewMap.get(key))) {
                    activitySystemOut.println(
                            NULL_STRING_2
                                    + key
                                    + ".setText("
                                    + Viewstant.SHUANG_YIN_HAO
                                    + Viewstant.SHUANG_YIN_HAO
                                    + ")" + END_SYNTAX);
                }
            }
        }

        activitySystemOut.println(NULL_STRING_1 + "}");

        if (needSetOnClickListener) {
            activitySystemOut.println();
            activitySystemOut.println(NULL_STRING_1 + "@Override");
            activitySystemOut.println(NULL_STRING_1 + "public void onClick(View v) {");
            if (isMakeOnClickListenerForActivity) {
                activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + "int id = v.getId()" + END_SYNTAX);
                int count = 0;
                for (String string : listenerList) {
                    activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ (count==0?"":"else ")+"if(id == R.id." +string+
                            "){");
                    activitySystemOut.println();
                    activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1 + "}");
                    count++;
                }
            }
            activitySystemOut.println(NULL_STRING_1 + "}");
        }

        if (isSetOnItemClick) {
            activitySystemOut.println();
            activitySystemOut.println(NULL_STRING_1 + "@Override");
            activitySystemOut.println(NULL_STRING_1 + "public void onItemClicked(" +modelClassName+
                    " v, int position) {");

            activitySystemOut.println();
            activitySystemOut.println(NULL_STRING_1 + "}");
        }

        if (hasSwipeRefreshLayout) {
            activitySystemOut.println();
            activitySystemOut.println(NULL_STRING_1 + "@Override");
            activitySystemOut.println(NULL_STRING_1 + "public void onRefresh() {");

            activitySystemOut.println();
            activitySystemOut.println(NULL_STRING_1 + "}");
        }

        activitySystemOut.println("}");

        activitySystemOut.saveStringToFile();
        addManifestTag();
    }

    private void createModelClass(String modelClassName, String modelPackageName) {
        Save2File modelSystemOut =
                new Save2File(
                        modelClassName, classPath + modelPackageName.replace(".", "/") + "/", fileSuffix);
        modelSystemOut.println("package " + modelPackageName + END_SYNTAX);
        modelSystemOut.println();
        modelSystemOut.println("public class " + modelClassName + "{");
        modelSystemOut.println();
        modelSystemOut.println(NULL_STRING_1+"public String strField1" + END_SYNTAX);
        modelSystemOut.println();

        modelSystemOut.println(NULL_STRING_1+"public " + modelClassName +
                "(String strField1) {");
        modelSystemOut.println(NULL_STRING_1 + NULL_STRING_2+"this.strField1 = strField1" + END_SYNTAX);
        modelSystemOut.println(NULL_STRING_1+"}");
        modelSystemOut.println();

        modelSystemOut.println("}");
        modelSystemOut.saveStringToFile();
    }

    private boolean isNeedSetOnClickListener(String string) {
        return Viewstant.BUTTON.equals(string)
                || Viewstant.IMAGE_VIEW.equals(string)
                || Viewstant.LINEAR_LAYOUT.equals(string)
                || Viewstant.RELATIVE_LAYOUT.equals(string)
                || Viewstant.VIEW.equals(string);
    }

    private void initMap(String xmlAbsolutePath) {
        idViewMap = new LinkedHashMap<>();
        viewList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        String lastLine = "";
        try {
            System.out.println("xmlAbsolutePath：" + xmlAbsolutePath);
            bufferedReader = new BufferedReader((new FileReader(xmlAbsolutePath)));
            while (bufferedReader.read() != -1) {
                String readLineTemp = bufferedReader.readLine();
                if (readLineTemp != null) {
                    final String readLine = readLineTemp.trim();
                    if (!readLine.contains("<!--") && !readLine.contains("-->")) { // 过滤注释行
                        if (readLine.contains("android:id=\"@+id/") && !readLine.contains(FAKE_TAG)) {
                            String key = readLine.replace("android:id=\"@+id/", "").replace(Viewstant.SHUANG_YIN_HAO, "");
                            if (lastLine.contains("<") && !lastLine.contains("android:")) { // xml文件头存在id的上一行不是对应的布局方式
                                String value = lastLine.replace("<", "");
                                System.out.println("valuevaluevaluevaluevalue:"+value);
                                if (value.contains("androidx")){
                                    final String[] split = value.split("\\.");
                                    value = split[split.length-1];
                                }
                                idViewMap.put(key, value);
                                if (!viewList.contains(value)) {
                                    viewList.add(value);
                                }
                            }
                        }
                        lastLine = readLine;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            initMapSuccessful = false;
            e.printStackTrace();
        }

        System.out.println("idViewMap:---->"+idViewMap);
        System.out.println("viewList:---->"+viewList);
    }

    /**
     * 在BaseAdapter类中，根据指定的xml布局文件生成对应的findViewById方法以及对应的ViewHolder
     */
    public String makeGetViewCode(String name, String classPath, String fileSuffix, String modelClassName) {

        Save2File activitySystemOut = new Save2File(name, classPath, fileSuffix);
        activitySystemOut.println();
        activitySystemOut.println(
                NULL_STRING_2  + "final static class " + VIEW_HOLDER_CLASS_NAME + " extends RecyclerView.ViewHolder { ");
        activitySystemOut.println();
        for (String id : idViewMap.keySet()) {
            activitySystemOut.println(NULL_STRING_2 + NULL_STRING_1 + "private final " + idViewMap.get(id) + " " + id + END_SYNTAX);
        }

        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ "public ViewHolder(@NonNull View itemView) {");
        activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+NULL_STRING_1+ "super(itemView)" + END_SYNTAX);

        final String firstString = new String(new char[]{VIEW_HOLDER_CLASS_NAME.charAt(0)});
        final String classObject =
                firstString.toLowerCase()
                        + VIEW_HOLDER_CLASS_NAME.substring(1, VIEW_HOLDER_CLASS_NAME.length());

        for (Iterator<String> iterator = idViewMap.keySet().iterator(); iterator.hasNext(); ) {
            String id = iterator.next();
            activitySystemOut.println(
                    NULL_STRING_2+NULL_STRING_1+NULL_STRING_1
                            + id
                            + " = "
                            + "itemView"
                            + "."
                            + "findViewById(R.id."
                            + id
                            + ")" + END_SYNTAX);
        }
        activitySystemOut.println(NULL_STRING_2+NULL_STRING_1 + "}");

        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ "public void bindData(@NonNull " + modelClassName+
                " itemModel) {");

        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1+ NULL_STRING_2+"timeTv.setText(itemModel.strField1"+")" + END_SYNTAX);

        activitySystemOut.println();
        activitySystemOut.println(NULL_STRING_2 +NULL_STRING_1 + "}");

        activitySystemOut.println();


        activitySystemOut.println(NULL_STRING_2 + "}");
        activitySystemOut.println("}");
        //		activitySystemOut.saveStringToFile();
        return activitySystemOut.getString();
    }

    private void addManifestTag() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            System.err.println("manifestPath:" + manifestPath);
            File file = new File(manifestPath);
            if (file.isFile()) {
                System.out.println("file is exist");
                StringBuffer buffer = new StringBuffer();
                bufferedReader = new BufferedReader(new FileReader(file));
                String readLine;
                String currentActivity =
                        "android:name="
                                + Viewstant.SHUANG_YIN_HAO
                                + classPackageName
                                + "."
                                + activityName
                                + Viewstant.SHUANG_YIN_HAO;
                while ((readLine = bufferedReader.readLine()) != null) {
                    if (readLine.contains(currentActivity)) {
                        System.err.println("清单已经注册，直接return");
                        return;
                    }
                    if (!readLine.contains("</application>") && !readLine.contains("</manifest>")) {
                        buffer
                                .append(readLine)
                                .append(Viewstant.LINE_SEPARATOR);
                    }
                }
                bufferedReader.close();
                buffer.append(
                        "        <activity android:screenOrientation=" + Viewstant.SHUANG_YIN_HAO + "fullSensor"
                                + Viewstant.SHUANG_YIN_HAO + "  ").append(currentActivity).append(" >");
                //				buffer.append("        <activity " + currentActivity + " >");
                buffer.append(Viewstant.LINE_SEPARATOR);
                buffer.append("        </activity>");
                buffer.append(Viewstant.LINE_SEPARATOR);
                buffer.append("    </application>");
                buffer.append(Viewstant.LINE_SEPARATOR);
                buffer.append(Viewstant.LINE_SEPARATOR);
                buffer.append("</manifest>");
                buffer.append(Viewstant.LINE_SEPARATOR);
                String string = buffer.toString();

                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.append(string);

            } else {
                System.out.println("file is not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bufferedWriter) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBaseActivityName() {
        return baseActivityName;
    }

    public void setBaseActivityName(String baseActivityName) {
        this.baseActivityName = baseActivityName;
    }
}
