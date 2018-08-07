package com.skh.message.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User: skh.
 * Date: 2018/8/3 Time: 14:50.
 * Description: 短信实体
 */
public class Sms implements Serializable {
    private static final long serialVersionUID = 1L;

    // 接收者电话
    private String phone;
    // 接收内容
    private String text;
    // 接受者姓名（若有需要）
    private String name;
    // 模板内容
    private String param;
    // 定时时间
    private Date date;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
