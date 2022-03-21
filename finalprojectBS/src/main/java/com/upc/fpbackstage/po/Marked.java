package com.upc.fpbackstage.po;


public class Marked {

  private Integer markedId;
  private Integer type;
  private String userId;
  private String articleId;

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public Integer getMarkedId() {
    return markedId;
  }

  public void setMarkedId(Integer markedId) {
    this.markedId = markedId;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
