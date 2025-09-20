package com.cloudtruss.peopledb.annotation;

import com.cloudtruss.peopledb.model.CrudOperation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MultiSQL.class) // This is wrapper container for the SQL annotation to allow it to be repeatable
public @interface SQL {
    String value() default "";
    CrudOperation operationType();
}
