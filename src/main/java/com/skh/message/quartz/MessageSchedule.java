package com.skh.message.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by User: skh.
 * Date: 2018/8/2 Time: 14:05.
 * Description: 任务调度
 */
@Component
public class MessageSchedule {
    private final Logger logger = LoggerFactory.getLogger(MessageSchedule.class);

    public void startJob(Map<String, Object> map, Date date, Scheduler scheduler, String type) {
        try {
            JobDataMap dataMap = new JobDataMap(map);

            JobDetail jobDetail = JobBuilder.newJob(MessageJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData(dataMap)
                    .usingJobData("type", type)
                    .build();

            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();

            SimpleTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "triggerGroup1")
                    .startAt(date)
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("任务已启动");
        } catch (SchedulerException e) {
            logger.error("发送异常", e.getMessage());
        }
    }
}
