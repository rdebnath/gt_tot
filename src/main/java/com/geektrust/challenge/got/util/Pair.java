package com.geektrust.challenge.got.util;

import static com.geektrust.challenge.got.util.Strings.newToStringBuilder;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

import com.geektrust.challenge.got.annotation.Immutable;
import com.geektrust.challenge.got.annotation.ImmutableType;
import com.geektrust.challenge.got.annotation.JustForTheChallenge;

/**
 * A class which represents a pair. It has a left value and a right value.
 * Neither of the values can be {@literal null}.
 * 
 * The immutable nature of this type depends on the immutable nature of left and
 * right values. If the either left or right or both are mutable then this
 * object cannot remain immutable.
 * 
 * @author Rajesh Debnath
 *
 * @param <L> Type of the left value.
 * @param <R> Type of the right value.
 */
@Immutable(ImmutableType.WEAK)
@JustForTheChallenge("Apache commons")
public final class Pair<L, R> {

	private final L left;

	private final R right;

	/**
	 * Constructs a pair object with supplied left and right values. Values must be
	 * non-null. The constructor is private so cannot be accessed from outside. Use
	 * {@link #of(Object, Object)} to create an instance.
	 * <p>
	 * This keeps various options open for future optimization such as creating
	 * object pool of this type, etc.
	 * 
	 * @param left  The left value of the pair. Must be non-null.
	 * @param right The right value of the pair. Must be non-null.
	 * @throws NullPointerException if any value is {@literal null}.
	 */
	private Pair(L left, R right) {
		this.left = requireNonNull(left, "left");
		this.right = requireNonNull(right, "right");
	}

	/**
	 * The left value held by the pair.
	 * 
	 * @return The left value.
	 */
	public L getLeft() {
		return left;
	}

	/**
	 * The right value held by the pair.
	 * 
	 * @return The right value.
	 */
	public R getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		return Objects.equals(left, other.left) && Objects.equals(right, other.right);
	}

	@Override
	public String toString() {
		return newToStringBuilder(this)
				.add("left", left)
				.add("right", right)
				.toString();
	}

	/**
	 * Factory method to create instance of {@link Pair}.
	 * 
	 * @param <L> The type of {@literal left}.
	 * @param <R> The type of {@literal right}.
	 * @param left The left value of the pair. Must be non-null.
	 * @param right The right value of the pair. Must be non-null.
	 * @return instance of {@link Pair} which hold supplied left and right values.
	 * @throws NullPointerException if any value is {@literal null}.
	 */
	public static <L, R> Pair<L, R> of(L left, R right) {
		return new Pair<>(left, right);
	}
}
