package com.example.administrator.shoppinggoodsmachine.model.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;


/**
 * Created by Administrator on 2018/8/27.
 */

public class MacUtil {
    public static String getLocalMacAddress(Context context){
        WifiManager manger = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = manger.getConnectionInfo();
        String mac = wifiInfo.getMacAddress();
        return mac;
    }
}
