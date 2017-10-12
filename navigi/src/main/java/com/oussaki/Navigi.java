package com.oussaki;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oussama abdallah , AKA oussaki on 10/11/2017 , 5:44 PM.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Navigi {
    String action() default "new";
    Class target();

}
