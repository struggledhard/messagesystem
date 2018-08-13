package com.skh.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User: skh.
 * Date: 2018/8/3 Time: 14:48.
 * Description: 邮件实体
 */
public class Mail implements Serializable {
    private static final long serialVersionUID = 1L;

    // 邮件主题
    private String subject;
    // 邮件内容
    private String content;
    // 接收者
    private String[] sendTo;
    // 定时时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
