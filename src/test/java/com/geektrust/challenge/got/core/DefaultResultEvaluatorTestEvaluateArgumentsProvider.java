package com.geektrust.challenge.got.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Result;

class DefaultResultEvaluatorTestEvaluateArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(new Arguments[] {
				Arguments.of("SPACE AIR LAND", (Predicate<Result>) r -> true, new Kingdom("SPACE", "GORILLA"), 		// 1
						Arrays.asList(new Kingdom("AIR", "BIRD"), new Kingdom("LAND", "TIGER"))),

				Arguments.of("NONE", (Predicate<Result>) r -> false, new Kingdom("SPACE", "GORILLA"), 				// 2
						Arrays.asList(new Kingdom("AIR", "BIRD"), new Kingdom("LAND", "TIGER"))),

				Arguments.of("WATER AIR LAND FIRE", (Predicate<Result>) r -> true, new Kingdom("WATER", "CROC"), 	// 3
						Arrays.asList(new Kingdom("AIR", "BIRD"), new Kingdom("LAND", "TIGER"),
								new Kingdom("FIRE", "GORILLA"))),

				Arguments.of("SPACE", (Predicate<Result>) r -> true, new Kingdom("SPACE", "GORILLA"),				// 4
						Collections.emptyList()),

				Arguments.of("NONE", (Predicate<Result>) r -> false, new Kingdom("SPACE", "GORILLA"),				// 5
						Collections.emptyList())
		});
	}

}
