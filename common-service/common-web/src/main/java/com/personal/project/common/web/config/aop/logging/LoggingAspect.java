package com.personal.project.common.web.config.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut(
            "@within(org.springframework.stereotype.Repository)" +
            "|| @within(org.springframework.stereotype.Service)" +
            "|| @within(org.springframework.web.bind.annotation.RestController)"
    )
    public void springBeanPointcut() { }


    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut(
            "within(com.personal.project.productservice.application.service..*)" +
                    " || within(com.personal.project.productservice.infrastructure.persistence.repository..*)" +
                    " || within(com.personal.project.productservice.presentation.web.rest..*)"
    )
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice.
     * @param exception exception.
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger(joinPoint)
                .error(
                        "Exception in {}() with cause = '{}' and exception = '{}'",
                        joinPoint.getSignature().getName(),
                        exception.getCause() != null ? exception.getCause() : "NULL",
                        exception.getMessage(),
                        exception
                );
    }

    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice.
     * @return result.
     * @throws Throwable throws {@link IllegalArgumentException}.
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }

    @Around("@annotation(com.personal.project.common.web.config.aop.logging.TrackTime)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;
            if (log.isDebugEnabled()) {
                log.info("Time Taken by {} is {}ms", joinPoint, timeTaken);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
