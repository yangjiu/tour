package com.joysus.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.adapter.VipReleaseAdapter;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.CommonResult;
import com.joysus.entity.RounteLineModel;
import com.joysus.okhttp.OkHttpUtil;
import com.joysus.util.ListViewHepler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 会员发布界面
 */
public class VIPReleaseActivity extends BaseActivity implements View.OnClickListener {
    public static VIPReleaseActivity self;
    private LinearLayout llback;
    private ListView lv_release;
    private VipReleaseAdapter adapter;
    private final static int LINE_SUCCESS = 1;
    private ArrayList<RounteLineModel> cm;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LINE_SUCCESS:
                    cm = (ArrayList<RounteLineModel>) msg.obj;
                    adapter = new VipReleaseAdapter(getApplicationContext(), cm);
                    lv_release.setAdapter(adapter);
                    ListViewHepler.setListViewHeightBasedOnChildren(lv_release);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_release);
        initView();
        getLineList();
        self = this;
    }

    private void initView() {
        llback = (LinearLayout) findViewById(R.id.ll_back);
        lv_release = (ListView) findViewById(R.id.lv_release);
        lv_release.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != cm) {
                    RounteLineModel clm = cm.get(position);
                    Intent intent = new Intent(getApplicationContext(), ProjectDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Id", clm.getId() + "");
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        llback.setOnClickListener(this);
    }

    // 获取所有线路
    private void getLineList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", "5");
        params.put("pageNo", "1");
        OkHttpUtil.post(ContantsUrl.URL_LINELIST, null, params, null, new Callback() {
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
                    ArrayList<RounteLineModel> cm = new ArrayList<RounteLineModel>();
                    for (int i = 0; i < obj.size(); i++) {
                        JSONObject jo = (JSONObject) obj.get(i);
                        cm.add(obj.toJavaObject(jo, RounteLineModel.class));
                    }
                    Message msg = new Message();
                    msg.what = LINE_SUCCESS;
                    msg.obj = cm;
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

    public void closeCurrent() {
        this.finish();
    }

    private void showToast(final String value) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 发起众筹 HTTP500
    private void Crowd_funding() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "31");
        params.put("departure", "project_desc");
        params.put("destination", "31");
        params.put("price", "10");
        params.put("startTime", System.currentTimeMillis() + "");
        params.put("endTime", (System.currentTimeMillis() + 500000) + "");
        params.put("lineId", "31");
        params.put("memberId", "123");
        params.put("status", "1");
        params.put("createTime", System.currentTimeMillis() + "");
        OkHttpUtil.post(ContantsUrl.URL_CROWDFUNDING, null, params, null, new Callback() {
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
                    CommonResult emm = JSONObject.toJavaObject(object,
                            CommonResult.class);
                    Log.d("LOGIN", "发起众筹");
                } else {

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
