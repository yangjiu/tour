package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;
import com.joysus.jpview.RatingBarEx;
import com.joysus.jpview.SeekBarEx;

/**
 * Created by Administrator on 2016/6/26.
 */

public class MyLanuchActivity extends BaseActivity {
    private RatingBarEx ratingBarEx;
    private SeekBarEx seekBarEx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylanuch);
        setTitle("发起");
        showBackButton(true);
        initView();
    }

    private void initView() {
        ratingBarEx = (RatingBarEx) findViewById(R.id.ratingBar);
        seekBarEx = (SeekBarEx) findViewById(R.id.seekBar);
        ratingBarEx.setRating(3.6f);
        seekBarEx.setIndex(88);
    }
}
