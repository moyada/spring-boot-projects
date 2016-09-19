package com.xyk.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description Quartz具体任务
 */
@Component
public class QuartzJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(context.getJobDetail().getKey().getName() + " Start:" + Thread.currentThread().getId());
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(context.getJobDetail().getKey().getName() + " End:" + Thread.currentThread().getId());
	}

}
