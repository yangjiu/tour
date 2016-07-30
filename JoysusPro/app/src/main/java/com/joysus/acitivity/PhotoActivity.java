package com.joysus.acitivity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.adapter.picpiker.MainGVAdapter;
import com.joysus.util.ScreenUtils;
import com.joysus.util.Utility;

/**
 * 主页面，可跳转至相册选择照片，并在此页面显示所选择的照片。
 */
public class PhotoActivity extends Activity {
    private MainGVAdapter adapter;
    private ArrayList<String> imagePathList;
    private boolean hasMeasured = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_main);
        //获取屏幕像素
        ScreenUtils.initScreen(this);

        TextView titleTV = (TextView) findViewById(R.id.topbar_title_tv);
        Button selectImgBtn = (Button) findViewById(R.id.main_select_image);
        GridView mainGV = (GridView) findViewById(R.id.main_gridView);

        titleTV.setText(R.string.app_name);
        imagePathList = new ArrayList<String>();
        adapter = new MainGVAdapter(this, imagePathList);
        mainGV.setAdapter(adapter);

        selectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至最终的选择图片页面
                Intent intent = new Intent(PhotoActivity.this, PhotoWallActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int code = intent.getIntExtra("code", -1);
        if (code != 100) {
            return;
        }

        ArrayList<String> paths = intent.getStringArrayListExtra("paths");
        //添加，去重
        boolean hasUpdate = false;
        for (String path : paths) {
            if (!imagePathList.contains(path)) {
                //最多9张
                if (imagePathList.size() == 6) {
                    Utility.showToast(this, "最多可添加6张图片。");
                    break;
                }

                imagePathList.add(path);
                hasUpdate = true;
            }
        }

        if (hasUpdate) {
            adapter.notifyDataSetChanged();
        }
    }
}
