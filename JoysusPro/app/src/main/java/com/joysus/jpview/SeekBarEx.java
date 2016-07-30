package com.joysus.jpview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.util.ScreenUtils;

public class SeekBarEx extends LinearLayout {

    private View v;
    private TextView bar;
    private View leftbar;
    private LinearLayout outframe;
    private boolean hasMeasured = false;
    private boolean hasMeasured1 = false;
    private int parwidth = 0, parheight = 0, twidth = 0, wheight = 0;

    public SeekBarEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 在构造函数中将Xml中定义的布局解析出来。
        v = LayoutInflater.from(context).inflate(R.layout.seekbar_layout, this,
                true);
        ScreenUtils.initScreen((Activity) context);
        init();
    }

    public SeekBarEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarEx(Context context) {
        this(context, null);
    }

    private void init() {
        bar = (TextView) v.findViewById(R.id.bar);
        leftbar = v.findViewById(R.id.leftbar);
        outframe = (LinearLayout) v.findViewById(R.id.outframe);

    }

    public void setIndex(final int index) {
        ViewTreeObserver vto = outframe.getViewTreeObserver();

        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    parheight = outframe.getHeight();
                    parwidth = outframe.getWidth();
                    // 获取到宽度和高度后，可用于计算
                    hasMeasured = true;

                }
                return true;
            }
        });
        ViewTreeObserver vto1 = bar.getViewTreeObserver();

        vto1.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured1 == false) {
                    twidth = bar.getWidth();
                    wheight = bar.getHeight();
                    // 获取到宽度和高度后，可用于计算
                    hasMeasured1 = true;

                } else {
                    int leftwidth = 0;
                    if (index <= 100) {
                        leftwidth = (int) ((parwidth - twidth) * (index * 0.01));
                        bar.setText(index + "%");
                    } else {
                        leftwidth = (int) ((parwidth - twidth) * (100 * 0.01));
                        bar.setText("100%");
                    }
                    int dph = ScreenUtils.dp2px(3f);
                    LayoutParams params = new LayoutParams(leftwidth, dph);
                    params.gravity = Gravity.CENTER;
                    leftbar.setLayoutParams(params);
                }
                return true;
            }
        });
    }

}
