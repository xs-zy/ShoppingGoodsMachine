package com.example.administrator.shoppinggoodsmachine.model.api;

import com.example.administrator.shoppinggoodsmachine.base.Urls;
import com.example.administrator.shoppinggoodsmachine.model.entity.LoginBean;
import com.example.administrator.shoppinggoodsmachine.model.entity.OutChannelInfoBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/1 14:10
 * 邮箱：xzy7319@sina.com
 */

public interface ChannelInfoApi {
    @POST(Urls.GETOUTCHANNELINFO)
    Call<OutChannelInfoBean> getResult(@Query("machineId") String machineId,@Query("receiveCode") String receiveCode);
}
