package org.dummy.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class TimeAop {

    @Pointcut("execution(* org.dummy.aop.api.*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(org.dummy.aop.annotaion.Timer)")
    private void enableTimer() {

    }

    /**
     * Around 조건으로 And 사용
     */
    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 실제 로직 실행
        Object result = joinPoint.proceed();

        stopWatch.stop();
        log.info("total time = {}", stopWatch.getTotalTimeSeconds());
    }
}
