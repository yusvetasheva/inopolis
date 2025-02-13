package com.example.inopolis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * Это указание на точку среза (Pointcut) в AOP.
     * Оно означает: «Перехватывать все методы, которые помечены аннотацией @LoggingAfter».
     * То есть, если метод в коде аннотирован @LoggingAfter, то он попадет под этот Pointcut.
     */
    @Pointcut("@annotation(LoggingBefore)")
    public void logBefore() {
    }

    @Pointcut("@annotation(LoggingAfter)")
    public void logAfter() {
    }

    @Before("logBefore()")
    public void logB(JoinPoint joinPoint) {
        log.info("Вызов BEFORE метода: {}", joinPoint.getArgs());
    }

    @After("logAfter()")
    public void logA(JoinPoint joinPoint) {
        log.info("Вызов AFTER метода: {}", joinPoint.getTarget());
    }

}
