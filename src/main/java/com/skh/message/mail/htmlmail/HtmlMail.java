package com.skh.message.mail.htmlmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by User: skh.
 * Date: 2018/7/24 Time: 10:06.
 * Description: 发送html格式邮件
 */
@Component
public class HtmlMail implements SendHtmlMail {
    private final Logger logger = LoggerFactory.getLogger(HtmlMail.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private String from = "1224341153@qq.com";

    @Override
    public void sendHtmlMail(String sendTo, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发生异常", e);
        }
    }
}
