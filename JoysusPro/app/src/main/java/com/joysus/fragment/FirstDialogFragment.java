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
 * Created by howso on 2016.06.24.
 */

public class FirstDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button btnok;
    private TextView message;
    private String msg = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        switch (styleNum) {
//            case 0:
//                style = DialogFragment.STYLE_NORMAL;//默认样式
//                break;
//            case 1:
//                style = DialogFragment.STYLE_NO_TITLE;//无标题样式
//                break;
//            case 2:
//                style = DialogFragment.STYLE_NO_FRAME;//无边框样式
//                break;
//            case 3:
//                style = DialogFragment.STYLE_NO_INPUT;//不可输入，不可获得焦点样式
//                break;
//        }
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);//设置样式
        msg = getArguments().getString("value");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_normal_layout, container, false);
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

    public static FirstDialogFragment newIns(String value) {
        FirstDialogFragment mdf = new FirstDialogFragment();
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
