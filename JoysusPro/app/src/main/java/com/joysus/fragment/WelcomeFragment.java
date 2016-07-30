package com.joysus.fragment;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.joysus.R;

/**
 */

public class WelcomeFragment extends DialogFragment implements View.OnClickListener {
    private Button btnok;
    private TextView message;
    private String msg = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);//设置样式
        msg = getArguments().getString("value");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_welcom_layout, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnok = (Button) v.findViewById(R.id.btnok);
        message = (TextView) v.findViewById(R.id.message);
        btnok.setOnClickListener(this);
        message.setText(msg);
        return v;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;

        window.setAttributes(windowParams);
    }

    public static WelcomeFragment newIns(String value) {
        WelcomeFragment mdf = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString("value", value);
        mdf.setArguments(args);
        return mdf;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnok:
                this.dismiss();
                break;
        }
    }
}
