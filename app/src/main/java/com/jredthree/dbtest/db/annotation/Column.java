package com.jredthree.dbtest.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: smart
 * time: 2016/12/16
 */

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "";
    String columnDefinition() default "";
    boolean nullable() default true;
    boolean insertable() default true;
    boolean updateable() default false;
    int length() default 255;
}
