package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service //<bean>�� ������ ���ִ� ������̼�
@Aspect //<aop:aspect> �� ���� ��, �����̽��� ����Ʈ���� �������ִ� ������ ����
public class AnoLogAdvice {

//	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
//	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@AfterReturning(pointcut="getPointcut()", returning="returnObj")	
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		System.out.println("����� �޼��� �̸� = " +method + ", �ش� �޼��� ���ڰ��� = " +args.toString());
	
	}
	
	
}
