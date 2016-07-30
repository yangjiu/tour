package com.joysus.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
 * Created by Administrator on 2016/7
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button btnok, btcode;
    ;
    private EditText etphonenum, etcode, etpwd, etpwd2;
    private CheckBox cbprotocol;
    private final static int REG_SUCCESS = 1;
    private final static int REG_ERROR_CODE = 2;
    private final static int REG_ERROR_SERVER = 3;
    private final static int REG_ERROR_NET = 4;
    private final static int SEND_SUCCESS = 5;
    private final static int SEND_ERROR_CODE = 6;
    private final static int SEND_ERROR_SERVER = 7;
    private final static int SEND_ERROR_NET = 8;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REG_SUCCESS:

                    break;
                case REG_ERROR_CODE:
                    Toast.makeText(context, "注册失败，" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case REG_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case REG_ERROR_NET:
                    Toast.makeText(context, "注册失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
                case SEND_SUCCESS:
                    etcode.setText(msg.obj.toString());
                    break;
                case SEND_ERROR_CODE:
                    Toast.makeText(context, "错误消息："+ msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case SEND_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case SEND_ERROR_NET:
                    Toast.makeText(context, "验证码接收失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");
        showBackButton(true);
        initView();
    }

    private void initView() {
        etphonenum = (EditText) findViewById(R.id.etphonenum);
        etcode = (EditText) findViewById(R.id.etcode);
        etpwd = (EditText) findViewById(R.id.etpwd);
        etpwd2 = (EditText) findViewById(R.id.etpwd2);
        cbprotocol = (CheckBox) findViewById(R.id.cbprotocol);
        btcode = (Button) findViewById(R.id.btcode);
        btcode.setOnClickListener(this);
        btnok = (Button) findViewById(R.id.btnregister);
        btnok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnregister:
                CheckText();
                break;
            case R.id.btcode:
                if (!TextUtils.isEmpty(etphonenum.getText().toString().trim())) {
                    SendCode();
                }
                break;
        }
    }

    private void CheckText() {
        if (TextUtils.isEmpty(etphonenum.getText().toString().trim())) {
            Toast.makeText(context, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etcode.getText().toString().trim())) {
            Toast.makeText(context, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etpwd.getText().toString().trim())) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etpwd2.getText().toString().trim())) {
            Toast.makeText(context, "请输入确认密码", Toast.LENGTH_SHORT).show();
        } else {
            if (etpwd.getText().toString().trim().equals(etpwd2.getText().toString().trim())) {
                Register();
            } else {
                Toast.makeText(context, "两次输入的密码不一致，请检查后重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SendCode() {
        // 组装参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", etphonenum.getText().toString().trim());
        params.put("vtype", "1");
        OkHttpUtil.post(ContantsUrl.URL_SENDSMS, null, params, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(SEND_ERROR_NET);
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
                        JSONObject object1 = JSONObject.parseObject(cr.getData().toString());
                        String vcode = object1.getString("vcode");
                        Message msg = new Message();
                        msg.what = SEND_SUCCESS;
                        msg.obj = vcode;
                        handler.sendMessage(msg);
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = SEND_ERROR_CODE;
                        msg.obj = cr.getMessage();
                        handler.sendMessage(msg);
                    }
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = SEND_ERROR_SERVER;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }

        });
    }

    private void Register() {
        // 组装参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", etphonenum.getText().toString().trim());
        params.put("vcode", etcode.getText().toString().trim());
        params.put("password", etpwd.getText().toString().trim());
        OkHttpUtil.post(ContantsUrl.URL_REGISTER, null, params, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(REG_ERROR_NET);
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
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = REG_ERROR_CODE;
                        msg.obj = cr.getMessage();
                        handler.sendMessage(msg);
                    }
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = REG_ERROR_SERVER;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
