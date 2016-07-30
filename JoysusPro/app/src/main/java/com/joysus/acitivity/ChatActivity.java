package com.joysus.acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.joysus.R;
import com.joysus.adapter.chat.ChatMsgAdapter;
import com.joysus.entity.ChatMsg;
import com.joysus.jpview.FaceRelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @文件描述 : 聊天界面
 * Created by qiuheng on 2016.06.08.
 */

public class ChatActivity extends Activity implements View.OnClickListener {

    public static boolean isForeground = false;
    public static ChatActivity self;
    private Button mBtnSend;

    private EditText mEditTextContent;

    private ListView mListView;

    private ChatMsgAdapter mAdapter;

    private List<ChatMsg> mDataArrays = new ArrayList<ChatMsg>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        self = this;
        isForeground = true;
        initView();
        initData();
    }

    public void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);
    }

    private String[] msgArray = new String[]{"[媚眼]测试啦[媚眼]", "测试啦", "测试啦",
            "测试啦", "测试啦", "你妹[苦逼]", "测[惊讶]你妹", "测你妹[胜利]",
            "测试啦"};

    private String[] dataArray = new String[]{"2016-5-12 12:00",
            "2016-5-12 12:10", "2016-5-12 12:11", "2016-5-12 12:20",
            "2016-5-12 12:30", "2016-5-12 12:35", "2016-5-12 12:40",
            "2016-5-12 12:50", "2016-5-12 12:50"};

    private final static int COUNT = 8;

    public void initData() {
        for (int i = 0; i < COUNT; i++) {
            ChatMsg entity = new ChatMsg();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName("你妹");
                entity.setMsgType(true);
            } else {
                entity.setName("没么");
                entity.setMsgType(false);
            }

            entity.setText(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new ChatMsgAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String contString = mEditTextContent.getText().toString();
                send(contString,false);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && ((FaceRelativeLayout) findViewById(R.id.FaceRelativeLayout))
                .hideFaceView()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void send(String content,boolean iscom) {

        if (content.length() > 0) {
            ChatMsg entity = new ChatMsg();
            entity.setDate(getDate());
            entity.setMsgType(iscom);
            entity.setText(content);

            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();
            mEditTextContent.setText("");
            mListView.setSelection(mListView.getCount() - 1);
        }
    }

    private String getDate() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH)+1);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String mins = String.valueOf(c.get(Calendar.MINUTE));

        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
                + mins);

        return sbBuffer.toString();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }
}
