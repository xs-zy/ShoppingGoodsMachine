package com.example.administrator.shoppinggoodsmachine.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.dialogui.DialogUIUtils;
import com.epton.sdk.callback.DevicesStateListener;
import com.epton.sdk.callback.ResultCallBack;
import com.epton.sdk.port.PortController;
import com.example.administrator.shoppinggoodsmachine.R;
import com.example.administrator.shoppinggoodsmachine.base.App;
import com.example.administrator.shoppinggoodsmachine.base.BaseActivity;
import com.example.administrator.shoppinggoodsmachine.model.api.MacineIdApi;
import com.example.administrator.shoppinggoodsmachine.model.entity.MachineBean;
import com.example.administrator.shoppinggoodsmachine.model.util.MacUtil;
import com.example.administrator.shoppinggoodsmachine.model.util.RetrofitClient;
import com.example.administrator.shoppinggoodsmachine.model.util.SerialPortUtils;
import com.example.administrator.shoppinggoodsmachine.model.util.StateInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 18:00
 * 邮箱：xzy7319@sina.com
 */

public class DeviceTestActivity extends BaseActivity implements DevicesStateListener {

    public GridView gridView;
    public SimpleAdapter simpleAdapter;
    public List<Map<String, Object>> data_list;
    public List<Integer> list;
    private SerialPortUtils portUtils;
    private boolean TAG = false;
    private TextView textView;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;
    //private EditText editText_once,editText_line;

    @Override
    protected void layoutId() {
        setContentView(R.layout.device_layout);
    }

    @Override
    protected void initView() {
       // textView_result = (TextView) findViewById(R.id.result);
        //editText_once = (EditText) findViewById(R.id.edit_once);
        //editText_line = (EditText) findViewById(R.id.edit_line);
        sp = App.context.getSharedPreferences("DeviceId", MODE_PRIVATE);
        editor = sp.edit();
        textView = (TextView) findViewById(R.id.dv_ID);
        gridView = (GridView) findViewById(R.id.grid);
    }

    @Override
    protected void initData() {
        portUtils = SerialPortUtils.getPortUtils();
        list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add(i);
        }
        data_list = new ArrayList<>();
        getData();
        //新建适配器
        String[] from = {"text"};
        int[] to = {R.id.text};
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.grid_item, from, to);
        gridView.setAdapter(simpleAdapter);
        gridView.deferNotifyDataSetChanged();
        String device_id = sp.getString("device_Id", "errorCode");
        if("errorCode".equals(device_id)){
            textView.setText("无");
        }else {
            textView.setText(device_id);
        }
    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < 60; i++) {
            Map<String, Object> map = new HashMap<>();
            //map.put("image", R.mipmap.ic_launcher);
            map.put("text", list.get(i));
            data_list.add(map);
        }
        return data_list;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        boolean is_open = portUtils.openSerialPort();
        Log.d("xuezhiyuan",is_open+"");
        if (is_open) {
            //打开红外检测
            portUtils.openRedLight();
            Toast.makeText(DeviceTestActivity.this, "串口打开成功", Toast.LENGTH_SHORT).show();
            TAG = true;
        } else {
            Toast.makeText(DeviceTestActivity.this, "串口打开失败，请检测...", Toast.LENGTH_SHORT).show();
        }

        if(TAG){
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //弹出对话框
                    final Dialog show = DialogUIUtils.showMdLoadingHorizontal(DeviceTestActivity.this, "运行中...", false, false, true).show();
                    PortController.outGoods(i, new ResultCallBack() {
                        @Override
                        public void onSuccess(int i, int i1) {
                            DialogUIUtils.dismiss(show);
                            Message message = new Message();
                            message.what = 0;
                            message.obj = i1 + "" + i;
                            mHandler.sendMessage(message);
                        }

                        @Override
                        public void onFailure(int i, String s, String s1) {
                            DialogUIUtils.dismiss(show);
                            Message message = new Message();
                            message.what = 0;
                            message.obj = s1 + "" + s;
                            mHandler.sendMessage(message);
                        }
                    });
                }
            });
        }else {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(DeviceTestActivity.this, "串口打开失败，请检测...", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String obj = (String) msg.obj;
                    Toast.makeText(DeviceTestActivity.this, obj, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void in(Class tClass) {

    }

    @Override
    protected void onDestroy() {
        portUtils.closeSerialProt();
        super.onDestroy();
    }

    @Override
    public void onStateChanged(int[] ints, byte[] bytes) {

    }

    @Override
    public void onModeChanged() {

    }

    @Override
    public void onDisconnected(int i) {

    }

    /**
     * 获取设备ID
     * @param view
     */
    public void getDeviceId(View view){
        String localMacAddress = MacUtil.getLocalMacAddress(App.context);
        MacineIdApi machineId =
                RetrofitClient.getInstance().getMachineId();
        Call<MachineBean> call =
                machineId.getResult(localMacAddress);
        call.enqueue(new Callback<MachineBean>() {
            @Override
            public void onResponse(Call<MachineBean> call, Response<MachineBean> response) {
                String status = response.body().getStatus();
                if("1".equals(status)){
                    String id = response.body().getData().getId();
                    Toast.makeText(DeviceTestActivity.this, "获取设备ID成功", Toast.LENGTH_SHORT).show();
                    textView.setText(id);
                    editor.putString("device_Id",id);
                    editor.commit();
                }else {
                    Toast.makeText(DeviceTestActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                    textView.setText("无");
                }
            }

            @Override
            public void onFailure(Call<MachineBean> call, Throwable t) {
                Toast.makeText(DeviceTestActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                textView.setText("无");
            }
        });
    }


    /**
     * 一键测试
     */
    public void StartTest(View view){
       /* final Dialog show = DialogUIUtils.showMdLoadingHorizontal(DeviceTestActivity.this, "运行中...", false, false, true).show();
        final int[] m = {0};
        while (m[0] <50){
                PortController.outGoods(m[0], new ResultCallBack() {
                    @Override
                    public void onSuccess(int i, int i1) {
                        DialogUIUtils.dismiss(show);
                        Message message = new Message();
                        message.what = 0;
                        message.obj = i1 + "" + i;
                        testHandler.sendMessage(message);
                        m[0]++;
                    }

                    @Override
                    public void onFailure(int i, String s, String s1) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = s + "" + s1;
                        testHandler.sendMessage(message);
                        DialogUIUtils.dismiss(show);
                        m[0] = 50;
                    }
                });
            }*/
    }


}
