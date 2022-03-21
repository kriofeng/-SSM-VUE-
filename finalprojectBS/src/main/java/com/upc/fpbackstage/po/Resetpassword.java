package com.upc.fpbackstage.po;


public class Resetpassword {

  private String userId;
  private String code;
  private String currentTime;
  private String deadline;
  private Integer isEffective;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }


  public String getDeadline() {
    return deadline;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }


  public Integer getIsEffective() {
    return isEffective;
  }

  public void setIsEffective(Integer isEffective) {
    this.isEffective = isEffective;
  }

}
