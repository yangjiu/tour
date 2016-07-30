package com.joysus.acitivity;

import android.os.Bundle;

import com.joysus.R;
import com.joysus.jpview.RatingBarEx;

/**
 */
public class PersonDetailActivity extends BaseActivity {
    private RatingBarEx ratingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        setTitle("");
        showBackButton(true);
        initView();
    }

    private void initView() {
        ratingBar = (RatingBarEx) findViewById(R.id.ratingBar);
        ratingBar.setRating(3.6f);
    }
}
