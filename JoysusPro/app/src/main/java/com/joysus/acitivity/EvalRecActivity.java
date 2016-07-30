package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;

/**
 */
public class EvalRecActivity  extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_rec);
        setTitle("评论");
        showBackButton(true);
    }
}
