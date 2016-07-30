package com.joysus.acitivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
public class ForgorPwdActivity extends BaseActivity implements View.OnClickListener {
    private Button btnok, btcode;
    private EditText etphonenum, etcode, etpwd, etpwd2;
    private final static int RST_SUCCESS = 1;
    private final static int RST_ERROR_CODE = 2;
    private final static int RST_ERROR_SERVER = 3;
    private final static int RST_ERROR_NET = 4;
    private final static int SEND_SUCCESS = 5;
    private final static int SEND_ERROR_CODE = 6;
    private final static int SEND_ERROR_SERVER = 7;
    private final static int SEND_ERROR_NET = 8;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RST_SUCCESS:

                    break;
                case RST_ERROR_CODE:
                    Toast.makeText(context, "密码修改失败，服务器正在维护中！", Toast.LENGTH_LONG).show();
                    break;
                case RST_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case RST_ERROR_NET:
                    Toast.makeText(context, "密码修改失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
                case SEND_SUCCESS:
                    break;
                case SEND_ERROR_CODE:
                    Toast.makeText(context, "验证码接收失败，服务器正在维护中！", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_forgot_pwd);
        setTitle("忘记密码");
        showBackButton(true);
        initView();
    }

    private void initView() {
        etphonenum = (EditText) findViewById(R.id.etphonenum);
        etcode = (EditText) findViewById(R.id.etcode);
        etpwd = (EditText) findViewById(R.id.etpwd);
        etpwd2 = (EditText) findViewById(R.id.etpwd2);
        btcode = (Button) findViewById(R.id.btcode);
        btcode.setOnClickListener(this);
        btnok = (Button) findViewById(R.id.btnok);
        btnok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnok:
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
                resetpwd();
            } else {
                Toast.makeText(context, "两次输入的密码不一致，请检查后重新输入", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SendCode() {
        // 组装参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", etphonenum.getText().toString().trim());
        params.put("vtype", "2");
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
                        etcode.setText(cr.getMessage());
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = SEND_ERROR_CODE;
                        msg.obj = response.code();
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

    private void resetpwd() {
        // 组装参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobile", etphonenum.getText().toString().trim());
        params.put("vcode", etcode.getText().toString().trim());
        params.put("password", etpwd.getText().toString().trim());
        OkHttpUtil.post(ContantsUrl.URL_RESERPWD, null, params, null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(RST_ERROR_NET);
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
                        etcode.setText(cr.getMessage());
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = RST_ERROR_CODE;
                        msg.obj = response.code();
                        handler.sendMessage(msg);
                    }
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = RST_ERROR_SERVER;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }
        });
    }
}
