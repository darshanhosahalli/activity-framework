package io.github.darshanhosahalli.activity_framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a method as an activity.
 *
 * @author darshan
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Activity {

    /**
     * The name of the activity.
     *
     * @return The activity name.
     */
    String activity();

    /**
     * The type of the activity.
     *
     * @return The activity type.
     */
    Class<?> type() default String.class;
}