package com.upc.fpbackstage.dto;

import com.upc.fpbackstage.po.Comments;

public class commentDto extends Comments {
    private String userName;
    private String headPortrait;

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
