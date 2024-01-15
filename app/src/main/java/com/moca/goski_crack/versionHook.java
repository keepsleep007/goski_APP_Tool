package com.moca.goski_crack;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class versionHook {
    private static final String TAG = "goskicrack";
    public static Map<String, String> versionMap = new HashMap<String, String>();
    // 4.3.7
    private static String vkr_Class_Name_4_3_7 = "com.goski.sharecomponent.viewmodel.SkiFieldPhotoTimeLineViewModel$a";
    private static String vkr_method_Name_4_3_7 = "onSuccess";
    private static String viewConstructor_4_3_7 = "com.goski.sharecomponent.viewmodel.o0";

    // 4.3.8
    private static String vkr_Class_Name_4_3_8 = "com.goski.sharecomponent.viewmodel.SkiFieldPhotoTimeLineViewModel$a";
    private static String vkr_method_Name_4_3_8 = "onSuccess";
    private static String viewConstructor_4_3_8 = "com.goski.sharecomponent.viewmodel.n0";

    // 4.4.1
    private static String vkr_Class_Name_4_4_1 = "com.goski.sharecomponent.viewmodel.SkiFieldPhotoTimeLineViewModel$a";
    private static String vkr_method_Name_4_4_1 = "onSuccess";
    private static String viewConstructor_4_4_1 = "com.goski.sharecomponent.viewmodel.n0";

    public versionHook() {
        versionMap.put("vkr_Class_Name_4.3.7", vkr_Class_Name_4_3_7);
        versionMap.put("vkr_method_Name_4.3.7", vkr_method_Name_4_3_7);
        versionMap.put("viewConstructor_4.3.7", viewConstructor_4_3_7);

        versionMap.put("vkr_Class_Name_4.3.8", vkr_Class_Name_4_3_8);
        versionMap.put("vkr_method_Name_4.3.8", vkr_method_Name_4_3_8);
        versionMap.put("viewConstructor_4.3.8", viewConstructor_4_3_8);

        versionMap.put("vkr_Class_Name_4.4.1", vkr_Class_Name_4_4_1);
        versionMap.put("vkr_method_Name_4.4.1", vkr_method_Name_4_4_1);
        versionMap.put("viewConstructor_4.4.1", viewConstructor_4_4_1);

        Log.v(TAG, "versionHook构造结束");
    }
}
