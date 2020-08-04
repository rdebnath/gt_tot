package com.geektrust.challenge.got.util;

import java.util.function.Supplier;

import com.geektrust.challenge.got.annotation.JustForTheChallenge;

/**
 * An utility class which holds commonly needed methods when working with
 * {@link String}.
 * 
 * @author Rajesh Debnath
 *
 */
@JustForTheChallenge("Apache commons")
public class Strings {

	/**
	 * A reusable constant for zero-length string. Recommended to be used instead of
	 * {@literal ""}.
	 */
	public static final String EMPTY = "";

	/**
	 * Check if the input {@code str} is not blank. A string is considered blank if
	 * it is either null, zero-length string or contains whitespace only.
	 * 
	 * @param str     The input string which is to be checked if blank or not.
	 * @param message The message which will be used in the exception thrown when
	 *                the string is blank.
	 * @return The input {@code str} if it's not blank. Throws
	 *         {@link IllegalArgumentException} otherwise.
	 * @throws IllegalArgumentException If the given {@code str} is blank.
	 */
	public static String checkNotBlank(String str, String message) {
		return checkNotBlank(str, () -> message);
	}

	/**
	 * Check if the input {@code str} is not blank. A string is considered blank if
	 * it is either null, zero-length string or contains whitespace only.
	 * 
	 * @param str             The input string which is to be checked if blank or
	 *                        not.
	 * @param messageSupplier The message supplier which will be invoked only when
	 *                        the string is blank. The message is used in the
	 *                        exception thrown when the string is blank. The
	 *                        supplier must be side-effect free.
	 * @return The input {@code str} if it's not blank. Throws
	 *         {@link IllegalArgumentException} otherwise.
	 * @throws IllegalArgumentException If the given {@code str} is blank.
	 */
	public static String checkNotBlank(String str, Supplier<String> messageSupplier) {
		if (isBlank(str)) {
			throw new IllegalArgumentException(messageSupplier.get());
		}
		return str;
	}

	/**
	 * Checks if the given {@code str} is blank. A string is considered blank if it
	 * is either null, zero-length string or contains whitespace only.
	 * 
	 * @param str The input string which is to be checked if blank or not.
	 * @return {@literal true} if the string is blank, {@literal false} otherwise.
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * Checks if the given {@code str} is NOT blank. A string is considered blank if
	 * it is either null, zero-length string or contains whitespace only.
	 * 
	 * @param str The input string which is to be checked if it is NOT blank.
	 * @return {@literal true} if the string is NOT blank, {@literal false} if
	 *         string is blank.
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * A convenience method to create a string builder designed specifically to
	 * construct string for {@link Object#toString()} methods.
	 * 
	 * @param obj The object's class name is included in the string being
	 *            constructed for {@link Object#toString()}.
	 * @return A descriptive string suitable to be returned from
	 *         {@code obj's toString()} method.
	 */
	public static ToStringBuilder newToStringBuilder(Object obj) {
		return newToStringBuilder(obj.getClass());
	}

	/**
	 * A convenience method to create a string builder designed specifically to
	 * construct string for {@link Object#toString()} methods.
	 * 
	 * @param clazz The class' name is included in the string being constructed for
	 *              {@link Object#toString()}.
	 * @return A descriptive string suitable to be returned from
	 *         {@link Object#toString()} method.
	 */
	public static ToStringBuilder newToStringBuilder(Class<?> clazz) {
		return new ToStringBuilder(clazz);
	}

	/**
	 * Private default constructor to restrict creation of object.
	 */
	private Strings() {
	}

}
