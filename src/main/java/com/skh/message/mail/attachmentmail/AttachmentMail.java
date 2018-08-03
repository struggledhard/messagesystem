package com.skh.message.mail.attachmentmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by User: skh.
 * Date: 2018/7/24 Time: 10:42.
 * Description: 发送带附件的邮件
 */
@Component
public class AttachmentMail implements SendAttachmentMail {
    private final Logger logger = LoggerFactory.getLogger(AttachmentMail.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private String from = "1224341153@qq.com";

    @Override
    public void sendAttachmentMail(String sendTo, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
            logger.info("带附件邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送异常", e);
        }
    }
}
