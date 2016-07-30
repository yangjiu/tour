package com.joysus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.joysus.R;
import com.joysus.chat.common.adapter.ChatGroupListAdapter;
import com.joysus.chat.common.data.ChatGroupBean;
import com.joysus.jpview.refresh.RefreshableView;
import com.joysus.util.FaceConversionUtil;

import java.util.ArrayList;

public class ChatMsgFramgment extends BaseFragment {
    private ListView listView;
    private RefreshableView refreshableView;
    private ChatGroupListAdapter chatGroupListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fram_msg, container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    private void initView(View v) {
        listView = (ListView) v.findViewById(R.id.lv_chat_list);
        refreshableView = (RefreshableView) v.findViewById(R.id.refreshable_list_view);
    }

    private void initData() {
        ArrayList<ChatGroupBean> datat = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ChatGroupBean cgb = new ChatGroupBean();
            cgb.setOthernickname("昵称" + i);
            cgb.setRecentmsg("最近聊天" + i);
            cgb.setRecenttime(System.currentTimeMillis() + i * 2000);
            datat.add(cgb);
        }
        chatGroupListAdapter = new ChatGroupListAdapter(getActivity(), datat);
        listView.setAdapter(chatGroupListAdapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        refreshableView.finishRefreshing();
                    }
                }).start();
            }
        }, 1);
    }
}
