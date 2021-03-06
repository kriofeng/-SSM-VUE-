package com.upc.fpbackstage.service;

public interface EmailService {
    /**
     * 发送简单邮件
     * @param sendTo 收件人地址
     * @param title  邮件标题
     * @param content 邮件内容
     */
    void sendSimpleMail(String sendTo, String title, String content);

    /**
     * 发送HTML邮件
     * @param sendTo 收件人地址
     * @param title 邮件标题
     * @param content 邮件内容
     */
    void sendHtmlMail(String sendTo, String title, String content);

}
