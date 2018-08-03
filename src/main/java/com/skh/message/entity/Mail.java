package com.skh.message.entity;

/**
 * Created by User: skh.
 * Date: 2018/8/3 Time: 14:48.
 * Description: 邮件实体
 */
public class Mail {
    // 邮件主题
    private String subject;
    // 邮件内容
    private String content;
    // 接收者
    private String[] sendTo;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getSendTo() {
        return sendTo;
    }

    public void setSendTo(String[] sendTo) {
        this.sendTo = sendTo;
    }
}
