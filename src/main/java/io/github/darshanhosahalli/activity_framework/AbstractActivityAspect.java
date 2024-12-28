package io.github.darshanhosahalli.activity_framework;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * Abstract aspect for handling activities.
 *
 * This aspect provides common functionality for before, afterReturning, afterThrowing, and around advice for methods annotated with the {@link Activity} annotation.
 */
@Aspect
@Order(1)
public class AbstractActivityAspect {

    /**
     * Before advice for methods annotated with {@link Activity}.
     *
     * @param joinPoint the join point
     * @param activity the activity annotation
     */
    @Before(value = "@annotation(activity)", argNames = "activity")
    public void methodForBefore(JoinPoint joinPoint, Activity activity) {
        this.before(activity);
    }

    /**
     * After returning advice for methods annotated with {@link Activity}.
     *
     * @param joinPoint the join point
     * @param activity the activity annotation
     * @param returnValue the return value of the method
     */
    @AfterReturning(value="@annotation(activity)", returning = "returnValue")
    public void methodForAfterReturning(JoinPoint joinPoint, Activity activity, Object returnValue) {
        this.afterReturning(activity, returnValue);
    }

    /**
     * After throwing advice for methods annotated with {@link Activity}.
     *
     * @param joinPoint the join point
     * @param activity the activity annotation
     * @param ex the exception thrown by the method
     */
    @AfterThrowing(pointcut="@annotation(activity)", throwing = "ex")
    public void methodForAfterThrowing(JoinPoint joinPoint, Activity activity, Exception ex) {
        this.afterThrowing(activity, ex);
    }

    /**
     * After advice for methods annotated with {@link Activity}.
     *
     * @param joinPoint the join point
     * @param activity the activity annotation
     */
    @After("@annotation(activity)")
    public void methodForAfter(JoinPoint joinPoint, Activity activity) {
        this.after(activity);
    }

    /**
     * Around advice for methods annotated with {@link Activity}.
     *
     * @param pjp the proceeding join point
     * @param activity the activity annotation
     * @return the return value of the method
     * @throws Throwable any exception thrown by the method
     */
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

    /**
     * Abstract method for before advice implementation.
     *
     * @param activity the activity annotation
     */
    public void before(Activity activity) {};

    /**
     * Abstract method for after returning advice implementation.
     *
     * @param activity the activity annotation
     * @param returnValue the return value of the method
     */
    public void afterReturning(Activity activity, Object returnValue) {};

    /**
     * Abstract method for after throwing advice implementation.
     *
     * @param activity the activity annotation
     * @param error the exception thrown by the method
     */
    public void afterThrowing(Activity activity, Exception error) {};

    /**
     * Abstract method for after advice implementation.
     *
     * @param activity the activity annotation
     */
    public void after(Activity activity) {};

    /**
     * Abstract method for around advice implementation (before).
     *
     * @param activity the activity annotation
     */
    public void around(Activity activity) {};

    /**
     * Abstract method for around advice implementation (after).
     *
     * @param activity the activity annotation
     * @param error the exception thrown by the method
     */
    public void around(Activity activity, Exception error) {};

}