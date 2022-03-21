package com.upc.fpbackstage.po;


public class Comments {

  private String commentId;
  private String articleId;
  private String content;
  private String userId;
  private String date;
  private Userbaseinfo userbaseinfo;
  public Userbaseinfo getUserbaseinfo() {
    return userbaseinfo;
  }

  public void setUserbaseinfo(Userbaseinfo userbaseinfo) {
    this.userbaseinfo = userbaseinfo;
  }



  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCommentId() {
    return commentId;
  }

  public void setCommentId(String commentId) {
    this.commentId = commentId;
  }


  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
