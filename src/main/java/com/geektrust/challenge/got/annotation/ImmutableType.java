package com.geektrust.challenge.got.annotation;

/**
 * An enum which holds degree of immutable nature of a type. To be used with
 * {@link Immutable} annotation.
 * 
 * @author Rajesh Debnath
 *
 */
public enum ImmutableType {

	/**
	 * Represents the type is absolutely immutable.
	 */
	STRONG,
	
	/**
	 * Represents the type is weakly immutable. For example, if the type is not
	 * final and hence the immutable nature possibly might be broken by subclasses
	 * (which is not recommended to do at all). Another example is if any of the
	 * dependent object is immutable, then the object's immutable nature is
	 * compromised as well.
	 */
	WEAK;
	
}
