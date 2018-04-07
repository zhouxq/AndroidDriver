package com.example.slope.androiddriver.utils;

import java.util.Map;

/**
 * Created by zhou on 2018/4/6.
 */

public class PhoneInfoUtil {

    static public void getPhoneInfo(Map<String,String> map){
        map.put("Product" , android.os.Build.PRODUCT);
        map.put("CPU_ABI" , android.os.Build.CPU_ABI);
        map.put("TAGS" , android.os.Build.TAGS);
        map.put("VERSION_CODES.BASE" , String.valueOf(android.os.Build.VERSION_CODES.BASE));
        map.put("MODEL" , android.os.Build.MODEL);
        map.put("SDK" , android.os.Build.VERSION.SDK);
        map.put("VERSION.RELEASE" , android.os.Build.VERSION.RELEASE);
        map.put("DEVICE" , android.os.Build.DEVICE);
        map.put("DISPLAY" , android.os.Build.DISPLAY);
        map.put("BRAND" , android.os.Build.BRAND);
        map.put("FINGERPRINT" , android.os.Build.FINGERPRINT);
        map.put("ID" , android.os.Build.ID);
        map.put("MANUFACTURER" , android.os.Build.MANUFACTURER);
        map.put("USER" , android.os.Build.USER);
    }


}
