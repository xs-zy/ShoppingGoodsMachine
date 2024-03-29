package com.example.administrator.shoppinggoodsmachine.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.shoppinggoodsmachine.R;
import com.example.administrator.shoppinggoodsmachine.base.App;
import com.example.administrator.shoppinggoodsmachine.base.BaseActivity;
import com.example.administrator.shoppinggoodsmachine.model.api.LoginApi;
import com.example.administrator.shoppinggoodsmachine.model.entity.LoginBean;
import com.example.administrator.shoppinggoodsmachine.model.util.MacUtil;
import com.example.administrator.shoppinggoodsmachine.model.util.ResUtil;
import com.example.administrator.shoppinggoodsmachine.model.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/27 15:50
 * 邮箱：xzy7319@sina.com
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private Button btn;
    private EditText ed_password;
    private SharedPreferences sp;
    private String device_id;
    @Override
    protected void layoutId() {
       setContentView(R.layout.login_layout);
    }

    @Override
    protected void initView() {
        btn = (Button) findViewById(R.id.login_btn);
        ed_password = (EditText) findViewById(R.id.pass_word);
    }

    @Override
    protected void initData() {
        sp = App.context.getSharedPreferences("DeviceId", MODE_PRIVATE);
        device_id = sp.getString("device_Id", "errorCode");
    }

    @Override
    protected void initListener() {
        btn.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void in(Class tClass) {

    }

    @Override
    public void onClick(View view) {
        if ("errorCode".equals(device_id)) {
            Toast.makeText(this,"暂无设备ID，请联系商家登录",Toast.LENGTH_SHORT).show();
        }else {
            String password = ed_password.getText().toString();
            switch (view.getId()) {
                case R.id.login_btn:
                    if (password.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (MacUtil.PWD.equals(password)) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, DeviceTestActivity.class);
                        startActivity(intent);
                        return;
                    }
                    //String encode = ResUtil.encode(password);
                    LoginApi loginInfo =
                            RetrofitClient.getInstance().getLoginInfo();
                    Call<LoginBean> call = loginInfo.getResult(Long.parseLong(device_id),password);
                    call.enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                            String status = response.body().getStatus();
                            String message = response.body().getMessage();
                            if ("1".equals(status)) {
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DeviceTestActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    }
}
