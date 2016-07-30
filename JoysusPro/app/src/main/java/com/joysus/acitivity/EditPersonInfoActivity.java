package com.joysus.acitivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.app.joyapp;
import com.joysus.db.CityManager;
import com.joysus.entity.CityModel;
import com.joysus.jpview.ActionSheet;
import com.joysus.jpview.ActionSheetForCity;
import com.joysus.jpview.ActionSheetPic;
import com.joysus.jpview.ActionSheetPrn;
import com.joysus.util.PhotoUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6.
 */
public class EditPersonInfoActivity extends BaseActivity implements View.OnClickListener, ActionSheet.OnActionSheetSelected, DialogInterface.OnCancelListener, ActionSheetPic.OnActionSheetSelected, ActionSheetPrn.OnActionSheetSelected {
    private RelativeLayout as_rl_face, as_rl_nickname, as_rl_summary, as_rl_gender,as_rl_pic, as_rl_city, as_rl_person, as_rl_card;
    private TextView txt_gender, txt_city;
    public   CityManager cymanager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personinfo);
        setTitle("编辑资料");
        showBackButton(true);
        initView();
        cymanager=new CityManager(this);
    }

    private void initView() {
        as_rl_face = (RelativeLayout) findViewById(R.id.as_rl_face);
        as_rl_nickname = (RelativeLayout) findViewById(R.id.as_rl_nickname);
        as_rl_summary = (RelativeLayout) findViewById(R.id.as_rl_summary);
        as_rl_gender = (RelativeLayout) findViewById(R.id.as_rl_gender);
        as_rl_city = (RelativeLayout) findViewById(R.id.as_rl_city);
        as_rl_person = (RelativeLayout) findViewById(R.id.as_rl_person);
        as_rl_card = (RelativeLayout) findViewById(R.id.as_rl_card);
        as_rl_pic=(RelativeLayout)findViewById(R.id.as_rl_pic);
        txt_gender = (TextView) findViewById(R.id.txt_gender);
        txt_city = (TextView) findViewById(R.id.txt_city);
        as_rl_pic.setOnClickListener(this);
        as_rl_face.setOnClickListener(this);
        as_rl_nickname.setOnClickListener(this);
        as_rl_summary.setOnClickListener(this);
        as_rl_gender.setOnClickListener(this);
        as_rl_city.setOnClickListener(this);
        as_rl_person.setOnClickListener(this);
        as_rl_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.as_rl_face:
                ActionSheetPic.showSheet(context, this, this);
                break;
            case R.id.as_rl_nickname:
                Intent setintent = new Intent(this, NickNameActivity.class);
                startActivity(setintent);
                break;
            case R.id.as_rl_summary:
                Intent sumintent = new Intent(this, EditSummaryActivity.class);
                startActivity(sumintent);
                break;
            case R.id.as_rl_gender:
                ActionSheet.showSheet(this, this, this);
                break;
            case R.id.as_rl_city:
//                ActionSheetForCity.showSheet(this,this,this);
                ActionSheetPrn ac = new ActionSheetPrn();
                ArrayList<CityModel> cms=new ArrayList<>();
                cms=cymanager.getCity("0");
                String[] bb=new String[cms.size()];
                for(int i=0;i<cms.size();i++)
                {
                    bb[i]=cms.get(i).getName();
                }
                String[] aa = {"南京市", "苏州市", "无锡市", "常州市", "镇江市"};
                ac.showSheet(context, bb, aa, this, this);
                break;
            case R.id.as_rl_person:
                Intent perintent = new Intent(this, PersonCardActivity.class);
                startActivity(perintent);
                break;
            case R.id.as_rl_card:
                Intent cerintent = new Intent(this, CertificatesActivity.class);
                startActivity(cerintent);
                break;
            case R.id.as_rl_pic:
                Intent intent = new Intent(EditPersonInfoActivity.this, PhotoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case 0:
                txt_gender.setText("男");
                break;
            case 1:
                txt_gender.setText("女");
                break;
            case 2:
                break;
            case 6:
                PhotoUtils.getInstance().takePhoto(context,
                        "sdcard/1.jpg");
                break;
            case 7:
                Intent intent = new Intent(EditPersonInfoActivity.this, PhotoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSelected(String value) {
        txt_city.setText(value);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }
}
