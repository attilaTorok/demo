package com.example.demo.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LoggerAspect {
    
    @Pointcut("within(com.example.demo..*)")
    private void everythingInMyApplication() {}
    
    /**
     * Log every method before called in the application.
     *
     * @param joinPoint the {@link JoinPoint}.
     */
    @Before("com.example.demo.aop.LoggerAspect.everythingInMyApplication()")
    public void logEverything(JoinPoint joinPoint) {
        log.info("Called {}", joinPoint.getTarget().toString().split("@")[0] + "::" + joinPoint.getSignature().getName());
    }
    
}
