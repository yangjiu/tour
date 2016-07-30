package com.joysus.chat.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joysus.R;
import com.joysus.acitivity.SimpleChatUserDefActivity;
import com.joysus.chat.common.data.ChatGroupBean;
import com.joysus.util.BaseTools;

import java.util.ArrayList;

/**
 * Created by howso on 2016.06.14.
 */

public class ChatGroupListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<ChatGroupBean> mDdata = new ArrayList<>();

    public ChatGroupListAdapter(Context context, ArrayList<ChatGroupBean> data) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        if (data != null) {
            this.mDdata = data;
        }
    }

    @Override
    public int getCount() {
        return mDdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mDdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_message, null);
            viewHolder.item_message_iv_vatar = (ImageView) convertView.findViewById(R.id.item_message_iv_vatar);
            viewHolder.item_message_tv_nickname = (TextView) convertView.findViewById(R.id.item_message_tv_nickname);
            viewHolder.item_message_tv_content = (TextView) convertView.findViewById(R.id.item_message_tv_content);
            viewHolder.item_message_tv_time = (TextView) convertView.findViewById(R.id.item_message_tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ChatGroupBean chatGroupBean = mDdata.get(position);
        if (chatGroupBean != null) {
//            viewHolder.item_message_iv_vatar=
            viewHolder.item_message_tv_nickname.setText(chatGroupBean.getOthernickname());
            viewHolder.item_message_tv_content.setText(chatGroupBean.getRecentmsg());
            viewHolder.item_message_tv_time.setText(BaseTools.getInstance().formatShowTimeToHMS(chatGroupBean.getRecenttime()));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SimpleChatUserDefActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nickname", chatGroupBean.getOthernickname());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView item_message_iv_vatar;
        public TextView item_message_tv_nickname;
        public TextView item_message_tv_content;
        public TextView item_message_tv_time;
    }
}
