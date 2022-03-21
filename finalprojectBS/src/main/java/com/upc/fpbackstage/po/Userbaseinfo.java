package com.upc.fpbackstage.po;


public class Userbaseinfo {

  private String userId;
  private String userPwd;
  private String userEmail;
  private Integer userSex;
  private Integer userStatus;
  private String userName;
  private String headPortrait;
  private Userfollow userfollow;


  public Userfollow getUserfollow() {
    return userfollow;
  }

  public void setUserfollow(Userfollow userfollow) {
    this.userfollow = userfollow;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }


  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
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
