package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 * Created by Administrator on 2016/6.
 */
public class NickNameActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname_modfy);
        setTitle("编辑昵称");
        showBackButton(false);
    }
}
