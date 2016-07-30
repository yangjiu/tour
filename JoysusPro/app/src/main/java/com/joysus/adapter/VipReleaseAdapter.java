package com.joysus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.entity.RounteLineModel;

import java.util.ArrayList;

/**
 * 会员发布适配器
 */
public class VipReleaseAdapter extends BaseAdapter {
    //LayoutInflater mInflater
    private LayoutInflater mInflater = null;
    //上下文
    private Context mcontext;
    ArrayList<RounteLineModel> cm = new ArrayList<>();

    public VipReleaseAdapter(Context context, ArrayList<RounteLineModel> cm) {
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
            convertView = mInflater.inflate(R.layout.item_vip_release, null);
            holer.iv_bg = (ImageView) convertView
                    .findViewById(R.id.iv_bg);
            holer.txtScenery = (TextView) convertView
                    .findViewById(R.id.txtScenery);
            holer.txtaddress = (TextView) convertView
                    .findViewById(R.id.txtaddress);
            holer.txttime = (TextView) convertView
                    .findViewById(R.id.txttime);
            //设置标签
            convertView.setTag(holer);
        } else {
            //获取标签
            holer = (ViewHoler) convertView.getTag();
        }

//       holer.iv_bg.setText(cm.get(position).getUsername());
        holer.txtScenery.setText(cm.get(position).getProject_desc());

        holer.txtaddress.setText(cm.get(position).getDestination());
        holer.txttime.setText(cm.get(position).getStart_time() + "--" + cm.get(position).getEnd_time());
        //返回视图
        return convertView;
    }

    static class ViewHoler {
        public ImageView iv_bg;
        public TextView txtScenery;
        public TextView txtaddress;
        public TextView txttime;
    }
}
