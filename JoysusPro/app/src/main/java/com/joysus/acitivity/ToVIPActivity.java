package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 * 升级成VIP
 */

public class ToVIPActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_vip);
        setTitle("升级VIP");
        showBackButton(true);
    }
}
