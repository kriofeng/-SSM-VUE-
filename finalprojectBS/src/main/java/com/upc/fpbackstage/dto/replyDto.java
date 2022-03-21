package com.upc.fpbackstage.dto;

import com.upc.fpbackstage.po.Reply;

public class replyDto extends Reply {
    private String userName;
    private String headPortrait;
    private String replyName;

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyToName) {
        this.replyName = replyToName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
