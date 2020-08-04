package com.geektrust.challenge.got.util;

import static java.util.Objects.requireNonNull;

import java.util.StringJoiner;

import com.geektrust.challenge.got.annotation.JustForTheChallenge;
import com.geektrust.challenge.got.annotation.NotThreadSafe;

/**
 * A class specifically designed to help constructing descriptive string
 * suitable to be returned from {@link Object#toString()} methods.
 * <p>
 * The object of this class can be obtained from
 * {@link Strings#toStringBuilder()} methods. Can also be created directly.
 * 
 * <p>
 * Example:
 * 
 * <pre> {@code
 * java.awt.Point point = new java.awt.Point(10, 30);
 * String toStr = new ToStringBuilder(point.getClass())
 *                        .add("x", point.getX())
 *                        .add("y", point.getY())
 *                        .toString();
 * }
 * 
 * Value of {@code toStr} is "[Type=java.awt.Point, x=10, y=30]"
 * </pre>
 * 
 * @author Rajesh Debnath
 *
 */
@NotThreadSafe
@JustForTheChallenge("Guava")
public class ToStringBuilder {

	private final StringJoiner joiner = new StringJoiner(", ", "[", "]");

	/**
	 * Constructs an instance initialized with supplied {@code type}'s name. At this
	 * point invoking {@link #toString()} returns "[Type=<type's full name>]".
	 * 
	 * @param type The type of the object whose string for toString() is being
	 *             constructed.
	 * @throws NullPointerException if {@code type} is null.
	 */
	public ToStringBuilder(Class<?> type) {
		requireNonNull(type, "type");
		add("Type", type.getName());
	}

	/**
	 * Add a key-value description. The following string will be appended:
	 * "name=value".
	 * 
	 * @param name  The name of the variable.
	 * @param value The value of the variable.
	 * @return {@literal this} object. Useful for method-chaining.
	 */
	public ToStringBuilder add(String name, Object value) {
		joiner.add(name + "=" + value);
		return this;
	}

	@Override
	public String toString() {
		return joiner.toString();
	}

}
