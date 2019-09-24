package com.example.administrator.shoppinggoodsmachine.base;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 16:00
 * 邮箱：xzy7319@sina.com
 */

public class Urls {

    //base_url
    public final static  String BASE_URL = "https://api.weieryuepai.com";

    //登录接口   参数加密的密码
    public final static String MACHINELOGIN = "/m/machineLogin";

    //广告列表
    public final static String ADVERTISMENTS = "/m/advertisments";

    //创建机具分配设备号
    public final static String BUILDMACHINE = "/m/buildMachine";

    //根据二维码获取出货道信息
    public final static String GETOUTCHANNELINFO = "/m/getOutChannelInfo";

    //出货结果通知
    public final static String TRADERESULTNOTICE = "/m/tradeResultNotice";
}
