package com.joysus.acitivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.InetAddressCodec;
import com.joysus.R;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.RounteLineModel;
import com.joysus.jpview.RatingBarEx;
import com.joysus.jpview.SeekBarEx;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 项目详情界面
 */
public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back;
    private RelativeLayout rightOneLayout;
    private static final int LINE_SUCCESS = 1;
    private static final int LINE_DETAIL_SUCCESS = 2;
    private ImageView iv_pic;
    private SeekBarEx seekBar;
    private TextView txttuanqi, txtluxian, txtjieshao, txtprice, txtcontent;
    private LinearLayout projectlayout, lawlayout, golayout;
    private TextView projecttxt, lawtxt, gotxt;
    private Button btnbm;
    private String id = "";
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LINE_SUCCESS:
                    RounteLineModel cm = (RounteLineModel) msg.obj;
                    LoadData(cm);
                    break;
                case LINE_DETAIL_SUCCESS:
                    txtcontent.setText(Html.fromHtml(String.valueOf(msg.obj)));
                    txtcontent.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        initView();
        id = this.getIntent().getStringExtra("Id").toString();
        getLineById(id);
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
        txtcontent = (TextView) findViewById(R.id.txtcontent);
        projectlayout = (LinearLayout) findViewById(R.id.projectlayout);
        lawlayout = (LinearLayout) findViewById(R.id.lawlayout);
        golayout = (LinearLayout) findViewById(R.id.golayout);
        projecttxt = (TextView) findViewById(R.id.projecttxt);
        lawtxt = (TextView) findViewById(R.id.lawtxt);
        gotxt = (TextView) findViewById(R.id.gotxt);
        btnbm = (Button) findViewById(R.id.btnbm);
        btnbm.setOnClickListener(this);
        projectlayout.setOnClickListener(this);
        lawlayout.setOnClickListener(this);
        golayout.setOnClickListener(this);
        projecttxt.setOnClickListener(this);
        lawtxt.setOnClickListener(this);
        gotxt.setOnClickListener(this);
        seekBar.setIndex(66);
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

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        projectlayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
        lawlayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
        golayout.setBackgroundColor(Color.parseColor("#F6F6F6"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rightOneLayout:
                break;
            case R.id.projectlayout:
            case R.id.projecttxt:
                resetTabBtn();
                projectlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                LineProDetail(id, "project_desc");
                break;
            case R.id.lawlayout:
            case R.id.lawtxt:
                resetTabBtn();
                lawlayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                LineProDetail(id, "rule_note");
                break;
            case R.id.golayout:
            case R.id.gotxt:
                resetTabBtn();
                golayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                LineProDetail(id, "before_note");
                break;
            case R.id.btnbm:
                if (null != VIPReleaseActivity.self) {
                    VIPReleaseActivity.self.closeCurrent();
                    this.finish();
                }
                break;
        }
    }

    private void showToast(final String value) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 获取所有的线路详情【众筹详情】

    private void getLineById(String id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        OkHttpUtil.post(ContantsUrl.URL_LINE, null, params, null, new Callback() {
            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    JSONObject object = JSONObject.parseObject(result);
                    String pString = object.getString("data");
                    JSONObject object1 = JSONObject.parseObject(pString);
                    RounteLineModel rmm = JSONObject.toJavaObject(object1,
                            RounteLineModel.class);
                    Message msg = new Message();
                    msg.what = LINE_SUCCESS;
                    msg.obj = rmm;
                    handler.sendMessage(msg);
                } else {
                    showToast("错误代码" + response.code() + ";数据获取失败");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                showToast("数据获取失败；请检查网络！");
            }
        });

    }

    // 线路项目详情页面
    private void LineProDetail(String id, String desc) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("col", desc);
        OkHttpUtil.post(ContantsUrl.URL_LINE_DETAIL, null, params, null, new Callback() {
            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                // 失败
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                // 获取code
                if (response.code() == 200) {
                    String result = response.body().string();
                    Message msg = new Message();
                    msg.what = LINE_DETAIL_SUCCESS;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } else {
                    showToast("错误代码" + response.code() + ";数据获取失败");
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                showToast("数据获取失败；请检查网络！");
            }
        });

    }
}
