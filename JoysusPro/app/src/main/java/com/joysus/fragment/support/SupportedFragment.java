package com.joysus.fragment.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joysus.R;
import com.joysus.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/6/26.
 */

public class SupportedFragment extends BaseFragment{
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.support_ed, container, false);
        return v;
    }
}
