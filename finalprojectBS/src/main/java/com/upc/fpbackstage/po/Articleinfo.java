package com.upc.fpbackstage.po;


public class Articleinfo {

  private String articleId;
  private String userId;
  private Integer type;
  private String title;
  private String content;
  private Integer views;
  private String date;
  private Integer commentCount;
  private Integer markedCount;
  private Integer likedCount;
  private String lableId;
  private Integer articleStatus;
  private String area;

  private Userbaseinfo userbaseinfo;
  private Marked marked;
  private Userfollow userfollow;

  public Userfollow getUserfollow() {
    return userfollow;
  }

  public void setUserfollow(Userfollow userfollow) {
    this.userfollow = userfollow;
  }

  public Marked getMarked() {
    return marked;
  }

  public void setMarked(Marked marked) {
    this.marked = marked;
  }

  public Label getLabel() {
    return label;
  }

  public void setLabel(Label label) {
    this.label = label;
  }

  private Label label;

  public Userbaseinfo getUserbaseinfo() {
    return userbaseinfo;
  }

  public void setUserbaseinfo(Userbaseinfo userbaseinfo) {
    this.userbaseinfo = userbaseinfo;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }


  public Integer getMarkedCount() {
    return markedCount;
  }

  public void setMarkedCount(Integer markedCount) {
    this.markedCount = markedCount;
  }


  public Integer getLikedCount() {
    return likedCount;
  }

  public void setLikedCount(Integer likedCount) {
    this.likedCount = likedCount;
  }


  public String getLableId() {
    return lableId;
  }

  public void setLableId(String lableId) {
    this.lableId = lableId;
  }


  public Integer getArticleStatus() {
    return articleStatus;
  }

  public void setArticleStatus(Integer articleStatus) {
    this.articleStatus = articleStatus;
  }


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

}
