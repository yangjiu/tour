package com.joysus.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.acitivity.CrundFundingActiviy;
import com.joysus.acitivity.PersonDetailActivity;
import com.joysus.entity.TripModel;
import com.joysus.jpview.CircularImage;
import com.joysus.jpview.RatingBarEx;
import com.joysus.jpview.SeekBarEx;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;

/**
 */
public class TripAdapter extends BaseAdapter {
    //LayoutInflater mInflater
    private LayoutInflater mInflater = null;
    //上下文
    private Context mcontext;
    ArrayList<TripModel> cm = new ArrayList<>();

    public TripAdapter(Context context, ArrayList<TripModel> cm) {
        this.mcontext = context;
        this.cm = cm;
        this.mInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return cm.size();
    }

    @Override
    public Object getItem(int position) {
        return cm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //创建ViewHolder
        ViewHoler holer = null;
        //判断convertView是否为空
        if (convertView == null) {
            //new ViewHolder
            holer = new ViewHoler();
            convertView = mInflater.inflate(R.layout.item_lanuch, null);
            holer.im_face = (CircularImage) convertView
                    .findViewById(R.id.imageface);
            holer.tv_nickname = (TextView) convertView
                    .findViewById(R.id.tv_nickname);
            holer.tv_age = (TextView) convertView
                    .findViewById(R.id.tv_age);
            holer.seekBarEx = (SeekBarEx) convertView.findViewById(R.id.seekBar);
            holer.ratingBar = (RatingBarEx) convertView
                    .findViewById(R.id.ratingBar);
            holer.iv_mg = (ImageView) convertView
                    .findViewById(R.id.iv_img);
            holer.tv_title = (TextView) convertView
                    .findViewById(R.id.tv_title);
            holer.tv_activity_time = (TextView) convertView
                    .findViewById(R.id.tv_activity_time);
            holer.tv_price = (TextView) convertView
                    .findViewById(R.id.tv_price);
            holer.tv_priced = (TextView) convertView
                    .findViewById(R.id.tv_priced);
            holer.tv_last_day = (TextView) convertView
                    .findViewById(R.id.tv_last_day);
            holer.imgshare = (ImageView) convertView.findViewById(R.id.imgshare);
            //设置标签
            convertView.setTag(holer);
        } else {
            //获取标签
            holer = (ViewHoler) convertView.getTag();
        }
//        holer.im_face
        holer.tv_nickname.setText(cm.get(position).getUsername());
        holer.tv_age.setText(cm.get(position).getAge());
//        holer.ratingBar = (RatingBar) convertView
//                .findViewById(R.id.ratingBar);
//        holer.iv_mg = (ImageView) convertView
//                .findViewById(R.id.iv_img);
        holer.ratingBar.setRating(3.5f);
        holer.seekBarEx.setIndex(50);
        holer.tv_title.setText(cm.get(position).getLineDesc());
        holer.tv_activity_time.setText(cm.get(position).getStartTime() + "--" + cm.get(position).getEndTime());
        holer.tv_price.setText(cm.get(position).getPrice() + "");
        holer.tv_priced.setText(cm.get(position).getPriced() + "");
        holer.tv_last_day.setText("30");
        holer.iv_mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, CrundFundingActiviy.class);
                mcontext.startActivity(in);
            }
        });
        holer.im_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, PersonDetailActivity.class);
                mcontext.startActivity(in);
            }
        });
        holer.imgshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                        {
                                SHARE_MEDIA.WEIXIN,
                                SHARE_MEDIA.QQ
                        };
                new ShareAction((Activity) mcontext).setDisplayList(displaylist)
                        .withText("呵呵")
                        .withTitle("title")
                        .withTargetUrl("http://www.baidu.com")
//                        .withMedia( image )
//                        .setListenerList(umShareListener)
                        .open();
            }
        });
        //返回视图
        return convertView;
    }

    static class ViewHoler {
        public CircularImage im_face;
        public TextView tv_nickname;
        public TextView tv_age;
        public SeekBarEx seekBarEx;
        public RatingBarEx ratingBar;
        public ImageView iv_mg;
        public ImageView imgshare;
        public TextView tv_title;
        public TextView tv_activity_time;
        public TextView tv_price;
        public TextView tv_priced;
        public TextView tv_last_day;
    }
}
