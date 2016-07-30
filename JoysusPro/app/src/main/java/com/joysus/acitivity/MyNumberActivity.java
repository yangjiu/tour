package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 */
public class MyNumberActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynumber);
        setTitle("我的号码");
        showBackButton(true);
    }
}
