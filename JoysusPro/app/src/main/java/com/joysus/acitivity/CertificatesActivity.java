package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 * Created by Administrator on 2016/6/29.
 */
public class CertificatesActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);
        setTitle("编辑资料");
        showBackButton(true);
    }
}
