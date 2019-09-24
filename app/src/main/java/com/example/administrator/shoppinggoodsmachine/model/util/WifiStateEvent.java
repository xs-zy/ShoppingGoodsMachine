package com.example.administrator.shoppinggoodsmachine.model.util;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/1 10:50
 * 邮箱：xzy7319@sina.com
 */

public class WifiStateEvent {

    public int state;

    public WifiStateEvent(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
