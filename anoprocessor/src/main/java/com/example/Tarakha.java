package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oussama abdallah , AKA oussaki on 10/10/2017 , 4:51 PM.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Tarakha {
    int id();
    String name();
    String engineer() default "[unassigned]";

    String date() default "[unimplemented]";
}
