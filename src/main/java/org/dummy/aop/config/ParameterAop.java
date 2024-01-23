package org.dummy.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ParameterAop {

    @Pointcut("execution(* org.dummy.aop.api.*.*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinpoint) {
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        log.info("method 정보 = {}", method);

        Object[] args = joinpoint.getArgs();
        for (Object arg : args) {
            log.info("type = {}, value = {}", arg.getClass().getSimpleName(), arg);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        log.info("returnObj = {}", returnObj);
    }
}
