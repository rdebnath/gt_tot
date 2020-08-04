package com.geektrust.challenge.got.model;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.geektrust.challenge.got.annotation.Immutable;

/**
 * Holds the result of sending the messages to other kingdoms.
 * 
 * @author Rajesh Debnath
 *
 */
@Immutable
public final class Result {

	private final Kingdom ruler;

	private final List<Kingdom> kingdomsWon;

	public Result(Kingdom ruler, List<Kingdom> kingdomsWon) {
		this.ruler = requireNonNull(ruler, "ruler");
		this.kingdomsWon = Collections.unmodifiableList(requireNonNull(kingdomsWon, "kingdomsWon"));
	}

	/**
	 * Gets the ruler which sent the message to other kingdoms.
	 * 
	 * @return The ruler which sent the message to other kingdoms.
	 */
	public Kingdom getRuler() {
		return ruler;
	}

	/**
	 * Gets the list of kingdom's won.
	 * 
	 * @return The list of kingdom's won.
	 */
	public List<Kingdom> getKingdomsWon() {
		return kingdomsWon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ruler, kingdomsWon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		return Objects.equals(ruler, other.ruler) && Objects.equals(kingdomsWon, other.kingdomsWon);
	}

	/**
	 * A convenient static method to create a builder object.
	 * 
	 * @return A new {@link ResultBuilder} object.
	 */
	public static ResultBuilder newBuilder() {
		return new ResultBuilder();
	}
	
}
