package com.activity.framework;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class AbstractActivityAspect {

    @Before(value = "@annotation(activity)", argNames = "activity")
    public void methodForBefore(JoinPoint joinPoint, Activity activity) {
        this.before(activity);
    }

    @AfterReturning(value="@annotation(activity)", returning = "returnValue")
    public void methodForAfterReturning(JoinPoint joinPoint, Activity activity, Object returnValue) {
        this.afterReturning(activity, returnValue);
    }

    @AfterThrowing(pointcut="@annotation(activity)", throwing = "ex")
    public void methodForAfterThrowing(JoinPoint joinPoint, Activity activity, Exception ex) {
        this.afterThrowing(activity, ex);
    }

    @After("@annotation(activity)")
    public void methodForAfter(JoinPoint joinPoint, Activity activity) {
        this.after(activity);
    }

    @Around("@annotation(activity)")
    public String methodForAround(ProceedingJoinPoint pjp, Activity activity) throws Throwable {
        try {
            this.around(activity);
            String retVal = (String) pjp.proceed();
            this.around(activity);
            return retVal;
        } catch(Exception error) {
            this.around(activity, error);
            throw error;
        }
    }

    public void before(Activity activity) {};

    public void afterReturning(Activity activity, Object returnValue) {};

    public void afterThrowing(Activity activity, Exception error) {};

    public void after(Activity activity) {};

    public void around(Activity activity) {};

    public void around(Activity activity, Exception error) {};

}