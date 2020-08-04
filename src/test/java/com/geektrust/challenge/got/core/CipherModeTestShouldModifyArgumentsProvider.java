package com.geektrust.challenge.got.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

class CipherModeTestShouldModifyArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(new Arguments[] {
				Arguments.of(true, CipherMode.UPPER, 'A'),	// 1
				Arguments.of(true, CipherMode.UPPER, 'Z'),	// 2
				Arguments.of(true, CipherMode.UPPER, 'M'),	// 3
				Arguments.of(false, CipherMode.UPPER, 'a'),	// 4
				Arguments.of(false, CipherMode.UPPER, '5'),	// 5
				Arguments.of(true, CipherMode.LOWER, 'a'),	// 6
				Arguments.of(true, CipherMode.LOWER, 'z'),	// 7
				Arguments.of(true, CipherMode.LOWER, 'n'),	// 8
				Arguments.of(false, CipherMode.LOWER, 'A'),	// 9
				Arguments.of(false, CipherMode.LOWER, '5'),	// 10
		});
	}

}
