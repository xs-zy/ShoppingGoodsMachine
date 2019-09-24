package com.example.administrator.shoppinggoodsmachine.model.util;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 16:37
 * 邮箱：xzy7319@sina.com
 */

public interface CallBack<T> {
    //成功
    void onSuccess(Call<ResponseBody> call, T response);
    //失败
    void onFailure(Call<ResponseBody> call, Throwable t);
}
