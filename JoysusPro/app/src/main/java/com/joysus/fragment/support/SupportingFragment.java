package com.joysus.fragment.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.acitivity.MyNumberActivity;
import com.joysus.fragment.BaseFragment;
import com.joysus.jpview.SeekBarEx;

/**
 * Created by Administrator on 2016/6/26.
 */

public class SupportingFragment extends BaseFragment implements View.OnClickListener {
    private View v;
    private SeekBarEx seekBar;
    private TextView tv_phonenumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.support_ing, container, false);
        initView();
        return v;
    }

    private void initView() {
        seekBar = (SeekBarEx) v.findViewById(R.id.seekBar);
        tv_phonenumber = (TextView) v.findViewById(R.id.tv_phonenumber);
        tv_phonenumber.setOnClickListener(this);
        seekBar.setIndex(66);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_phonenumber:
                Intent in=new Intent(getActivity(), MyNumberActivity.class);
                startActivity(in);
                break;
        }
    }
}
