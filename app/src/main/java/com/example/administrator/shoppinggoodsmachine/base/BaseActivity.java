package com.example.administrator.shoppinggoodsmachine.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.shoppinggoodsmachine.receiver.NetWorkChangReceiver;


/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 15:51
 * 邮箱：xzy7319@sina.com
 */

public abstract class BaseActivity extends App {
    private boolean isRegistered = false;
    private NetWorkChangReceiver netWorkChangReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册网络状态监听广播
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
        App.context = this;
        isRegistered = true;
        layoutId();
        initView();
        initData();
        initListener();
        loadData();
    }

    //布局绑定
    protected abstract void layoutId();
    //初始化view
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();
    //加载数据
    protected abstract void loadData();
    //跳转Activity
    public abstract void in(Class tClass);
    //回退Activity
    public void out() {
        finish();
    }

    @Override
    protected void onDestroy() {
        //解绑
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
        super.onDestroy();
    }
}
