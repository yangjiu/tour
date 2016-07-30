package com.joysus.acitivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.adapter.FragmentViewPagerAdapter;
import com.joysus.fragment.support.SupportNotedFragment;
import com.joysus.fragment.support.SupportedFragment;
import com.joysus.fragment.support.SupportingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */

public class SupportActivity extends BaseActivity implements View.OnClickListener {

    public List<Fragment> fragments = new ArrayList<>();
    private ViewPager mViewPager;
    private FragmentViewPagerAdapter mAdapter;

    /**
     * 头部导航栏
     */
    LinearLayout supporting, supported, supportnoted;
    /**
     * 文字
     */
    TextView txtsupporting, txtsupported, txtsupportnoted;

    View v_ing, v_ed, v_not_ed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_main);
        initView();
        setTitle("支持");
        showBackButton(true);
    }

    private void initView() {
        supporting = (LinearLayout) findViewById(R.id.supportinglayout);
        supported = (LinearLayout) findViewById(R.id.supportedlayout);
        supportnoted = (LinearLayout) findViewById(R.id.supportnotedlayout);
        txtsupporting = (TextView) findViewById(R.id.supportingtxt);
        txtsupported = (TextView) findViewById(R.id.supportedtxt);
        txtsupportnoted = (TextView) findViewById(R.id.supportnotedtxt);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        v_ing = findViewById(R.id.v_ing);
        v_ed = findViewById(R.id.v_ed);
        v_not_ed = findViewById(R.id.v_not_ed);
        supporting.setOnClickListener(this);
        supported.setOnClickListener(this);
        supportnoted.setOnClickListener(this);
        SupportingFragment sif = new SupportingFragment();
        SupportedFragment sef = new SupportedFragment();
        SupportNotedFragment snef = new SupportNotedFragment();
        fragments.add(sif);
        fragments.add(sef);
        fragments.add(snef);
        mViewPager.setOffscreenPageLimit(1);
        mAdapter = new FragmentViewPagerAdapter(this.getSupportFragmentManager(), mViewPager, fragments);
        mAdapter.setOnExtraPageChangeListener(new FragmentViewPagerAdapter.OnExtraPageChangeListener() {
            @Override
            public void onExtraPageSelected(int position) {
//                resetTabBtn();
                switch (position) {
//                    case 0:
//                        rdo_home.setBackgroundResource(R.drawable.ceshirenwu_m);
//                        hometxt.setTextColor(Color.parseColor("#3767c6"));
//                        break;
//                    case 1:
//                        rdo_msg.setBackgroundResource(R.drawable.ceshirenwu_m);
//                        msgtxt.setTextColor(Color.parseColor("#3767c6"));
//                        break;
//                    case 2:
//                        rdo_group.setBackgroundResource(R.drawable.wode_m);
//                        gruoptxt.setTextColor(Color.parseColor("#3767c6"));
//                        break;
                    default:
                        break;
                }
            }
        });
    }

    // 重置 Tab 按钮样式
    protected void resetTabBtn() {
        txtsupporting.setTextColor(Color.parseColor("#666666"));
        txtsupported.setTextColor(Color.parseColor("#666666"));
        txtsupportnoted.setTextColor(Color.parseColor("#666666"));
        v_ing.setVisibility(View.INVISIBLE);
        v_ed.setVisibility(View.INVISIBLE);
        v_not_ed.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supportinglayout:
                resetTabBtn();
                txtsupporting.setTextColor(Color.parseColor("#fb5959"));
                v_ing.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.supportedlayout:
                resetTabBtn();
                txtsupported.setTextColor(Color.parseColor("#fb5959"));
                v_ed.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.supportnotedlayout:
                resetTabBtn();
                txtsupportnoted.setTextColor(Color.parseColor("#fb5959"));
                v_not_ed.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
