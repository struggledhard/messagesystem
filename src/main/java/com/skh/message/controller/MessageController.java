package com.skh.message.controller;

import com.skh.message.quartz.MessageSchedule;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Queue;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User: skh.
 * Date: 2018/8/1 Time: 13:59.
 * Description: 消息控制器
 */
@Controller
public class MessageController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("mailQueue")
    private Queue mailQueue;

    @Autowired
    @Qualifier("smsQueue")
    private Queue smsQueue;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    MessageSchedule messageSchedule;

    @GetMapping(value = "/mail")
    @ResponseBody
    public void sendMail() {
        Map<String, String> map = new HashMap<>();
        map.put("send_to", "1224341153@qq.com");
        map.put("subject", "简单邮件");
        map.put("content", "你好");

        jmsTemplate.convertAndSend(mailQueue, map);
    }

    @GetMapping(value = "/sms")
    @ResponseBody
    public void sendSms() {
        Map<String, String> map = new HashMap<>();
        map.put("phone", "12345637896");
        map.put("sign", "模板");
        map.put("code", "SSS123");
        map.put("param", "{\"aa:aa\"}");

        jmsTemplate.convertAndSend(smsQueue, map);
    }

    // 定时发送
    @GetMapping(value = "/mails")
    @ResponseBody
    public void sendMails() {
        Map<String, String> map = new HashMap<>();
        map.put("send_to", "1224341153@qq.com");
        map.put("subject", "简单邮件");
        map.put("content", "你好");
        Date date = new Date();
        date.setTime(date.getTime() + 5000);
        messageSchedule.startJob(map, date, scheduler, "mail");
    }

//    // 定时发送
//    // 批量发送邮件
//    @GetMapping(value = "/mails")
//    @ResponseBody
//    public void sendMailss(String[] sendTo, String subject, String content) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("send_to", sendTo);
//        map.put("subject", subject);
//        map.put("content", content);
//        Date date = new Date();
//        date.setTime(date.getTime() + 5000);
//        messageSchedule.startJob(map, date, scheduler, "mail");
//    }
}
