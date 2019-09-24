package com.example.administrator.shoppinggoodsmachine.model.api;

import com.example.administrator.shoppinggoodsmachine.base.Urls;
import com.example.administrator.shoppinggoodsmachine.model.entity.LoginBean;
import com.example.administrator.shoppinggoodsmachine.model.entity.MachineBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/31 14:43
 * 邮箱：xzy7319@sina.com
 */
//分配设备号
public interface MacineIdApi {
    @POST(Urls.BUILDMACHINE)
    Call<MachineBean> getResult(@Query("deviceNum") String deviceNum);
}
