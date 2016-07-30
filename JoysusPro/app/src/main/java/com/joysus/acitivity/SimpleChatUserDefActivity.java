package com.joysus.acitivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.joysus.R;
import com.joysus.chat.Constants;
import com.joysus.chat.SimpleCommonUtils;
import com.joysus.chat.common.adapter.ChattingListAdapter;
import com.joysus.chat.common.data.ImMsgBean;
import com.joysus.chat.common.userdef.SimpleUserdefEmoticonsKeyBoard;
import com.joysus.chat.userdef.SimpleUserDefAppsGridView;
import com.joysus.jpview.refresh.RefreshableView;
import com.sj.emoji.EmojiBean;

import java.util.ArrayList;
import java.util.List;

import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.interfaces.EmoticonClickListener;
import sj.keyboard.widget.EmoticonsEditText;
import sj.keyboard.widget.FuncLayout;

/**
 * Created by qiuheng on 2016.06.13.
 */

public class SimpleChatUserDefActivity extends BaseActivity implements FuncLayout.OnFuncKeyBoardListener {
    public static boolean isForeground = false;
    public static SimpleChatUserDefActivity self;
    private ListView lvChat;
    private SimpleUserdefEmoticonsKeyBoard ekBar;
    private RefreshableView refreshableView;
    private ChattingListAdapter chattingListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_chat_userdef);
        initView();

    }

    private void initView() {
        self = this;
        isForeground = true;
        lvChat = (ListView) findViewById(R.id.lv_chat);
        ekBar = (SimpleUserdefEmoticonsKeyBoard) findViewById(R.id.ek_bar);
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        initEmoticonsKeyBoardBar();
        initListView();
        showBackButton(true);

        /* 获取上个页面传来的值
                */
        Bundle bundle = getIntent().getExtras();
        // 如果bundle 不为null
        if (null != bundle) {
            setTitle(bundle.getString("nickname", "--"));
        }
    }

    private void initEmoticonsKeyBoardBar() {
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());
        ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(this, emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);
        ekBar.addFuncView(new SimpleUserDefAppsGridView(this));

        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSendBtnClick(ekBar.getEtChat().getText().toString());
                ekBar.getEtChat().setText("");
            }
        });
        ekBar.getEmoticonsToolBarView().addFixedToolItemView(false, R.mipmap.icon_add, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleChatUserDefActivity.this, "ADD", Toast.LENGTH_SHORT).show();
            }
        });
        ekBar.getEmoticonsToolBarView().addToolItemView(R.mipmap.icon_setting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleChatUserDefActivity.this, "SETTING", Toast.LENGTH_SHORT).show();
            }
        });
    }

    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

            if (isDelBtn) {
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                    if (o instanceof EmoticonEntity) {
                        OnSendImage(((EmoticonEntity) o).getIconUri());
                    }
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };

    private void initListView() {
        chattingListAdapter = new ChattingListAdapter(this);
        List<ImMsgBean> beanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ImMsgBean bean = new ImMsgBean();
            bean.setSenderType(ImMsgBean.CHAT_SENDER_ME);
            bean.setContent("Test:" + i);
            beanList.add(bean);
        }
        chattingListAdapter.addData(beanList);
        lvChat.setAdapter(chattingListAdapter);
        lvChat.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        break;
                    case SCROLL_STATE_IDLE:
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        ekBar.reset();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
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
        }, 0);
    }

    private void OnSendBtnClick(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ImMsgBean bean = new ImMsgBean();
            bean.setContent(msg);
            bean.setSenderType(ImMsgBean.CHAT_SENDER_OTHER);
            chattingListAdapter.addData(bean, true, false);
            scrollToBottom();
        }
    }

    public void OnReciveClick(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ImMsgBean bean = new ImMsgBean();
            bean.setContent(msg);
            bean.setSenderType(ImMsgBean.CHAT_SENDER_ME);
            chattingListAdapter.addData(bean, true, false);
            scrollToBottom();
        }
    }

    private void OnSendImage(String image) {
        if (!TextUtils.isEmpty(image)) {
            OnSendBtnClick("[img]" + image);
        }
    }

    private void scrollToBottom() {
        lvChat.requestLayout();
        lvChat.post(new Runnable() {
            @Override
            public void run() {
                lvChat.setSelection(lvChat.getBottom());
            }
        });
    }

    @Override
    public void OnFuncPop(int height) {
        scrollToBottom();
    }

    @Override
    public void OnFuncClose() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        ekBar.reset();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }
}
