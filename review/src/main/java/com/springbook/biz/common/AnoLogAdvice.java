package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service //<bean>의 역할을 해주는 어노테이션
@Aspect //<aop:aspect> 의 역할 즉, 어드바이스와 포인트컷을 연결해주는 역할을 해줌
public class AnoLogAdvice {

//	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
//	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@AfterReturning(pointcut="getPointcut()", returning="returnObj")	
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("실행된 메서드 이름 = " +method + ", 해당 메서드 인자값들 = " +args.toString());
	
	}
	
	
}
