package com.qb.dynamic;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Aspect
public class DataSourceSwitchAspect {

	public   String readName = "rDataSource";
	
	public   String writeName = "wDataSource";

	public String getReadName() {
		return readName;
	}

	public void setReadName(String readName) {
		this.readName = readName;
	}

	public String getWriteName() {
		return writeName;
	}

	public void setWriteName(String writeName) {
		this.writeName = writeName;
	}

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
			DBContextHolder.setDbType(readName);
		else
			DBContextHolder.setDbType(writeName);
	}
}
