package com.qb.dynamic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Aspect
public class DataSourceSwitchAspect {

	public static final String READ_NAME = "rDataSource";
	
	public static final String WRITE_NAME = "wDataSource";

	@Pointcut("execution(* com..*Dao.*(..))")
	public void switchDataSourcePointcut() {
	}

	@Around("switchDataSourcePointcut() && args(..)")
	public void aroundPerformCompletedJobPointcut(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		doSwitch(methodName);
		pjp.proceed();
	}

	private  void  doSwitch(String methodName){
		String lowerStr = methodName.toLowerCase();
		if(lowerStr.startsWith("read")
			||lowerStr.startsWith("select")
			||lowerStr.startsWith("list"))
			DBContextHolder.setDbType(READ_NAME);
		else
			DBContextHolder.setDbType(WRITE_NAME);
	}
}
