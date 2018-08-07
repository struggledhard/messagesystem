package com.skh.message.service;

import com.skh.message.entity.Mail;
import com.skh.message.entity.Sms;
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
    public void sendMail(Mail mail) {
//        String[] sendTos = (String[]) map.get("send_to");
//        String subject = (String) map.get("subject");
//        String content = (String) map.get("content");
        simpleMail.sendSimpleMail(mail.getSendTo(), mail.getSubject(), mail.getContent());
    }

    @JmsListener(destination = "sms-queue")
    public void sendSms(Sms sms) {
        sendSms.sendSms(sms.getPhone(), "孙开华", "SMS_141615964", sms.getParam(),
                null, null);
    }
}
