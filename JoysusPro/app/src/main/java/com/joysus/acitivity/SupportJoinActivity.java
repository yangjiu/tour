package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 */
public class SupportJoinActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_join);
        setTitle("感谢您的参与");
        showBackButton(true);
    }
}
