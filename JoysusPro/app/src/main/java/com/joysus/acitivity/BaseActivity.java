package com.joysus.acitivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.app.joyapp;

/**
 * Created by qiuheng on 2016.06.13.
 */

public class BaseActivity extends FragmentActivity {
    /**
     * 应用程序上下文
     */
    public Context context;
    /**
     * 全局变量
     */
    public joyapp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app=(joyapp)this.getApplication();
        context = this;
    }

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    public static boolean checkPermission(final Activity activity, final String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int storagePermission = ActivityCompat.checkSelfPermission(activity, permission);
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void showPermissionDialog(final Activity activity, String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
    }
    /**
     * 设置标题
     */
    public final void setTitle(String title) {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.ll_layout);
        if (layout == null)
            return;
        TextView vTitle = (TextView) layout.findViewById(R.id.tv_title);
        if (vTitle == null)
            return;
        vTitle.setText(title);
    }

    /**
     * 是否显示返回按钮
     */
    public final void showBackButton(boolean needShow) {
        if (needShow) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.ll_layout);
            if (layout == null)
                return;
            LinearLayout vBack = (LinearLayout) layout
                    .findViewById(R.id.ll_back);
            if (vBack == null)
                return;
            vBack.setVisibility(View.VISIBLE);
            vBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
