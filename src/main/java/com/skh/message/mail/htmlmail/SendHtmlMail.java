package com.skh.message.mail.htmlmail;

/**
 * Created by User: admin.
 * Date: 2018/7/24 Time: 10:05.
 * Description: 发送html格式邮件
 */
public interface SendHtmlMail {
    void sendHtmlMail(String sendTo, String subject, String content);
}
