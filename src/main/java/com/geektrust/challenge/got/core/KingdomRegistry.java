package com.geektrust.challenge.got.core;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.annotation.Immutable;
import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.util.Pair;

/**
 * Registry of all available Kingdoms.
 * 
 * @author Rajesh Debnath
 *
 */
@Immutable
public final class KingdomRegistry {

	private final Kingdom ruler;
	
	private final Map<String, Pair<Kingdom, MessageProcessor<String>>> nameToKingdomMap;

	/**
	 * Creates the registry with the supplied kingdoms and the ruler.
	 * 
	 * @param kingdoms The collection of pair of kingdom and respective processor to
	 *                 be initialized with.
	 * @param ruler    The ruler amongst all the kingdoms. This must be present in
	 *                 the {@code kingdoms}.
	 */
	KingdomRegistry(Collection<Pair<Kingdom, MessageProcessor<String>>> kingdoms, Kingdom ruler) {
		requireNonNull(kingdoms, "kingdoms");
		this.ruler = requireNonNull(ruler, "ruler");
		this.nameToKingdomMap = Collections
				.unmodifiableMap(kingdoms.stream().collect(Collectors.toMap(p -> p.getLeft().getName(), p -> p)));
	}

	/**
	 * Gets the Kingdom whose {@code name} matches the supplied value.
	 * 
	 * @param name Name of the kingdom to be looked up.
	 * @return An {@link Optional} containing the kingdom whose name matches the
	 *         supplied {@code name}. If none found, empty {@link Optional} is
	 *         returned.
	 */
	public Optional<Kingdom> getKingdomByName(String name) {
		return getKingdomProcessorPairByName(name).map(Pair::getLeft);
	}

	/**
	 * Gets the pair of Kingdom-MessageProcessor whose {@code name} matches the
	 * supplied value.
	 * 
	 * @param name Name of the kingdom-processor pair to be looked up.
	 * @return An {@link Optional} containing the pair of kingdom-processor whose
	 *         name matches the supplied {@code name}. If none found, empty
	 *         {@link Optional} is returned.
	 */
	public Optional<Pair<Kingdom, MessageProcessor<String>>> getKingdomProcessorPairByName(String name) {
		return Optional.ofNullable(nameToKingdomMap.get(name));
	}

	/**
	 * Lets know if a Kingdom exists in the registry with the given {@code name}.
	 * 
	 * @param name The name of the kingdom to be checked.
	 * @return {@literal true} if there exists a kingdom with that {@code name},
	 *         {@literal false} otherwise.
	 */
	public boolean contains(String name) {
		return nameToKingdomMap.containsKey(name);
	}

	/**
	 * Gets the ruler.
	 * 
	 * @return The ruler.
	 */
	public Kingdom getRuler() {
		return ruler;
	}
	
}
