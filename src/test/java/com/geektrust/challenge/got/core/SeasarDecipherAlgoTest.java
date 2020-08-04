package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class SeasarDecipherAlgoTest {

	@ParameterizedTest
	@ArgumentsSource(SeaserDecipherAlgoTestDecipherArgumentsProvider.class)
	void testDecipher(String expected, CipherMode mode, String encrypted, Integer key) {
		SeasarDecipherAlgo algo = new SeasarDecipherAlgo(mode);
		assertEquals(expected, algo.decipher(encrypted, key));
	}

}
