package com.joysus.acitivity;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.joysus.R;
import com.joysus.adapter.FragmentViewPagerAdapter;
import com.joysus.app.joyapp;
import com.joysus.constant.ContantsUrl;
import com.joysus.entity.EveryMoneyModel;
import com.joysus.fragment.ChatGroupFramgment;
import com.joysus.fragment.ChatMsgFramgment;
import com.joysus.fragment.FirstDialogFragment;
import com.joysus.fragment.HomeFragment;
import com.joysus.fragment.MyFragment;
import com.joysus.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by howso on 2016.06.16
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    public List<Fragment> fragments = new ArrayList<>();
    private ViewPager mViewPager;
    private FragmentViewPagerAdapter mAdapter;
    private CusWindowsFoucusChanged mCusWindowsFoucusChanged;
    /**
     * 底部4个按钮
     */
    LinearLayout msglayout;
    LinearLayout grouplayout;
    LinearLayout homelayout;
    LinearLayout mylaoyout;
    /**
     * 底部背景
     */
    ImageButton rdo_msg;
    ImageButton rdo_group;
    ImageButton rdo_home;
    ImageButton rdo_my;

    /**
     * 底部文字
     */
    TextView msgtxt;
    TextView gruoptxt;
    TextView hometxt;
    TextView mytxt;
    String basicDialog = "sss";
    private final static int PROMO_SUCCESS = 5;
    private final static int PROMO_ERROR_CODE = 6;
    private final static int PROMO_ERROR_SERVER = 7;
    private final static int PROMO_ERROR_NET = 8;
    // handler 统一处理消息
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case PROMO_SUCCESS:
                    EveryMoneyModel emm = (EveryMoneyModel) msg.obj;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    FirstDialogFragment.newIns(emm.getTitle() + emm.getValue() + "元").show(ft, basicDialog);
