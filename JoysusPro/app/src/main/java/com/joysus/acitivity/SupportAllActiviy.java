package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 */
public class SupportAllActiviy extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_all);
        setTitle("全额付款");
        showBackButton(true);
    }
}
