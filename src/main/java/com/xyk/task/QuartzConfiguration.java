package com.xyk.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration 
public class QuartzConfiguration {
	@Autowired
	@Qualifier("Cron1")
	private CronTriggerFactoryBean cron1;

	@Autowired
	@Qualifier("Cron2")
	private CronTriggerFactoryBean cron2;

	@Bean
	public JobFactory autoWiringSpringBeanJobFactory() {
		return new JobFactory();
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setJobFactory(autoWiringSpringBeanJobFactory());
		scheduler.setTriggers(cron1.getObject(), cron2.getObject());
		return scheduler;
	}
}