//                    dialogFragment.show(ft, basicDialog);
//                    dialogFragment.setTextView(emm.getTitle());
                    break;
                case PROMO_ERROR_CODE:
                    Toast.makeText(context, "服务器正在维护中！", Toast.LENGTH_LONG).show();
                    break;
                case PROMO_ERROR_SERVER:
                    Toast.makeText(context, "服务器错误,代码：" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case PROMO_ERROR_NET:
                    Toast.makeText(context, "请检查网络连接情况", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public void setCusWindowsFoucusChanged(CusWindowsFoucusChanged cws) {
        this.mCusWindowsFoucusChanged = cws;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        joyapp.getIns().arries.add(this);
        init();
        /**
         * 为了不重复显示dialog，在显示对话框之前移除正在显示的对话框。
         */

//        Fragment fragment = getFragmentManager().findFragmentByTag(basicDialog);
//        if (null != fragment) {
//            ft.remove(fragment);
//        }

        /**
         * 0:默认样式
         * 1：无标题样式
         * 2：无边框样式
         * 3：不可输入，不可获得焦点样式
         * 可根据参数不同执行测试这几种样式的对话框。
         */
    }

    private void init() {
        homelayout = (LinearLayout) findViewById(R.id.homelayout);
        mylaoyout = (LinearLayout) findViewById(R.id.mylayout);
        msglayout = (LinearLayout) findViewById(R.id.msglayout);
        grouplayout = (LinearLayout) findViewById(R.id.grouplayout);
        rdo_home = (ImageButton) findViewById(R.id.rdo_home);
        rdo_my = (ImageButton) findViewById(R.id.rdo_my);
        rdo_msg = (ImageButton) findViewById(R.id.rdo_msg);
        rdo_group = (ImageButton) findViewById(R.id.rdo_group);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        hometxt = (TextView) findViewById(R.id.hometxt);
        mytxt = (TextView) findViewById(R.id.mytxt);
        msgtxt = (TextView) findViewById(R.id.msgtxt);
        gruoptxt = (TextView) findViewById(R.id.gruoptxt);

        msglayout.setOnClickListener(this);
        grouplayout.setOnClickListener(this);
        homelayout.setOnClickListener(this);
        mylaoyout.setOnClickListener(this);
        rdo_home.setOnClickListener(this);
        rdo_my.setOnClickListener(this);
        rdo_msg.setOnClickListener(this);
        rdo_group.setOnClickListener(this);
        HomeFragment hf = new HomeFragment();
        ChatMsgFramgment cmf = new ChatMsgFramgment();
        ChatGroupFramgment cgf = new ChatGroupFramgment();
        MyFragment mf = new MyFragment();
        fragments.add(hf);
        fragments.add(cmf);
        fragments.add(cgf);
        fragments.add(mf);
        rdo_home.setBackgroundResource(R.mipmap.home_h);
        hometxt.setTextColor(Color.parseColor("#ff8e8e"));
        mViewPager.setOffscreenPageLimit(1);
        mAdapter = new FragmentViewPagerAdapter(this.getSupportFragmentManager(), mViewPager, fragments);
        mAdapter.setOnExtraPageChangeListener(new FragmentViewPagerAdapter.OnExtraPageChangeListener() {
            @Override
            public void onExtraPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        rdo_home.setBackgroundResource(R.mipmap.home_h);
                        hometxt.setTextColor(Color.parseColor("#ff8e8e"));
                        break;
                    case 1:
                        rdo_msg.setBackgroundResource(R.mipmap.xiaoxi_h);
                        msgtxt.setTextColor(Color.parseColor("#ff8e8e"));
                        break;
                    case 2:
                        rdo_group.setBackgroundResource(R.mipmap.map_h);
                        gruoptxt.setTextColor(Color.parseColor("#ff8e8e"));
                        break;
                    case 3:
                        rdo_my.setBackgroundResource(R.mipmap.geren_h);
                        mytxt.setTextColor(Color.parseColor("#ff8e8e"));
                        break;
                    default:
                        break;
                }
            }
        });
        promotion();
    }

    // 获取每天第一次登录奖励金额
    private void promotion() {
        OkHttpUtil.get(ContantsUrl.URL_PROMOTION, new Callback() {
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
                    String data = object.getString("data");
                    JSONObject object1 = JSONObject.parseObject(data);
                    try {
                        EveryMoneyModel emm = JSONObject.toJavaObject(object1,
                                EveryMoneyModel.class);
                        if (null != emm) {
                            // 失败
                            Message msg = new Message();
                            msg.what = PROMO_SUCCESS;
                            msg.obj = emm;
                            handler.sendMessage(msg);
                        } else {
                            // 失败
                            Message msg = new Message();
                            msg.what = PROMO_ERROR_SERVER;
                            msg.obj = response.code();
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        // 失败
                        Message msg = new Message();
                        msg.what = PROMO_ERROR_CODE;
                        msg.obj = response.code();
                        handler.sendMessage(msg);
                    }
                } else {
                    // 失败
                    Message msg = new Message();
                    msg.what = PROMO_ERROR_SERVER;
                    msg.obj = response.code();
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(PROMO_ERROR_NET);
            }
        });
    }

    /**
     * 重置Tab样式
     */
    protected void resetTabBtn() {
        rdo_home.setBackgroundResource(R.mipmap.home);
        rdo_msg.setBackgroundResource(R.mipmap.xiaoxi);
        rdo_group.setBackgroundResource(R.mipmap.renmai);
        rdo_my.setBackgroundResource(R.mipmap.geren);
        hometxt.setTextColor(Color.parseColor("#666666"));
        gruoptxt.setTextColor(Color.parseColor("#666666"));
        msgtxt.setTextColor(Color.parseColor("#666666"));
        mytxt.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdo_home:
            case R.id.homelayout:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.msglayout:
            case R.id.rdo_msg:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.grouplayout:
            case R.id.rdo_group:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.rdo_my:
            case R.id.mylayout:
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (mCusWindowsFoucusChanged != null) {
                mCusWindowsFoucusChanged.FoucusChanged();
            }
        }
    }

    public interface CusWindowsFoucusChanged {
        void FoucusChanged();
    }

    ;
}
