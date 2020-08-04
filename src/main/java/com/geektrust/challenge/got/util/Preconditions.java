package com.geektrust.challenge.got.util;

import java.util.function.Supplier;

import com.geektrust.challenge.got.annotation.JustForTheChallenge;

/**
 * A utility class to facilitate checking pre-conditions.
 * 
 * @author Rajesh Debnath
 *
 */
@JustForTheChallenge("Guava")
public class Preconditions {

	/**
	 * Does nothing if the {@code condition} is {@literal true}. If the condition is
	 * {@literal false}, {@link IllegalArgumentException} is thrown with the
	 * supplied {@code message}.
	 * 
	 * @param condition boolean value representing if a particular condition has
	 *                  been met.
	 * @param message   The message to be use when exception is thrown.
	 * @throws IllegalArgumentException If the {@code condition} is
	 *                                  {@literal false}.
	 */
	public static void checkArgument(boolean condition, String message) {
		checkArgument(condition, () -> message);
	}

	/**
	 * Does nothing if the {@code condition} is {@literal true}. If the condition is
	 * {@literal false}, {@link IllegalArgumentException} is thrown with the message
	 * supplied by {@code messageSupplier}.
	 * 
	 * @param condition       boolean value representing if a particular condition
	 *                        has been met.
	 * @param messageSupplier The message to be use when exception is thrown.
	 * @throws IllegalArgumentException If the {@code condition} is
	 *                                  {@literal false}.
	 */
	public static void checkArgument(boolean condition, Supplier<String> messageSupplier) {
		if (!condition) {
			throw new IllegalArgumentException(messageSupplier.get());
		}
	}

	private Preconditions() {
	}

}
