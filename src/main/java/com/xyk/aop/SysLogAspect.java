package com.xyk.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

import com.xyk.annotation.SysLog;

/**
 * Created by xueyikang on 2016/9/19.
 * @version 1.0
 * @since  1.7
 * @description 拦截器配置
 */
@Aspect
@Component
public class SysLogAspect {
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	// 配置切片
	@Pointcut("@annotation(com.xyk.annotation.SysLog)")
	public void methodAspect() {
	}

	// 调用前处理程序
	@Before("methodAspect()")
	public void before(JoinPoint joinPoint) {
		Method method = ((MethodSignature) ((MethodInvocationProceedingJoinPoint) joinPoint).getSignature())
				.getMethod();
		SysLog log = method.getAnnotation(SysLog.class);
		String logValue = log.value();
		System.out.println(logValue + "start");
	}

	// 调用后处理程序
	@After("methodAspect()")
	public void after(JoinPoint joinPoint) {
		Method method = ((MethodSignature) ((MethodInvocationProceedingJoinPoint) joinPoint).getSignature())
				.getMethod();
		SysLog log = method.getAnnotation(SysLog.class);
		String logValue = log.value();
		System.out.println(logValue + "completed");
	}

	// 调用时处理程序
	@Around("methodAspect()")
	public void around(ProceedingJoinPoint point) throws Throwable {
		// 使用代理调用类中方法		
		// ((SysLogAspect)AopContext.currentProxy()).before(point);
		Method method = ((MethodSignature) ((MethodInvocationProceedingJoinPoint) point).getSignature()).getMethod();
		method.invoke(point.getTarget(), point.getArgs());
		SysLog log = method.getAnnotation(SysLog.class);
		String logValue = log.value();
		System.out.println(logValue);
	}

	// 异常处理程序
	@AfterThrowing(pointcut = "methodAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		System.err.println(e.getClass().getPackage() + e.getMessage());
	}
}
