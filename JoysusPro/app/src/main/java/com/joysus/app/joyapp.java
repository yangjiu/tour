package com.joysus.app;

import android.app.Activity;
import android.app.Application;

import com.joysus.db.ChatManager;
import com.joysus.db.CityManager;
import com.joysus.util.FaceConversionUtil;
import com.joysus.util.ScreenUtils;
import com.umeng.socialize.PlatformConfig;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by qiuheng on 2016.06.08.
 */

public class joyapp extends Application {
    private static final String TAG = "joysus";
    public static joyapp ins;
    public ArrayList<Activity> arries = new ArrayList<>();
    // 包的名字
    public String packageName;
    private ChatManager chatManager;

    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    public static joyapp getIns() {
        if (ins == null) {
            ins = new joyapp();
        }
        return ins;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        packageName = getPackageName();
        chatManager = new ChatManager(this);
        chatManager.initCellDB();
        //设置开启日志，release 版本需要关闭 日志
        JPushInterface.setDebugMode(true);
        //初始化JPUSH
        JPushInterface.init(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                FaceConversionUtil.getInstace().getFileText(getApplicationContext());
            }
        }).start();
    }
}
