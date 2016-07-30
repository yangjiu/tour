package com.joysus.acitivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.adapter.TripOrderAdapter;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.TripOrder;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 排行页面
 */

public class RankingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout yearlayout, quarterlayout;
    private TextView yeartv, quartertv;
    private View v_year, v_quarter;
    private ListView lv_rank;
    private final static int TRIPORDER_SUCCESS = 1;
    private final static int TRIPORDER_ERROR_CODE = 2;
    private final static int TRIPORDER_ERROR_SERVER = 3;
    private final static int TRIPORDER_ERROR_NET = 4;
    private TripOrderAdapter adapter;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case TRIPORDER_SUCCESS:
                    ArrayList<TripOrder> cm = (ArrayList<TripOrder>) msg.obj;
                    adapter = new TripOrderAdapter(RankingActivity.this, cm);
                    lv_rank.setAdapter(adapter);
                    break;
                case TRIPORDER_ERROR_CODE:
                case TRIPORDER_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case TRIPORDER_ERROR_NET:
                    Toast.makeText(context, "数据获取失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        setTitle("排行榜");
        showBackButton(true);
        intiView();
        TripOrderList();
    }

    private void intiView() {
        yearlayout = (LinearLayout) findViewById(R.id.yearlayout);
        quarterlayout = (LinearLayout) findViewById(R.id.quarterlayout);
        yeartv = (TextView) findViewById(R.id.yeartv);
        quartertv = (TextView) findViewById(R.id.quartertv);
        v_year = findViewById(R.id.v_year);
        v_quarter = findViewById(R.id.v_quarter);
        lv_rank = (ListView) findViewById(R.id.lv_rank);
        yearlayout.setOnClickListener(this);
        quarterlayout.setOnClickListener(this);
        yeartv.setOnClickListener(this);
        quartertv.setOnClickListener(this);
    }

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        yeartv.setTextColor(Color.parseColor("#666666"));
        quartertv.setTextColor(Color.parseColor("#666666"));
        v_year.setVisibility(View.INVISIBLE);
        v_quarter.setVisibility(View.INVISIBLE);
    }

    // 21排行榜
    private void TripOrderList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", "2015-09-01");
        params.put("endTime", "2015-12-01");
        params.put("pageSize ", "5");
        params.put("pageNo ", "1");
        OkHttpUtil.post(ContantsUrl.URL_TRIPORDER, null, params, null, new Callback() {
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
                    String pString1 = object1.getString("list");
                    JSONArray obj = new JSONObject().parseArray(pString1);
                    ArrayList<TripOrder> cm = new ArrayList<TripOrder>();
                    for (int i = 0; i < obj.size(); i++) {
                        JSONObject jo = (JSONObject) obj.get(i);
                        cm.add(obj.toJavaObject(jo, TripOrder.class));
                    }
                    Message msg = new Message();
                    msg.what = TRIPORDER_SUCCESS;
                    msg.obj = cm;
                    handler.sendMessage(msg);
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = TRIPORDER_ERROR_CODE;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(TRIPORDER_ERROR_NET);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yearlayout:
            case R.id.yeartv:
                resetTabBtn();
                yeartv.setTextColor(Color.parseColor("#fb5959"));
                v_year.setVisibility(View.VISIBLE);
                break;
            case R.id.quarterlayout:
            case R.id.quartertv:
                resetTabBtn();
                quartertv.setTextColor(Color.parseColor("#fb5959"));
                v_quarter.setVisibility(View.VISIBLE);
                break;
        }
    }
}
