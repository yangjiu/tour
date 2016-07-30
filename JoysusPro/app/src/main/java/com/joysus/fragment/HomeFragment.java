package com.joysus.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.acitivity.CitySelectedActivity;
import com.joysus.acitivity.CrundFundingActiviy;
import com.joysus.acitivity.HomeActivity;
import com.joysus.acitivity.LoginActivity;
import com.joysus.acitivity.RankingActivity;
import com.joysus.acitivity.VIPReleaseActivity;
import com.joysus.adapter.TripAdapter;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.CommonResult;
import com.joysus.entity.TripModel;
import com.joysus.jpview.TopScrollView;
import com.joysus.jpview.flashview.FlashView;
import com.joysus.jpview.flashview.constants.EffectConstants;
import com.joysus.okhttp.OkHttpUtil;
import com.joysus.util.HttpClientUtil;
import com.joysus.util.ListViewHepler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by howso on 2016.06.24.
 */

public class HomeFragment extends BaseFragment implements TopScrollView.OnScrollListener, View.OnClickListener {
    //定义自己的类的对象
    public static HomeFragment self;
    private View v;
    private FlashView flashView;
    private ArrayList<String> imageUrls = new ArrayList<String>();
    private TopScrollView myScrollView;
    private int searchLayoutTop;
    private LinearLayout search01, search02;
    private HomeActivity homeActivity;
    private ImageView iv_paihang, imgdeit;
    private Button btnxinshou;
    private TextView hometxt, msgtxt, gruoptxt, hometxt1, msgtxt1, gruoptxt1, tv_cityselected;
    private LinearLayout homelayout, msglayout, grouplayout, homelayout1, msglayout1, grouplayout1, tabbottom1, ll_left;
    private View v_home, v_msg, v_group, v_home1, v_msg1, v_group1;
    private final static int BANNER_SUCCESS = 0;
    private final static int BANNER_ERROR_CODE = 1;
    private final static int BANNER_ERROR_SERVER = 2;
    private final static int BANNER_ERROR_NET = 3;
    private final static int TRIP_SUCCESS = 4;
    private final static int TRIP_ERROR_CODE = 5;
    private final static int TRIP_ERROR_SERVER = 6;
    private final static int TRIP_ERROR_NET = 7;
    private ListView tList;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case BANNER_SUCCESS:
                    flashView.setImageUris(imageUrls);
                    flashView.setEffect(EffectConstants.DEFAULT_EFFECT);// 更改图片切换的动画效果
                    break;
                case TRIP_ERROR_CODE:
                case BANNER_ERROR_CODE:
                    Toast.makeText(getActivity(), "数据获取失败，服务器正在维护中！", Toast.LENGTH_LONG).show();
                    break;
                case TRIP_ERROR_SERVER:
                case BANNER_ERROR_SERVER:
                    Toast.makeText(getActivity(), "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case TRIP_ERROR_NET:
                case BANNER_ERROR_NET:
                    Toast.makeText(getActivity(), "数据获取失败，请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
                case TRIP_SUCCESS:
                    ArrayList<TripModel> cm = (ArrayList<TripModel>) msg.obj;
                    TripAdapter adapter = new TripAdapter(getActivity(), cm);
                    tList.setAdapter(adapter);
                    ListViewHepler.setListViewHeightBasedOnChildren(tList);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fram_home, container, false);
        initView();
        GetBanner();
        TripList();
        initData();
        self = this;
        return v;
    }
    private void initView() {
        flashView = (FlashView) v.findViewById(R.id.flash_view);
        search01 = (LinearLayout) v.findViewById(R.id.search01);
        search02 = (LinearLayout) v.findViewById(R.id.search02);
        tList = (ListView) v.findViewById(R.id.lv_trip);
        hometxt = (TextView) v.findViewById(R.id.hometxt);
        msgtxt = (TextView) v.findViewById(R.id.msgtxt);
        gruoptxt = (TextView) v.findViewById(R.id.gruoptxt);
        hometxt1 = (TextView) v.findViewById(R.id.hometxt1);
        msgtxt1 = (TextView) v.findViewById(R.id.msgtxt1);
        gruoptxt1 = (TextView) v.findViewById(R.id.gruoptxt1);
        tv_cityselected = (TextView) v.findViewById(R.id.tv_cityselected);
        iv_paihang = (ImageView) v.findViewById(R.id.iv_paihang);
        homelayout = (LinearLayout) v.findViewById(R.id.homelayout);
        msglayout = (LinearLayout) v.findViewById(R.id.msglayout);
        grouplayout = (LinearLayout) v.findViewById(R.id.grouplayout);
        homelayout1 = (LinearLayout) v.findViewById(R.id.homelayout1);
        msglayout1 = (LinearLayout) v.findViewById(R.id.msglayout1);
        grouplayout1 = (LinearLayout) v.findViewById(R.id.grouplayout1);
        tabbottom1 = (LinearLayout) v.findViewById(R.id.tabbottom1);
        btnxinshou = (Button) v.findViewById(R.id.btnxinshou);
        imgdeit = (ImageView) v.findViewById(R.id.imgdeit);
        imgdeit.setOnClickListener(this);
        btnxinshou.setOnClickListener(this);
        v_home = v.findViewById(R.id.v_home);
        v_group = v.findViewById(R.id.v_group);
        v_msg = v.findViewById(R.id.v_msg);
        v_home1 = v.findViewById(R.id.v_home1);
        v_group1 = v.findViewById(R.id.v_group1);
        v_msg1 = v.findViewById(R.id.v_msg1);
        ll_left = (LinearLayout) v.findViewById(R.id.ll_left);
        iv_paihang.setOnClickListener(this);
        ll_left.setOnClickListener(this);
        hometxt.setOnClickListener(this);
        msgtxt.setOnClickListener(this);
        gruoptxt.setOnClickListener(this);
        hometxt1.setOnClickListener(this);
        msgtxt1.setOnClickListener(this);
        gruoptxt1.setOnClickListener(this);
        homelayout.setOnClickListener(this);
        msglayout.setOnClickListener(this);
        grouplayout.setOnClickListener(this);
        homelayout1.setOnClickListener(this);
        msglayout1.setOnClickListener(this);
        grouplayout1.setOnClickListener(this);
        tList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        myScrollView = (TopScrollView) v.findViewById(R.id.myScrollView);
        myScrollView.setOnScrollListener(this);
    }

