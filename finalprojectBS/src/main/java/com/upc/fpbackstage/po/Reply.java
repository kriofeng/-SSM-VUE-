package com.upc.fpbackstage.po;


public class Reply {

  private String replyId;
  private String parentId;
  private String content;
  private String replyTo;
  private String date;
  private String userId;
  private Userbaseinfo userbaseinfo;
  private Userbaseinfo userbaseinfo2;
  public Userbaseinfo getUserbaseinfo2() {
    return userbaseinfo2;
  }

  public void setUserbaseinfo2(Userbaseinfo userbaseinfo2) {
    this.userbaseinfo2 = userbaseinfo2;
  }


  public Userbaseinfo getUserbaseinfo() {
    return userbaseinfo;
  }

  public void setUserbaseinfo(Userbaseinfo userbaseinfo) {
    this.userbaseinfo = userbaseinfo;
  }



  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getReplyId() {
    return replyId;
  }

  public void setReplyId(String replyId) {
    this.replyId = replyId;
  }


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(String replyTo) {
    this.replyTo = replyTo;
  }

}
