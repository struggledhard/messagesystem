package com.skh.message.mail.stasticresource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by User: skh.
 * Date: 2018/7/24 Time: 11:07.
 * Description: 发送带静态资源的邮件
 */
@Component
public class StaticResourceMail implements SendStaticResourceMail {
    private final Logger logger = LoggerFactory.getLogger(StaticResourceMail.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private String from = "1224341153@qq.com";

    @Override
    public void sendStaticResourceMail(String sendTo, String subject, String content, String rscPath, String rcsId) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(rscPath);
            helper.addInline(rcsId, file);

            javaMailSender.send(message);
            logger.info("带静态资源的邮件已经发送");
        } catch (MessagingException e) {
            logger.error("发送带静态资源邮件时发生异常！", e);
        }
    }
}
