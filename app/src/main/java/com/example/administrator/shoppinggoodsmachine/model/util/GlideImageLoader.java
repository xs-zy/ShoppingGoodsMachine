package com.example.administrator.shoppinggoodsmachine.model.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 15:40
 * 邮箱：xzy7319@sina.com
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap((Bitmap) path);
       /* Glide.with(context)
                .load((Bitmap)path)
                .into(imageView);*/
    }
}
