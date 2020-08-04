package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

class CipherModeTest {

	@Test
	void testBounds() {
		assertEquals(65, CipherMode.UPPER.getLowerBound());
		assertEquals(90, CipherMode.UPPER.getUpperBound());

		assertEquals(97, CipherMode.LOWER.getLowerBound());
		assertEquals(122, CipherMode.LOWER.getUpperBound());
	}

	@ParameterizedTest
	@MethodSource("valuesForTestIsKeyValid")
	void testIsKeyValid(boolean expected, int key) {
		assertEquals(expected, CipherMode.UPPER.isKeyValid(key));
		assertEquals(expected, CipherMode.LOWER.isKeyValid(key));
	}
	
	private static Object[][] valuesForTestIsKeyValid() {
		return new Object[][] {
			{ true, 4 },		// 1
			{ true, 25 },		// 2
			{ true, 12 },		// 3
			{ true, 21 },		// 4
			{ false, 0 },		// 5
			{ false, 26 },		// 6
			{ false, -2 },		// 7
			{ false, 66 }		// 8
		};
	}
	
	@ParameterizedTest
	@ArgumentsSource(CipherModeTestShouldModifyArgumentsProvider.class)
	void testShouldModify(boolean expected, CipherMode mode, Character ch) {
		assertEquals(expected, mode.shouldModify(ch));
	}
	
}
