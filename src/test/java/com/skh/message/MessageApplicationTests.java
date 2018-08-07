package com.skh.message;

import com.skh.message.mail.attachmentmail.AttachmentMail;
import com.skh.message.mail.htmlmail.HtmlMail;
import com.skh.message.mail.simple.SimpleMail;
import com.skh.message.mail.stasticresource.StaticResourceMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageApplicationTests {

    @Autowired
    private SimpleMail simpleMail;

    @Autowired
    private HtmlMail htmlMail;

    @Autowired
    private AttachmentMail attachmentMail;

    @Autowired
    private StaticResourceMail staticResourceMail;

    @Autowired
    private TemplateEngine templateEngine;

    private String senTo = "1224341153@qq.com";

    String[] sendTo = {"1224341153@qq.com"};

    @Test
    public void setSimpleMail() throws Exception {
        simpleMail.sendSimpleMail(sendTo, "简单邮件", "你好");
    }

    @Test
    public void setHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        htmlMail.sendHtmlMail(senTo, "html邮件", content);
    }

    @Test
    public void setAttachmentMail() throws Exception {
        String filePath = "d:\\xx\\aa.log";
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>一封带附件的邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        attachmentMail.sendAttachmentMail(senTo, "带附件邮件", content, filePath);
    }

    @Test
    public void setStaticResourceMail() throws Exception {
        String rscId = "xx001";
        String content="<html><body>带有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\favicon.png";

        staticResourceMail.sendStaticResourceMail(senTo, "带有静态资源的邮件", content, imgPath, rscId);
    }

    @Test
    public void setTemplateMail() throws Exception {
        // 创建邮件正文
        Context context = new Context();
        context.setVariable("id", "001");

        String mailContent = templateEngine.process("mailtemplate", context);

        simpleMail.sendSimpleMail(sendTo, "模板邮件", mailContent);
    }
}
