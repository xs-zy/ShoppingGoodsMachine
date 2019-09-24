package com.example.administrator.shoppinggoodsmachine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/7 15:52
 * 邮箱：xzy7319@sina.com
 */

public class MediaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_MEDIA_CHECKING:
                break;
            case Intent.ACTION_MEDIA_MOUNTED:
                // 获取挂载路径, 读取U盘文件
                Uri uri = intent.getData();
                if (uri != null) {
                    String filePath = uri.getPath();
                    File rootFile = new File(filePath);
                    for (File file : rootFile.listFiles()) {
                        // 文件列表...
                    }
                }
                break;
            case Intent.ACTION_MEDIA_EJECT:
                break;
            case Intent.ACTION_MEDIA_UNMOUNTED:
                break;
        }
    }
}
