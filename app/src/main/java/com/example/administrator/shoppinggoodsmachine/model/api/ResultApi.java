package com.example.administrator.shoppinggoodsmachine.model.api;

import com.example.administrator.shoppinggoodsmachine.base.Urls;
import com.example.administrator.shoppinggoodsmachine.model.entity.MachineBean;
import com.example.administrator.shoppinggoodsmachine.model.entity.ResultNoticeBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/1 14:39
 * 邮箱：xzy7319@sina.com
 */

public interface ResultApi {
    @POST(Urls.TRADERESULTNOTICE)
    Call<ResultNoticeBean> getSuccessResult(@Query("channelId") String channelId, @Query("errCode") String errCode, @Query("receiveCode") String receiveCode);

    @POST(Urls.TRADERESULTNOTICE)
    Call<ResultNoticeBean> getFailResult(@Query("channelId") String channelId,@Query("errCode") String errCode,@Query("receiveCode") String receiveCode,@Query("errorMsg") String errorMsg);
}
