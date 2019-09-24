package com.example.administrator.shoppinggoodsmachine.model.api;

import com.example.administrator.shoppinggoodsmachine.base.Urls;
import com.example.administrator.shoppinggoodsmachine.model.entity.LoginBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/31 00:24
 * 邮箱：xzy7319@sina.com
 */

public interface LoginApi {
    @POST(Urls.MACHINELOGIN)
    Call<LoginBean> getResult(@Query("loginInfo") String loginInfo);
}
