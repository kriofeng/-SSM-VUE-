package com.upc.fpbackstage.dto;

import com.upc.fpbackstage.po.Userbaseinfo;

public class UserbaseinfoDto extends Userbaseinfo {
    private String code;
    private String followedId;//被关注的
    private String followerId;//粉丝
    private Integer postNum;//帖子数
    private Integer blogNum;//博客数

    public String getCode() {
        return code;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    public void setCode(String code) {
        this.code = code;
    }



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
}
