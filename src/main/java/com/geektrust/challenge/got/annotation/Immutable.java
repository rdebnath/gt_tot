package com.geektrust.challenge.got.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A marker annotation representing the annotated type is immutable.
 * 
 * @author Rajesh Debnath
 *
 */
@Target(ElementType.TYPE)
public @interface Immutable {

	/**
	 * A value to represent the degree of immutable nature this type has (see
	 * {@link ImmutableType}).
	 * 
	 * @return immutable type to represent the degree of immutable nature of this
	 *         type. Default is {@link ImmutableType#STRONG}.
	 */
	ImmutableType value() default ImmutableType.STRONG;

}
