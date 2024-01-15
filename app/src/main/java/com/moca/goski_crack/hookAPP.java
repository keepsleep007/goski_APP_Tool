package com.moca.goski_crack;

import android.content.pm.ApplicationInfo;
import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class hookAPP implements IXposedHookLoadPackage {
    private static final String TAG = "goskicrack";

    String photodata;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        Log.v(TAG, "=========================" + lpparam.processName);
        ApplicationInfo appInfo = lpparam.appInfo;
        Log.v(TAG, "className===" + appInfo.className);
        Log.v(TAG, "dataDir===" + appInfo.dataDir);
        Log.v(TAG, "name===" + appInfo.name);
        Log.v(TAG, "processName===" + appInfo.processName);
        Log.v(TAG, "sourceDir===" + appInfo.sourceDir);
        Log.v(TAG, "===========================");

        // 判断hook的程序
        if (lpparam.processName.equals("com.goski.gosking")) {
            ClassLoader classLoader = lpparam.classLoader;

            XposedHelpers.findAndHookMethod("com.goski.goskibase.basebean.version.VersionInfo", classLoader, "getVer", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    String goskiVersion = (String) param.getResult();
                    Log.v(TAG, "getVer: " + goskiVersion);
                    if (goskiVersion != null && goskiVersion.length() > 0) {
                        new versionHook();
                        String vkr_Class_Name = "vkr_Class_Name_" + goskiVersion;
                        String vkr_method_Name = "vkr_method_Name_" + goskiVersion;
                        String viewConstructor = "viewConstructor_" + goskiVersion;

                        Log.v(TAG, "vkr_Class_Name: " + versionHook.versionMap.get(vkr_Class_Name));
                        Log.v(TAG, "vkr_method_Name_: " + versionHook.versionMap.get(vkr_method_Name));
                        Log.v(TAG, "viewConstructor_: " + versionHook.versionMap.get(viewConstructor));

                        // hook
                        Class<?> vkr = XposedHelpers.findClassIfExists(versionHook.versionMap.get(vkr_Class_Name), classLoader);
                        if (vkr == null) {
                            Log.v(TAG, "====class Not Found====");
                        } else {
                            Log.v(TAG, "====class load ok====");
                            // 查找并且hook
                            Class baseResp = classLoader.loadClass("com.goski.goskibase.basebean.BaseResp");
                            XposedHelpers.findAndHookMethod(versionHook.versionMap.get(vkr_Class_Name), classLoader, versionHook.versionMap.get(vkr_method_Name), baseResp, new XC_MethodHook() {
                                // final Class<?> SkiFieldDetailPhoto = XposedHelpers.findClass("com.goski.goskibase.basebean.circle.SkiFieldDetailPhoto", classLoader);

                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Object arg = param.args[0];
                                    // XposedBridge.log("arg[0]的值：" + arg);
                                    // Class<?> baseRespClass = param.args[0].getClass();
                                    // XposedBridge.log("baseRespClass的值：" + arg);
                                    Class clazz = arg.getClass();
                                    // 获取类中声明的字段
                                    // Field[] fields = clazz.getDeclaredFields();
                                    // for (Field field : fields) {
                                    //     field.setAccessible(true);
                                    //     XposedBridge.log(field.getName() + ":" + field.get(arg));
                                    // }
                                    Field datField = clazz.getDeclaredField("dat");
                                    datField.setAccessible(true);// 设置data属性为可访问的
                                    if (datField.get(arg) != null) {
                                        ArrayList datArrayList = (ArrayList) datField.get(arg);
                                        XposedBridge.log("数据量==========" + datArrayList.size());

                                        StringBuffer stringBuffer = new StringBuffer();
                                        for (Object skiFieldDetailPhoto_obj : datArrayList) {
                                            // 使用反射获取对象中字段的值
                                            Class<?> aClass = skiFieldDetailPhoto_obj.getClass();
                                            // 构建get方法
                                            Method getPhotoDate = aClass.getMethod("getPhotoDate");
                                            // 执行方法
                                            Object invokeResult = getPhotoDate.invoke(skiFieldDetailPhoto_obj);
                                            // 取值
                                            stringBuffer.append(((String) invokeResult) + ",");
                                        }
                                        photodata = stringBuffer.toString();
                                        // XposedBridge.log("GOSKI_Crack1==>" + photodata);
                                    }
                                }

                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                }
                            });

                            // final Class<?> SkiFieldDetailPhoto = XposedHelpers.findClass("com.goski.goskibase.basebean.circle.SkiFieldDetailPhoto", classLoader);
                            // XposedHelpers.findAndHookConstructor("com.goski.sharecomponent.viewmodel.p0", classLoader, SkiFieldDetailPhoto, new XC_MethodHook() {
                            //     @Override
                            //     protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //         super.beforeHookedMethod(param);
                            //         int num = 0;
                            //
                            //         // 获取入参对象
                            //         Object skiFieldDetailPhoto_obj = param.args[0];
                            //         // 使用反射获取对象中字段的值
                            //         Class<?> aClass = skiFieldDetailPhoto_obj.getClass();
                            //         // 构建get方法
                            //         Method getPhotoDate = aClass.getMethod("getPhotoDate");
                            //         // 执行方法
                            //         Object invokeResult = getPhotoDate.invoke(skiFieldDetailPhoto_obj);
                            //         // 取值
                            //         // XposedBridge.log("GOSKI_Crack1==>" + (String) invokeResult);
                            //
                            //         String str2 = (String) invokeResult;
                            //         // 截取，只要年月日，去掉时分秒
                            //         str2 = str2.substring(0, 10);
                            //         // XposedBridge.log("str2==========" + str2);
                            //         if (photodata.indexOf(str2) != -1) {
                            //             String[] split = photodata.split(str2);
                            //             num = split.length - 1;
                            //             // XposedBridge.log("数量==========" + num);
                            //         }
                            //         // XposedBridge.log("str2==========" + str2);
                            //         String[] split = str2.split("-");
                            //         split[1] = split[1] + "(" + num + ")";
                            //         XposedBridge.log("GOSKI_Crack==>" + split[0] + "-" + split[1] + "-" + split[2]);
                            //
                            //         // 调用set方法 将修改后的数据赋值
                            //         Method setPhotoDate = aClass.getMethod("setPhotoDate", String.class);
                            //         setPhotoDate.invoke(skiFieldDetailPhoto_obj, (split[0] + "-" + split[1] + "-" + split[2]));
                            //
                            //         Object invokeResult1 = getPhotoDate.invoke(skiFieldDetailPhoto_obj);
                            //         XposedBridge.log("GOSKI_Crack1==>" + (String) invokeResult1);
                            //
                            //     }
                            //
                            //     @Override
                            //     protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            //         super.afterHookedMethod(param);
                            //     }
                            // });

                            XposedHelpers.findAndHookConstructor(versionHook.versionMap.get(viewConstructor), classLoader, String.class, String.class, boolean.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    int num = 0;
                                    String str2 = (String) param.args[1];
                                    // XposedBridge.log("str2==========" + str2);
                                    if (photodata.indexOf(str2) != -1) {
                                        String[] split = photodata.split(str2);
                                        num = split.length - 1;
                                        // XposedBridge.log("数量==========" + num);
                                    }
                                    // XposedBridge.log("str2==========" + str2);
                                    String[] split = str2.split("-");
                                    split[1] = split[1] + "(" + num + ")";
                                    XposedBridge.log("GOSKI_Crack==>" + split[0] + "-" + split[1] + "-" + split[2]);
                                    param.args[1] = split[0] + "-" + split[1] + "-" + split[2];
                                }

                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                }
                            });
                        }
                    }
                }
            });
        }
    }

}
