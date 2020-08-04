package com.geektrust.challenge.got.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A marker annotation representing the annotated type is not thread-safe.
 * 
 * @author Rajesh Debnath
 *
 */
@Target({ ElementType.TYPE })
public @interface NotThreadSafe {
}
