package com.skh.message.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.jms.Queue;

/**
 * Created by User: skh.
 * Date: 2018/8/2 Time: 11:25.
 * Description: 执行任务
 */
public class MessageJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(MessageJob.class);


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("mailQueue")
    private Queue queueMail;

    @Autowired
    @Qualifier("smsQueue")
    private Queue smsQueue;

    //private Map<String, Object> mapParam;
    private JobDataMap map;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        map = jobExecutionContext.getJobDetail().getJobDataMap();
        //mapParam = map.getWrappedMap();
        if (map.get("type").equals("mail")) {
            jmsTemplate.convertAndSend(queueMail, map);
            logger.info("邮件已发送");
        }
        if (map.get("type").equals("sms")) {
            jmsTemplate.convertAndSend(smsQueue, map);
            logger.info("短信已发送");
        }
    }
}
