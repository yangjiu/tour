package com.stone.verticalslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by howso on 2016.06.14.
 */

public class CustListView extends ListView {
    //如果是Ture ,则允许拖动至底部的下一页
    boolean allowDragTop = true;
    float downY = 0;
    //是否需要承包touch事件，needConsumeTouch一旦被定性，则不会更改
    boolean needConsumeTouch = true;

    public CustListView(Context context) {
        this(context, null);
    }

    public CustListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getY();
            //默认情况下，ListView内部滚动优先，默认情况下由改listView消费touch事件
            needConsumeTouch = true;
            allowDragTop = isAtTop();
        }
        else if (ev.getAction()==MotionEvent.ACTION_MOVE){
            if(!needConsumeTouch)
            {
                //在最顶端且向上拉了，则这个touch事件交给父类处理
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
            else if (allowDragTop){
                //needConsumeTouch尚未被定性，此处给其定性
                //允许拖动到底部的下一页，而且又向上拖动了，就将touch事件交给父View
                if(ev.getRawY()-downY>2)
                {
                    // flag设置，由父类去消费
                    needConsumeTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }
        // 通知父view是否要处理touch事件
        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断ListView是否在顶部
     */
    private boolean isAtTop() {
        boolean resultValue = false;
        int childNum = getChildCount();
        if (childNum == 0) {
            //没有child,肯定在顶部
            resultValue = true;
        } else {
            if (getFirstVisiblePosition() == 0) {
                //根据第一个childView来判定是否在顶部
                View firstView = getChildAt(0);
                if (Math.abs(firstView.getTop() - getTop()) < 2) {
                    resultValue = true;
                }
            }
        }
        return resultValue;
    }
}
