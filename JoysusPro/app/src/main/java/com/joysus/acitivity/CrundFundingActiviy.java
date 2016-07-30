package com.joysus.acitivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.entity.RounteLineModel;
import com.joysus.jpview.RatingBarEx;
import com.joysus.jpview.SeekBarEx;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 */
public class CrundFundingActiviy extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back;
    private RelativeLayout rightOneLayout, as_rl_face;
    private static final int LINE_SUCCESS = 1;
    private static final int LINE_DETAIL_SUCCESS = 2;
    private ImageView iv_pic;
    private SeekBarEx seekBar;
    private RatingBarEx ratingBarEx;
    private TextView txttuanqi, txtluxian, txtjieshao, txtprice, txtcontent;
    private LinearLayout projectlayout, lawlayout, golayout, pinjialayout;
    private TextView projecttxt, lawtxt, gotxt, pinjiatxt;
    private String id = "";
    private Button btnokzhichi, btnokquane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crund_funding);
        setTitle("基本信息");
        showBackButton(true);
        initView();
        RounteLineModel cm = new RounteLineModel();
        cm.setStart_time("2015-06-06");
        cm.setEnd_time("2016-06-06");
        cm.setLine_desc("测试测试测试");
        cm.setPrice(5000);
        LoadData(cm);
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        rightOneLayout = (RelativeLayout) findViewById(R.id.rightOneLayout);
        ll_back.setOnClickListener(this);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        seekBar = (SeekBarEx) findViewById(R.id.seekBar);
        txttuanqi = (TextView) findViewById(R.id.txttuanqi);
        txtluxian = (TextView) findViewById(R.id.txtluxian);
        txtjieshao = (TextView) findViewById(R.id.txtjieshao);
        txtprice = (TextView) findViewById(R.id.txtprice);
        txtcontent = (TextView) findViewById(R.id.tv_content);
        projectlayout = (LinearLayout) findViewById(R.id.projectlayout);
        lawlayout = (LinearLayout) findViewById(R.id.lawlayout);
        golayout = (LinearLayout) findViewById(R.id.golayout);
        pinjialayout = (LinearLayout) findViewById(R.id.pinjialayout);
        projecttxt = (TextView) findViewById(R.id.projecttxt);
        lawtxt = (TextView) findViewById(R.id.lawtxt);
        gotxt = (TextView) findViewById(R.id.gotxt);
        pinjiatxt = (TextView) findViewById(R.id.pinjiatxt);
        ratingBarEx = (RatingBarEx) findViewById(R.id.ratingBar);
        btnokzhichi = (Button) findViewById(R.id.btnokzhichi);
        as_rl_face = (RelativeLayout) findViewById(R.id.as_rl_face);
        as_rl_face.setOnClickListener(this);
        btnokzhichi.setOnClickListener(this);
        btnokquane = (Button) findViewById(R.id.btnokquane);
        btnokquane.setOnClickListener(this);
        projectlayout.setOnClickListener(this);
        lawlayout.setOnClickListener(this);
        golayout.setOnClickListener(this);
        pinjialayout.setOnClickListener(this);
        projecttxt.setOnClickListener(this);
        lawtxt.setOnClickListener(this);
        gotxt.setOnClickListener(this);
        pinjiatxt.setOnClickListener(this);
        seekBar.setIndex(66);
        ratingBarEx.setRating(3.6f);
    }

    private void LoadData(RounteLineModel cm) {
        String tuanqi = "团期: <font color='#f8944b'>" + cm.getStart_time() + "</font> 至 <font color='#f8944b'>" + cm.getEnd_time() + "</font>";
        txttuanqi.setText(Html.fromHtml(tuanqi));
        txttuanqi.setMovementMethod(LinkMovementMethod.getInstance());
        String luxian = "路线: <font color='#343434'>" + cm.getLine_desc() + "</font>";
        txtluxian.setText(Html.fromHtml(luxian));
        txtluxian.setMovementMethod(LinkMovementMethod.getInstance());
        txtjieshao.setText(cm.getStroke_desc());
        String prices = "<font color='red'>￥" + cm.getPrice() + "</font> 起";
        txtprice.setText(Html.fromHtml(prices));
        txtprice.setMovementMethod(LinkMovementMethod.getInstance());
    }

    // 线路项目详情页面
    private void LineProDetail() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "31");
        params.put("col", "project_desc");
        String url = "http://121.42.174.46/yueyuehui/admin/pages/showLine.jsp";
        OkHttpUtil.post(url, null, params, null, new Callback() {
            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();

                    Log.d("LOGIN", "线路详情【众筹详情】");
                } else {

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });

    }

    //重置Tab按钮样式
    protected void resetTabBtn() {
        projectlayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
        lawlayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
        golayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
        pinjialayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnokzhichi:
                Intent in1 = new Intent(this, SupportJoinActivity.class);
                startActivity(in1);
                break;
            case R.id.btnokquane:
                Intent in2 = new Intent(this, SupportAllActiviy.class);
                startActivity(in2);
                break;
            case R.id.projecttxt:
                resetTabBtn();
                projectlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtcontent.setText("项目介绍");
                break;
            case R.id.lawlayout:
            case R.id.lawtxt:
                resetTabBtn();
                lawlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtcontent.setText("规则说明");
                break;
            case R.id.golayout:
            case R.id.gotxt:
                resetTabBtn();
                golayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtcontent.setText("爱心天使");
                break;
            case R.id.pinjialayout:
            case R.id.pinjiatxt:
                resetTabBtn();
                pinjialayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                txtcontent.setText("评价");
                break;
            case R.id.as_rl_face:
                Intent in = new Intent(this, PersonDetailActivity.class);
                startActivity(in);
                break;
        }
    }
}
