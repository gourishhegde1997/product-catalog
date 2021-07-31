package com.springPractice.productcatalog.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.springPractice.productcatalog.repository.ProductRepository.*(..))")
    public void beforeProductRepositoryMethodCall(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().toShortString();
        logger.info("Triggered : " + className);
    }

    @After("execution(* com.springPractice.productcatalog.repository.ProductRepository.*(..))")
    public void afterProductRepositoryMethodCall(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().toShortString();
        logger.info("Returned from : " + className);
    }

}
