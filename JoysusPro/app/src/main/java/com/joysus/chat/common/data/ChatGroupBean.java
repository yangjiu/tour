package com.joysus.chat.common.data;

/**
 * Created by howso on 2016.06.14.
 */

public class ChatGroupBean {
    //其他人nickname
    private String othernickname;
    //最近一条聊天记录
    private String recentmsg;
    //头像
    private String iconface;
    //最近一条聊天时间
    private long recenttime;

    public String getOthernickname() {
        return othernickname;
    }

    public void setOthernickname(String othernickname) {
        this.othernickname = othernickname;
    }

    public String getRecentmsg() {
        return recentmsg;
    }

    public void setRecentmsg(String recentmsg) {
        this.recentmsg = recentmsg;
    }

    public String getIconface() {
        return iconface;
    }

    public void setIconface(String iconface) {
        this.iconface = iconface;
    }

    public long getRecenttime() {
        return recenttime;
    }

    public void setRecenttime(long recenttime) {
        this.recenttime = recenttime;
    }
}
