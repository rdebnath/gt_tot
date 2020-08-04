package com.geektrust.challenge.got.annotation;

import static com.geektrust.challenge.got.util.Strings.EMPTY;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * This is a marker annotation. A class annotated with this means it is used
 * just for the sake of this challenge. Ideally, in a real production software a
 * third-party library would have been used instead.
 * 
 * <p>
 * The optional message is to tell what alternate library otherwise would have
 * been used.
 * 
 * @author Rajesh Debnath
 *
 */
@Target(ElementType.TYPE)
public @interface JustForTheChallenge {

	/**
	 * An optional message such as which third-party libraries would have been used.
	 * 
	 * @return The supplied message, defaults to zero-length string.
	 */
	String value() default EMPTY;

}
