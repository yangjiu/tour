package com.joysus.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.app.joyapp;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.CommonResult;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/26.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout as_rl_logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("设置");
        showBackButton(true);
        initView();
        joyapp.getIns().arries.add(this);
    }

    private void initView() {
        as_rl_logout = (LinearLayout) findViewById(R.id.as_rl_logout);
        as_rl_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.as_rl_logout:
                logout();
                break;
        }
    }

    private void showToast(final String value) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        OkHttpUtil.post(ContantsUrl.URL_LOGOUT, new Callback() {
            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    JSONObject object = JSONObject.parseObject(result);
                    CommonResult emm = JSONObject.toJavaObject(object,
                            CommonResult.class);
                    if (emm.getCode().equals("1")) {
                        if (joyapp.getIns().arries.size() > 0) {
                            for (Activity act : joyapp.getIns().arries) {
                                if (null != act) {
                                    act.finish();
                                }
                            }
                            Intent intentpaihang = new Intent(SettingActivity.this, LoginActivity.class);
                            startActivity(intentpaihang);
                        }
                    }
                } else {
                    showToast("错误代码" + response.code() + ";退出失败");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                showToast("退出失败；请检查网络！");
            }
        });
    }
}
