package com.joysus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.entity.TripOrder;

import java.util.ArrayList;

/**
 */

public class TripOrderAdapter extends BaseAdapter {
    //LayoutInflater mInflater
    private LayoutInflater mInflater = null;
    //上下文
    private Context mcontext;
    ArrayList<TripOrder> cm = new ArrayList<>();

    public TripOrderAdapter(Context context, ArrayList<TripOrder> cm) {
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
            convertView = mInflater.inflate(R.layout.item_queue, null);
            holer.im_face = (ImageView) convertView
                    .findViewById(R.id.imageface);
            holer.tv_info = (TextView) convertView
                    .findViewById(R.id.tv_info);
            holer.ratingBar = (RatingBar) convertView
                    .findViewById(R.id.ratingBar);
            holer.btnorder = (Button) convertView
                    .findViewById(R.id.btnorder);
            holer.tv_money = (TextView) convertView
                    .findViewById(R.id.tv_money);
            holer.tv_persignal = (TextView) convertView
                    .findViewById(R.id.tv_persignal);
            //设置标签
            convertView.setTag(holer);
        } else {
            //获取标签
            holer = (ViewHoler) convertView.getTag();
        }
//        holer.im_face
        holer.tv_info.setText(cm.get(position).getUsername());
//        holer.ratingBar = (RatingBar) convertView
//                .findViewById(R.id.ratingBar);
        if (position == 0) {
            holer.btnorder.setBackgroundResource(R.mipmap.jinzuan);
        } else if (position == 1) {
            holer.btnorder.setBackgroundResource(R.mipmap.yinzuan);
        } else if (position == 2) {
            holer.btnorder.setBackgroundResource(R.mipmap.tongzuan);
        } else {
            holer.btnorder.setTextColor(Color.parseColor("#666666"));
        }
        holer.btnorder.setText((position + 1) + "");
        String sText = "总筹资额：<font color='red'>" + cm.get(position).getPrices() + "万</font>";
        holer.tv_money.setText(Html.fromHtml(sText));
        holer.tv_money.setMovementMethod(LinkMovementMethod.getInstance());
        holer.tv_persignal.setText(cm.get(position).getPdesc() + "");
        //返回视图
        return convertView;
    }

    static class ViewHoler {
        public ImageView im_face;
        public TextView tv_info;
        public RatingBar ratingBar;
        public Button btnorder;
        public TextView tv_money;
        public TextView tv_persignal;
    }
}
