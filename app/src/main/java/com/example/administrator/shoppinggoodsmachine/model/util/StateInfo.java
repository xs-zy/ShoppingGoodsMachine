package com.example.administrator.shoppinggoodsmachine.model.util;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/30 13:31
 * 邮箱：xzy7319@sina.com
 */

public interface StateInfo {
    //没有货道号
      int NULL_SHOP_NUM = 404;
    //通讯异常,初始化未完成!
      int SOCKET_ERROR = 405;
    //出货成功
      int SHIPMENT_SUCCESS = 200;
    //出货失败
      int SHIPMENT_FAIL = 500;
    //出货情况
      int SHIPMENT_STATE = 101;
}