    public void setCity(String value) {
        if (null != tv_cityselected) {
            tv_cityselected.setText(value);
        }
    }

    private void GetBanner() {
        // 组装参数
        OkHttpUtil.get(ContantsUrl.URL_BANNER, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(BANNER_ERROR_NET);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 失败
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                // 获取code
                if (response.code() == 200) {
                    // 判断类型
                    String result = response.body().string();
                    JSONObject object = JSONObject.parseObject(result);
                    CommonResult cr = JSONObject.toJavaObject(object, CommonResult.class);
                    if (cr.getCode().equals("1")) {
                        // Json解析
                        try {
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            String array = jsonObject.getString("data");
                            JSONObject obj = new JSONObject().parseObject(array);
                            JSONArray jsonArray = obj.getJSONArray("list");

                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject jo = (JSONObject) jsonArray.get(i);
                                imageUrls.add(ContantsUrl.BASE_URL + jo.getString("path"));
                            }
                            handler.sendEmptyMessage(BANNER_SUCCESS);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 失败
                        Message msg = new Message();
                        msg.what = BANNER_ERROR_CODE;
                        msg.obj = response.code();
                        handler.sendMessage(msg);
                    }
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = BANNER_ERROR_SERVER;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }

        });
    }

    //获取首页行程
    private void TripList() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("orderby", "create_time");
        params.put("pageSize", "3");
        params.put("pageNo", "1");
        OkHttpUtil.post(ContantsUrl.URL_TRIPLIST, null, params, null, new Callback() {
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
                    ArrayList<TripModel> cm = new ArrayList<TripModel>();
                    for (int i = 0; i < obj.size(); i++) {
                        JSONObject jo = (JSONObject) obj.get(i);
                        cm.add(obj.toJavaObject(jo, TripModel.class));
                    }
                    // 失败
                    Message msg = new Message();
                    msg.what = TRIP_SUCCESS;
                    msg.obj = cm;
                    handler.sendMessage(msg);
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = TRIP_ERROR_CODE;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(TRIP_ERROR_NET);
            }
        });
    }

    private void initData() {
        homeActivity = (HomeActivity) this.getActivity();
        homeActivity.setCusWindowsFoucusChanged(new HomeActivity.CusWindowsFoucusChanged() {
            @Override
            public void FoucusChanged() {
                searchLayoutTop = flashView.getBottom();//获取searchLayout的顶部位置
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= searchLayoutTop) {
            if (search01.getVisibility() == View.INVISIBLE) {
                search02.setVisibility(View.INVISIBLE);
                search01.setVisibility(View.VISIBLE);
                tabbottom1.setVisibility(View.VISIBLE);
            }
        } else {
            if (search01.getVisibility() == View.VISIBLE) {
                search01.setVisibility(View.INVISIBLE);
                tabbottom1.setVisibility(View.INVISIBLE);
                search02.setVisibility(View.VISIBLE);
            }
        }
    }

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        hometxt.setTextColor(Color.parseColor("#666666"));
        hometxt1.setTextColor(Color.parseColor("#666666"));
        msgtxt.setTextColor(Color.parseColor("#666666"));
        msgtxt1.setTextColor(Color.parseColor("#666666"));
        gruoptxt.setTextColor(Color.parseColor("#666666"));
        gruoptxt1.setTextColor(Color.parseColor("#666666"));
        v_home.setVisibility(View.INVISIBLE);
        v_group.setVisibility(View.INVISIBLE);
        v_msg.setVisibility(View.INVISIBLE);
        v_home1.setVisibility(View.INVISIBLE);
        v_group1.setVisibility(View.INVISIBLE);
        v_msg1.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hometxt:
            case R.id.hometxt1:
            case R.id.homelayout:
            case R.id.homelayout1:
                resetTabBtn();
                hometxt.setTextColor(Color.parseColor("#fb5959"));
                hometxt1.setTextColor(Color.parseColor("#fb5959"));
                v_home.setVisibility(View.VISIBLE);
                v_home1.setVisibility(View.VISIBLE);
                break;
            case R.id.msgtxt:
            case R.id.msgtxt1:
            case R.id.msglayout:
            case R.id.msglayout1:
                resetTabBtn();
                msgtxt.setTextColor(Color.parseColor("#fb5959"));
                msgtxt1.setTextColor(Color.parseColor("#fb5959"));
                v_msg.setVisibility(View.VISIBLE);
                v_msg1.setVisibility(View.VISIBLE);
                break;
            case R.id.gruoptxt:
            case R.id.gruoptxt1:
            case R.id.grouplayout:
            case R.id.grouplayout1:
                resetTabBtn();
                gruoptxt.setTextColor(Color.parseColor("#fb5959"));
                gruoptxt1.setTextColor(Color.parseColor("#fb5959"));
                v_group.setVisibility(View.VISIBLE);
                v_group1.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_left:
                Intent intent = new Intent(getActivity(), CitySelectedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("city", tv_cityselected.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.iv_paihang:
                Intent intentpaihang = new Intent(getActivity(), RankingActivity.class);
                startActivity(intentpaihang);
                break;
            case R.id.btnxinshou:
                // 实例化TestDialog,可以传参数进去,例如标题,或者其他参数,还有一个唯一码.
                WelcomeFragment.newIns("欢迎欢迎欢迎！！！！！！！").show(getActivity().getFragmentManager().beginTransaction(), "welcome");
                break;
            case R.id.imgdeit:
                Intent intente = new Intent(getActivity(), VIPReleaseActivity.class);
                startActivity(intente);
                break;

        }
    }
}
