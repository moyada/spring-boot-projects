package com.xyk.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xyk.async.AsyncWorker;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description SpringSechdule配置
 */
// @Component
public class SpringScheduleTask {
	private final AtomicInteger counter = new AtomicInteger(0);

	private static int id = 0;

	@Autowired
	private AsyncWorker worker;

	/**
	 * Created by xueyikang on 2016/9/19.
	 * @version 1.0
	 * @since  1.7
	 * @description 具体调度任务，每两秒执行一次
	 */
	@Scheduled(fixedRate = 2000)
	public void tash() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			worker.work("" + counter.getAndIncrement());
			try {
				TimeUnit.SECONDS.sleep(3);
				// System.out.println(id++);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
