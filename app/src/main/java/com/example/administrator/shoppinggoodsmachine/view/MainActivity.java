package com.example.administrator.shoppinggoodsmachine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.epton.sdk.callback.DevicesStateListener;
import com.epton.sdk.callback.ResultCallBack;
import com.epton.sdk.port.PortController;
import com.example.administrator.shoppinggoodsmachine.R;
import com.example.administrator.shoppinggoodsmachine.base.App;
import com.example.administrator.shoppinggoodsmachine.base.BaseActivity;
import com.example.administrator.shoppinggoodsmachine.model.api.ChannelInfoApi;
import com.example.administrator.shoppinggoodsmachine.model.api.PictureApi;
import com.example.administrator.shoppinggoodsmachine.model.api.ResultApi;
import com.example.administrator.shoppinggoodsmachine.model.entity.OutChannelInfoBean;
import com.example.administrator.shoppinggoodsmachine.model.entity.PictureBean;
import com.example.administrator.shoppinggoodsmachine.model.entity.ResultNoticeBean;
import com.example.administrator.shoppinggoodsmachine.receiver.BarcodeScannerResolver;
import com.example.administrator.shoppinggoodsmachine.model.util.GlideImageLoader;
import com.example.administrator.shoppinggoodsmachine.model.util.RetrofitClient;
import com.example.administrator.shoppinggoodsmachine.model.util.SerialPortUtils;
import com.example.administrator.shoppinggoodsmachine.model.util.WifiStateEvent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity implements DevicesStateListener {

    RelativeLayout linearLayout;
    final long[] mHints = new long[4];
    List<String> image_list;
    private Banner banner;
    private BarcodeScannerResolver mBarcodeScannerResolver;
    private Button setting_btn;
    private SharedPreferences sp;
    private String device_id;
    private TextView textView;
    private int jump = 0;
    private SerialPortUtils portUtils;
    private boolean isFinish = false;
    private List<Bitmap> bitmaps;
    private Bitmap diskBitmap;


    @Override
    protected void layoutId() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        //初始化控件
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initViews(){
        textView = (TextView) findViewById(R.id.text_info);
        banner = (Banner) findViewById(R.id.banner);
        linearLayout = (RelativeLayout) findViewById(R.id.line_setting);
        setting_btn = (Button) findViewById(R.id.setting);
    }

    @Override
    protected void initData() {
        sp = App.context.getSharedPreferences("DeviceId", MODE_PRIVATE);
        device_id = sp.getString("device_Id", "errorCode");
        portUtils = SerialPortUtils.getPortUtils();
        mBarcodeScannerResolver = new BarcodeScannerResolver();
        image_list = new ArrayList<>();
        bitmaps = new ArrayList<>();
        boolean is_open = portUtils.openSerialPort();
        getPicture();
        if (is_open) {
            //如果串口打开则打开红外检测
            portUtils.openRedLight();
        }else {
            Toast.makeText(MainActivity.this, "串口打开失败，请检测...", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void initListener() {
        mBarcodeScannerResolver.setScanSuccessListener(new BarcodeScannerResolver.OnScanSuccessListener() {
            @Override
            public void onScanSuccess(final String barcode) {
                if(!isFinish){
                    isFinish = true;
                    getCargoNum(barcode);
                }else {
                    Toast.makeText(MainActivity.this,"正在出货中，请等待...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *  无网络或者设备未激活则点击此按钮
     * @param view
     */
    public void settingBtn(View view) {
        if (jump == 0) {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }



    @Override
    protected void loadData() {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setDelayTime(1000 * 30);
    }

    @Override
    public void in(Class tClass) {

    }

    /**
     * 移除扫码监听
     */
    @Override
    protected void onDestroy() {
        mBarcodeScannerResolver.removeScanSuccessListener();
        mBarcodeScannerResolver = null;
        super.onDestroy();
    }

    /**
     * 进入登录界面
     *
     * @param view
     */
    public void top_btn(View view) {
        System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
        //获取系统当前时间
        mHints[mHints.length - 1] = SystemClock.uptimeMillis();
        if (SystemClock.uptimeMillis() - mHints[0] <= 500) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    /**
     * Activity截获按键事件.发给 BarcodeScannerResolver
     * dispatchKeyEvent() 和 onKeyDown() 方法均可
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        mBarcodeScannerResolver.resolveKeyEvent(event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 网络请求
     * 获取首页轮播图片
     */
    public void getPicture(){
        PictureApi pictureApi =
                RetrofitClient.getInstance().getPictures();
        Call<PictureBean> call = pictureApi.getResult();
        call.enqueue(new Callback<PictureBean>() {
            @Override
            public void onResponse(Call<PictureBean> call, Response<PictureBean> response) {
                String status = response.body().getStatus();
                if ("1".equals(status)) {
                    //获取数据成功
                    List<PictureBean.DataBean> data = response.body().getData();
                    if(data.size() != 0){
                        for(int i=0;i<data.size();i++){
                            image_list.add(data.get(i).getAdContent());
                            Log.d("v_zyuanxue",data.get(i).getAdContent());
                        }
                    }else {
                        image_list.add("a.jpg");
                        image_list.add("b.jpg");
                        image_list.add("c.jpg");
                        image_list.add("d.jpg");
                    }
                    List<String> usbPaths = getUsbPaths(MainActivity.this);
                    if (usbPaths.size() != 0) {
                        String usb_src = usbPaths.get(0);
                        for(int i = 0;i < image_list.size();i++){
                            diskBitmap = getDiskBitmap(usb_src + "/udisk0/"+image_list.get(i));
                            bitmaps.add(diskBitmap);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "请检查U盘", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    String message = response.body().getMessage();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                banner.setImages(bitmaps);
                banner.start();
            }

            @Override
            public void onFailure(Call<PictureBean> call, Throwable t) {
                List<String> usbPaths = getUsbPaths(MainActivity.this);
                image_list.add("a.jpg");
                image_list.add("b.jpg");
                image_list.add("c.jpg");
                image_list.add("d.jpg");
                if (usbPaths.size() != 0) {
                    String usb_src = usbPaths.get(0);
                    for(int i = 0;i < image_list.size();i++){
                        diskBitmap = getDiskBitmap(usb_src + "/udisk0/"+image_list.get(i));
                        bitmaps.add(diskBitmap);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "请检查U盘", Toast.LENGTH_SHORT).show();
                }
                banner.setImages(bitmaps);
                banner.start();
            }
        });
    }


    /**
     *  获取货道号
     * @param barcode  顾客手中的二维码
     * @return
     */
    public void getCargoNum(final String barcode) {
        ChannelInfoApi cargoNum =
                RetrofitClient.getInstance().getCargoNum();
        Call<OutChannelInfoBean> call =
                cargoNum.getResult(device_id, barcode);
        call.enqueue(new Callback<OutChannelInfoBean>() {

            @Override
            public void onResponse(Call<OutChannelInfoBean> call, Response<OutChannelInfoBean> response) {
                String status = response.body().getStatus();
                if ("1".equals(status)) {
                    OutChannelInfoBean.DataBean dataBean = response.body().getData().get(0);
                    final int cargoNum = dataBean.getIndex();
                    final String id = dataBean.getId();
                    PortController.outGoods(cargoNum-1, new ResultCallBack() {
                        @Override
                        public void onSuccess(int i, int i1) {
                            sendSuccessRequest(id, "0000", barcode);
                        }

                        @Override
                        public void onFailure(int i, String s, String s1) {
                            sendFailRequest(id, "0001", s1, barcode);
                        }
                    });
                } else {
                    String message = response.body().getMessage();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    isFinish = false;
                }
            }

            @Override
            public void onFailure(Call<OutChannelInfoBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                isFinish = false;
            }
        });
    }


    /**
     *   返回失败出货结果
     * @param channelId
     * @param errCode
     * @param errorMsg
     * @param receiveCode
     */
    private void sendFailRequest(String channelId, String errCode, String errorMsg, String receiveCode){
        ResultApi result =
                RetrofitClient.getInstance().getResult();
        Call<ResultNoticeBean> failResult
                = result.getFailResult(channelId, errCode, receiveCode, errorMsg);
        failResult.enqueue(new Callback<ResultNoticeBean>() {
            @Override
            public void onResponse(Call<ResultNoticeBean> call, Response<ResultNoticeBean> response) {
                String status = response.body().getStatus();
                if("1".equals(status)){
                    Log.d("zhiyuan","无故障出货成功");
                    Toast.makeText(MainActivity.this, "出货失败", Toast.LENGTH_SHORT).show();
                    isFinish = false;
                }
            }

            @Override
            public void onFailure(Call<ResultNoticeBean> call, Throwable t) {
                Log.d("zhiyuan","网络请求失败");
                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                isFinish = false;
            }
        });
    }


    /**
     *   返回成功出货结果
     * @param channelId
     * @param errCode
     * @param receiveCode
     */
    private void sendSuccessRequest(String channelId, String errCode, String   receiveCode){
        ResultApi result =
                RetrofitClient.getInstance().getResult();
        final Call<ResultNoticeBean> failResult
                = result.getSuccessResult(channelId, errCode, receiveCode);
        failResult.enqueue(new Callback<ResultNoticeBean>() {
            @Override
            public void onResponse(Call<ResultNoticeBean> call, Response<ResultNoticeBean> response) {
                String status = response.body().getStatus();
                if("1".equals(status)){
                    Log.d("zhiyuan","出货成功");
                    Toast.makeText(MainActivity.this, "出货成功", Toast.LENGTH_SHORT).show();
                    isFinish = false;
                }
            }

            @Override
            public void onFailure(Call<ResultNoticeBean> call, Throwable t) {
                Log.d("zhiyuan","网络请求失败");
                Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                isFinish = false;
            }
        });
    }


    //下位机设备状态监听
    @Override
    public void onStateChanged(int[] ints, byte[] bytes) {

    }

    @Override
    public void onModeChanged() {

    }

    @Override
    public void onDisconnected(int i) {

    }

    /***
     * 判断网络状态
     *
     * @param event
     */
    @Subscribe
    public void onEvent(WifiStateEvent event) {
        int net_state = event.getState();
        if (net_state == 1) {
            //有网络
            //获取设备ID
            jump = 1;
            if ("errorCode".equals(device_id)) {
                linearLayout.setVisibility(View.VISIBLE);
                textView.setText("获取设备ID");
                setting_btn.setText("设备设置");
            } else {
                //获取图片
                banner.setVisibility(View.VISIBLE);
            }
        } else {
            //无网络
            jump = 0;
            textView.setText("网络无连接");
            setting_btn.setText("网络设置");
            linearLayout.setVisibility(View.VISIBLE);

        }
    }


    private Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return bitmap;
    }


    public static List<String> getUsbPaths(Context cxt) {
        List<String> usbPaths = new ArrayList<>();
        try {
            StorageManager srgMgr = (StorageManager) cxt.getSystemService(Context.STORAGE_SERVICE);
            Class<StorageManager> srgMgrClass = StorageManager.class;
            String[] paths = (String[]) srgMgrClass.getMethod("getVolumePaths").invoke(srgMgr);
            for (String path : paths) {
                Object volumeState = srgMgrClass.getMethod("getVolumeState", String.class).invoke(srgMgr, path);
                if (!path.contains("emulated") && Environment.MEDIA_MOUNTED.equals(volumeState))
                    usbPaths.add(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usbPaths;
    }

}
