package com.skh.message.mail.attachmentmail;

/**
 * Created by User: admin.
 * Date: 2018/7/24 Time: 10:41.
 * Description: 发送带附件的邮件
 */
public interface SendAttachmentMail {
    void sendAttachmentMail(String sendTo, String subject, String content, String filePath);
}
