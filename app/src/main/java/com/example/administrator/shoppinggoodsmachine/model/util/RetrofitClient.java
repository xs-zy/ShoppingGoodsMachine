package com.example.administrator.shoppinggoodsmachine.model.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.shoppinggoodsmachine.base.Urls;
import com.example.administrator.shoppinggoodsmachine.model.api.ChannelInfoApi;
import com.example.administrator.shoppinggoodsmachine.model.api.LoginApi;
import com.example.administrator.shoppinggoodsmachine.model.api.MacineIdApi;
import com.example.administrator.shoppinggoodsmachine.model.api.PictureApi;
import com.example.administrator.shoppinggoodsmachine.model.api.ResultApi;
import com.example.administrator.shoppinggoodsmachine.model.entity.LoginBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 16:10
 * 邮箱：xzy7319@sina.com
 */

public class RetrofitClient {

    static RetrofitClient instance;

    /**
     * 构建 retrofit请求
     */
    private final static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * 获取图片列表
     * @return
     */
    public PictureApi getPictures(){return  retrofit.create(PictureApi.class);}

    /**
     * 获取登录信息
     * @return
     */
    public LoginApi getLoginInfo(){
        return retrofit.create(LoginApi.class);
    }

    /**
     * 获取设备号
     * @return
     */
    public MacineIdApi getMachineId(){
        return retrofit.create(MacineIdApi.class);
    }

    /**
     * 获取货道
     */
    public ChannelInfoApi getCargoNum(){
        return retrofit.create(ChannelInfoApi.class);
    }

    /**
     * 返回结果
     */
    public ResultApi getResult(){
        return retrofit.create(ResultApi.class);
    }



    /**
     * 创建请求单例
     * @return
     */
    public static RetrofitClient getInstance(){
        if(instance == null){
            synchronized (RetrofitClient.class){
                if(instance ==null){
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }
}
