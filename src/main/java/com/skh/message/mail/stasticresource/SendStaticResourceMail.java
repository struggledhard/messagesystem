package com.skh.message.mail.stasticresource;

/**
 * Created by User: admin.
 * Date: 2018/7/24 Time: 11:05.
 * Description: 发送带静态资源的邮件
 */
public interface SendStaticResourceMail {
    void sendStaticResourceMail(String sendTo, String subject, String content, String rscPath, String rcsId);
}
