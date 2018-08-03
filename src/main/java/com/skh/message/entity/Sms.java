package com.skh.message.entity;

/**
 * Created by User: skh.
 * Date: 2018/8/3 Time: 14:50.
 * Description: 短信实体
 */
public class Sms {
    // 接收者电话
    private String phone;
    // 接收内容
    private String text;
    // 接受者姓名（若有需要）
    private String receiveName;

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

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
