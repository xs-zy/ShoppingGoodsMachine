package com.example.administrator.shoppinggoodsmachine.model.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.administrator.shoppinggoodsmachine.view.MainActivity;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/31 15:05
 * 邮箱：xzy7319@sina.com
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Intent sayIntent = new Intent(context, MainActivity.class);
            sayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(sayIntent);
        }
    }
}
