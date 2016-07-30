package com.joysus.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.CommonResult;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/3.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText edusername, edpwd;
    private Button btnlogin;
    private TextView txtregister, txtforgetpwd;
    private Context context;
    private final static int LOGIN_SUCCESS = 1;
    private final static int LOGIN_ERROR_USER = 2;
    private final static int LOGIN_ERROR_SERVER = 3;
    private final static int LOGIN_ERROR_NET = 4;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCESS:

                    break;
                case LOGIN_ERROR_USER:
                    Toast.makeText(context, "登录失败，请检查用户名密码！", Toast.LENGTH_LONG).show();
                    break;
                case LOGIN_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case LOGIN_ERROR_NET:
                    Toast.makeText(context, "登录失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        context = this;

    }

    private void initView() {
        edusername = (EditText) findViewById(R.id.edusername);
        edpwd = (EditText) findViewById(R.id.edpwd);
        txtregister = (TextView) findViewById(R.id.txtregister);
        txtforgetpwd = (TextView) findViewById(R.id.txtforgetpwd);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        txtregister.setOnClickListener(this);
        txtforgetpwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                CheckUserPwd();
                break;
            case R.id.txtregister:
                Intent rintent = new Intent(this, RegisterActivity.class);
                startActivity(rintent);
                break;
            case R.id.txtforgetpwd:
                Intent fintent = new Intent(this, ForgorPwdActivity.class);
                startActivity(fintent);
                break;
        }
    }

    private void CheckUserPwd() {
        if (TextUtils.isEmpty(edusername.getText().toString().trim())) {
            Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edusername.getText().toString().trim())) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            Login();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void Login() {

        Map<String, String> params = new HashMap<>();
        params.put("username", edusername.getText().toString().trim());
        params.put("password", edpwd.getText().toString().trim());
        OkHttpUtil.post(ContantsUrl.URL_LOGIN, null, params, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(LOGIN_ERROR_NET);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 失败
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                // 获取code
                if (response.code() == 200) {
                    // 判断类型
                    String result = response.body().string();
                    JSONObject object = JSONObject.parseObject(result);
                    CommonResult cr = JSONObject.toJavaObject(object, CommonResult.class);

                    if (cr.getCode().equals("1")) {
                        handler.sendEmptyMessage(LOGIN_SUCCESS);
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = LOGIN_ERROR_USER;
                        msg.obj = response.code();
                        handler.sendMessage(msg);
                    }
                } else {
                    handler.sendEmptyMessage(LOGIN_ERROR_SERVER);
                }
            }
        });
    }
}
