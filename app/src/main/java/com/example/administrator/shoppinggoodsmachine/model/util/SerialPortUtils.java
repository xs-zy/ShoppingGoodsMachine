package com.example.administrator.shoppinggoodsmachine.model.util;

import android.text.TextUtils;
import android.util.Log;

import com.epton.sdk.callback.DevicesStateListener;
import com.epton.sdk.callback.ResultCallBack;
import com.epton.sdk.port.ADH815Port;
import com.epton.sdk.port.PortController;
import com.example.administrator.shoppinggoodsmachine.base.App;


/**
 * 作者：create by ZhiYuan Xue on 2019/8/30 13:14
 * 邮箱：xzy7319@sina.com
 */

public class SerialPortUtils implements DevicesStateListener{

    private static SerialPortUtils portUtils = null;

    private SerialPortUtils(){}
    public synchronized static SerialPortUtils getPortUtils(){
        if(portUtils == null){
            portUtils = new SerialPortUtils();
        }
        return portUtils;
    }

    /**
     * 打开串口
     * @return
     */
    public boolean openSerialPort(){
       return  PortController.init(App.context, "ttyS", 0, 38400);
    }

    /**
     * 关闭串口
     */
    public void closeSerialProt(){
        PortController.closeSerialPort();
    }

    /**
     * 打开红外，开启掉货检测
     */
    public void openRedLight(){
        PortController.setStateListener(this);
        PortController.set815Mode(1, "80");
    }

    /**
     * 关闭红外，关闭掉货检测
     */
    public void closeRedLight(){
        PortController.set815Mode(1, "00");
    }



    /**
     *  出货接口
     * @param num   货道号
     * @return
     */
    public int shipment(String num){
        final int[] STATE = {StateInfo.SHIPMENT_STATE};
        if (TextUtils.isEmpty(num)) return StateInfo.NULL_SHOP_NUM;
        if (ADH815Port.ADH815State == -1) return StateInfo.SOCKET_ERROR;
        PortController.outGoods(Integer.parseInt(num), new ResultCallBack() {
            @Override
            public void onSuccess(int i, int i1) {
                STATE[0] = StateInfo.SHIPMENT_SUCCESS;
            }

            @Override
            public void onFailure(int i, String s, String s1) {
                Log.d("tencent",s+"======"+s1);
                //需要处理
                STATE[0] = StateInfo.SHIPMENT_FAIL;
            }
        });
        return STATE[0];
    }

    @Override
    public void onStateChanged(int[] ints, byte[] bytes) {

    }

    @Override
    public void onModeChanged() {

    }

    @Override
    public void onDisconnected(int i) {

    }
}
