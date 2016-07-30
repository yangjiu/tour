package com.joysus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.joysus.R;
import com.joysus.acitivity.CollectActivity;
import com.joysus.acitivity.EditPersonInfoActivity;
import com.joysus.acitivity.EvaluateActivity;
import com.joysus.acitivity.MyLanuchActivity;
import com.joysus.acitivity.SettingActivity;
import com.joysus.acitivity.SupportActivity;


/**
 * Created by howso on 2016.06.24.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private View v;
    private RelativeLayout as_rl_support, as_rl_lanuch, as_rl_collect, as_rl_evaluate, as_rl_set,as_rl_face;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fram_my, container, false);
        initView();
        return v;
    }

    private void initView() {
        as_rl_support = (RelativeLayout) v.findViewById(R.id.as_rl_support);
        as_rl_lanuch = (RelativeLayout) v.findViewById(R.id.as_rl_lanuch);
        as_rl_collect = (RelativeLayout) v.findViewById(R.id.as_rl_collect);
        as_rl_evaluate = (RelativeLayout) v.findViewById(R.id.as_rl_evaluate);
        as_rl_set = (RelativeLayout) v.findViewById(R.id.as_rl_set);
        as_rl_face=(RelativeLayout)v.findViewById(R.id.as_rl_face);
        as_rl_support.setOnClickListener(this);
        as_rl_lanuch.setOnClickListener(this);
        as_rl_collect.setOnClickListener(this);
        as_rl_evaluate.setOnClickListener(this);
        as_rl_set.setOnClickListener(this);
        as_rl_face.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.as_rl_support:
                Intent spintent = new Intent(getActivity(), SupportActivity.class);
                startActivity(spintent);
                break;
            case R.id.as_rl_lanuch:
                Intent laintent = new Intent(getActivity(), MyLanuchActivity.class);
                startActivity(laintent);
                break;
            case R.id.as_rl_collect:
                Intent ccintent = new Intent(getActivity(), CollectActivity.class);
                startActivity(ccintent);
                break;
            case R.id.as_rl_evaluate:
                Intent elintent = new Intent(getActivity(), EvaluateActivity.class);
                startActivity(elintent);
                break;
            case R.id.as_rl_set:
                Intent setintent = new Intent(getActivity(), SettingActivity.class);
                startActivity(setintent);
                break;
            case R.id.as_rl_face:
                Intent faceintent = new Intent(getActivity(), EditPersonInfoActivity.class);
                startActivity(faceintent);
                break;
        }
    }
}
