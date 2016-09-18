package com.xyk.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xyk.async.AsyncWorker;

// @Component
public class SpringScheduleTask {
	private final AtomicInteger counter = new AtomicInteger(0);

	private static int id = 0;

	@Autowired
	private AsyncWorker worker;

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
