package com.geektrust.challenge.got.model;

import static com.geektrust.challenge.got.util.Strings.checkNotBlank;
import static com.geektrust.challenge.got.util.Strings.newToStringBuilder;

import java.util.Objects;

import com.geektrust.challenge.got.annotation.Immutable;

/**
 * Represents a Kingdom which has a name and an emblem.
 * <p>
 * As a consistent practice, all model objects must be immutable.
 * 
 * @author Rajesh Debnath
 *
 */
@Immutable
public final class Kingdom {

	private final String name;

	private final String emblem;

	/**
	 * Constructs a Kingdom with supplied name and emblem.
	 * 
	 * @param name The name of the kingdom. Must not be blank.
	 * @param emblem The emblem of the kingdom. Must not be blank.
	 * @throws IllegalArgumentException if any of the string is blank.
	 */
	public Kingdom(String name, String emblem) {
		this.name = checkNotBlank(name, "name");
		this.emblem = checkNotBlank(emblem, "emblem");
	}

	/**
	 * Get the name of the kingdom.
	 * 
	 * @return name of the kingdom.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the emblem of the kingdom.
	 * 
	 * @return emblem of the kingdom.
	 */
	public String getEmblem() {
		return emblem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, emblem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Kingdom other = (Kingdom) obj;
		return Objects.equals(name, other.name) && Objects.equals(emblem, other.emblem);
	}

	@Override
	public String toString() {
		return newToStringBuilder(this)
				.add("name", name)
				.add("emblem", emblem)
				.toString();
	}

}
