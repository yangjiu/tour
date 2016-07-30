package com.joysus.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joysus.R;

/**
 * Created by Administrator on 2016/6/26.
 */
public class EvaluateActivity extends BaseActivity implements View.OnClickListener {
    private Button btnpingjia;
    private LinearLayout evluamelayout, evluaotherlayout, left, right;
    private TextView evluatxt, evluaedtxt;
    private View v_evluame, v_evluaother;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        setTitle("评价");
        showBackButton(true);
        initView();
    }

    private void initView() {
        left = (LinearLayout) findViewById(R.id.left);
        right = (LinearLayout) findViewById(R.id.right);
        evluamelayout = (LinearLayout) findViewById(R.id.evluamelayout);
        evluaotherlayout = (LinearLayout) findViewById(R.id.evluaotherlayout);
        evluatxt = (TextView) findViewById(R.id.evluatxt);
        evluaedtxt = (TextView) findViewById(R.id.evluaedtxt);
        evluamelayout.setOnClickListener(this);
        evluaotherlayout.setOnClickListener(this);
        evluaedtxt.setOnClickListener(this);
        evluamelayout.setOnClickListener(this);
        v_evluame = findViewById(R.id.v_evluame);
        v_evluaother = findViewById(R.id.v_evluaother);

        btnpingjia = (Button) findViewById(R.id.btnpingjia);
        btnpingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EvaluateActivity.this, EvalRecActivity.class);
                startActivity(in);
            }
        });
    }

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        evluatxt.setTextColor(Color.parseColor("#666666"));
        evluaedtxt.setTextColor(Color.parseColor("#666666"));
        v_evluame.setVisibility(View.INVISIBLE);
        v_evluaother.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evluamelayout:
            case R.id.evluatxt:
                resetTabBtn();
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.GONE);
                evluatxt.setTextColor(Color.parseColor("#fb5959"));
                v_evluame.setVisibility(View.VISIBLE);
                break;
            case R.id.evluaotherlayout:
            case R.id.evluaedtxt:
                resetTabBtn();
                left.setVisibility(View.GONE);
                right.setVisibility(View.VISIBLE);
                evluaedtxt.setTextColor(Color.parseColor("#fb5959"));
                v_evluaother.setVisibility(View.VISIBLE);
                break;
        }
    }
}
