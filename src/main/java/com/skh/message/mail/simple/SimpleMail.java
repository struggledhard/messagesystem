package com.skh.message.mail.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


/**
 * Created by User: skh.
 * Date: 2018/7/23 Time: 10:13.
 * Description: 发送简单邮件接口实现
 */
@Component
public class SimpleMail implements SendSimpleMail {
    private final Logger logger = LoggerFactory.getLogger(SimpleMail.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private String from = "1224341153@qq.com";

    @Override
    public void sendSimpleMail(String[] sendTo, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(sendTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try {
            javaMailSender.send(mailMessage);
            logger.info("邮件已发送");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("邮件发生异常", e);
        }
    }
}
