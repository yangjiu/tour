package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 * Created by Administrator on 2016/6.
 */
public class PersonCardActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_modfy);
        setTitle("编辑身份");
        showBackButton(false);
    }
}
