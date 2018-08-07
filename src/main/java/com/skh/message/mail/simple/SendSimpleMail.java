package com.skh.message.mail.simple;

/**
 * Created by User: admin.
 * Date: 2018/7/23 Time: 10:11.
 * Description: 简单邮件接口
 */
public interface SendSimpleMail {
    void sendSimpleMail(String[] sendTo, String subject, String content);
}
