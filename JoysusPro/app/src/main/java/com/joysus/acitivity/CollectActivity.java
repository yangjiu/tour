package com.joysus.acitivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.jpview.RatingBarEx;
import com.joysus.jpview.SeekBarEx;

/**
 * Created by Administrator on 2016/6/26.
 */
public class CollectActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout viplayout, prolayout;
    private TextView viptxt, protxt;
    private View v_vip, v_pro;
    private RatingBarEx ratingBarEx, ratingBarEx1;
    private SeekBarEx seekBarEx;
    private LinearLayout llright,llbottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_activity);
        setTitle("收藏");
        showBackButton(true);
        initView();
    }

    private void initView() {
        llbottom = (LinearLayout) findViewById(R.id.llbottom);
        llright = (LinearLayout) findViewById(R.id.llright);
        viplayout = (LinearLayout) findViewById(R.id.viplayout);
        prolayout = (LinearLayout) findViewById(R.id.prolayout);
        viptxt = (TextView) findViewById(R.id.viptxt);
        protxt = (TextView) findViewById(R.id.protxt);
        v_vip = findViewById(R.id.v_vip);
        v_pro = findViewById(R.id.v_pro);
        ratingBarEx = (RatingBarEx) findViewById(R.id.ratingBar);
        ratingBarEx1 = (RatingBarEx) findViewById(R.id.ratingBar1);
        seekBarEx = (SeekBarEx) findViewById(R.id.seekBar);
        seekBarEx.setIndex(88);
        ratingBarEx.setRating(3.6f);
        ratingBarEx1.setRating(3.6f);
        viplayout.setOnClickListener(this);
        prolayout.setOnClickListener(this);
        viptxt.setOnClickListener(this);
        protxt.setOnClickListener(this);

    }

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        viptxt.setTextColor(Color.parseColor("#666666"));
        protxt.setTextColor(Color.parseColor("#666666"));
        v_vip.setVisibility(View.INVISIBLE);
        v_pro.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viplayout:
            case R.id.viptxt:
                resetTabBtn();
                viptxt.setTextColor(Color.parseColor("#fb5959"));
                v_vip.setVisibility(View.VISIBLE);
                llbottom.setVisibility(View.VISIBLE);
                llright.setVisibility(View.INVISIBLE);
                break;
            case R.id.prolayout:
            case R.id.protxt:
                resetTabBtn();
                protxt.setTextColor(Color.parseColor("#fb5959"));
                v_pro.setVisibility(View.VISIBLE);
                llbottom.setVisibility(View.GONE);
                llright.setVisibility(View.VISIBLE);
                break;
        }
    }
}
