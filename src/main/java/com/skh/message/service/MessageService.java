package com.skh.message.service;

import com.skh.message.mail.simple.SendSimpleMail;
import com.skh.message.sms.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by User: skh.
 * Date: 2018/8/1 Time: 14:23.
 * Description: 监听消息队列
 */
@Service
public class MessageService {
    @Autowired
    private SendSimpleMail simpleMail;

    @Autowired
    private SendSms sendSms;

    @JmsListener(destination = "mail-queue")
    public void sendMail(Map<String, String> map) {
        simpleMail.sendSimpleMail(map.get("send_to"),
                map.get("subject"),
                map.get("content"));
    }

    @JmsListener(destination = "sms-queue")
    public void sendSms(Map<String, String> map) {
        sendSms.sendSms(map.get("phone"),
                map.get("sign"),
                map.get("code"),
                map.get("param"),
                null, null);
    }
}
