package com.xyk.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description Quartzµ÷¶ÈÆ÷ÅäÖÃ
 */
@Configuration 
public class ScheduleTask {

	@Bean(name="QuartzJob")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(QuartzJob.class);
        factory.setGroup("mygroup");
        factory.setName("myjob");
        return factory;
    }

    //Job is scheduled after every 1 minute 
    @Bean(name="Cron1")
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(1000);
        stFactory.setName("mytrigger");
        stFactory.setGroup("mygroup");
        stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
        return stFactory;
    }
    
    //Job is scheduled after every 5 secends
    @Bean(name="Cron2")
    public CronTriggerFactoryBean cronTriggerFactoryBean2(){
    	CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
    	stFactory.setJobDetail(jobDetailFactoryBean().getObject());
    	stFactory.setStartDelay(1000);
    	stFactory.setName("mytrigger");
    	stFactory.setGroup("mygroup");
    	stFactory.setCronExpression("0/5 * * * * ?");
    	return stFactory;
    }
}
