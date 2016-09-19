package com.xyk.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.xyk.annotation.SysLog;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description 异步处理程序
 */
@Component
public class AsyncWorker {
	
	@Async
	@SysLog("worker")
	public void work(String name) {
		String threadName = Thread.currentThread().getName();
		System.out.println("   " + threadName + " beginning work on " + name);
		try {
			Thread.sleep(5000); // simulates work

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println("   " + threadName + " completed work on " + name);
	}
}
