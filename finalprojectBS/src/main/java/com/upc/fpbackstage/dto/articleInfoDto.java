package com.upc.fpbackstage.dto;

import com.upc.fpbackstage.po.Articleinfo;

public class articleInfoDto extends Articleinfo {
    private String userName;
    private String headPortrait;
    private String followedId;//被关注的
    private String followerId;//粉丝

    public String getFollowedId() {
        return followedId;
    }

    public void setFollowedId(String followedId) {
        this.followedId = followedId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
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
