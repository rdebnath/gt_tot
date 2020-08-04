package com.geektrust.challenge.got.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

class SeaserDecipherAlgoTestDecipherArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(new Arguments[] {
				Arguments.of("OLWL", CipherMode.UPPER, "ROZO", 3),		// 1
				Arguments.of("OL1WL", CipherMode.UPPER, "RO1ZO", 3),	// 2
				Arguments.of("YXVNK", CipherMode.UPPER, "AZXPM", 2),	// 3
				Arguments.of("YXvNK", CipherMode.UPPER, "AZvPM", 2),	// 4
				Arguments.of("BCZA", CipherMode.UPPER, "ABYZ", 25),		// 5
				Arguments.of("olwl", CipherMode.LOWER, "rozo", 3),		// 6
				Arguments.of("ol1wl", CipherMode.LOWER, "ro1zo", 3),	// 7
				Arguments.of("yxvnk", CipherMode.LOWER, "azxpm", 2),	// 8
				Arguments.of("yxVNk", CipherMode.LOWER, "azVNm", 2),	// 9
				Arguments.of("bcza", CipherMode.LOWER, "abyz", 25),		// 10
		});
	}

}
