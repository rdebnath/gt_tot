package com.geektrust.challenge.got.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import nl.jqno.equalsverifier.EqualsVerifier;

class PairTest {

	@Test
	void testNulls() {
		Throwable th = assertThrows(NullPointerException.class, () -> Pair.of(null, null));
		assertEquals("left", th.getMessage());

		Throwable th2 = assertThrows(NullPointerException.class, () -> Pair.of("", null));
		assertEquals("right", th2.getMessage());

		assertDoesNotThrow(() -> Pair.of("", ""));
		assertDoesNotThrow(() -> Pair.of("LAND", "GORILLA"));
		assertDoesNotThrow(() -> Pair.of(new Object(), ""));
		assertDoesNotThrow(() -> Pair.of(new Date(), Integer.valueOf(20)));
	}

	@Test
	void testEquality() {
		EqualsVerifier.forClass(Pair.class)
			.usingGetClass()
			.verify();
	}
	
	@ParameterizedTest
	@MethodSource("valuesForTestGetters")
	void testGetters(Object left, Object right) {
		Pair<Object, Object> p = Pair.of(left, right);
		assertEquals(left, p.getLeft());
		assertEquals(right, p.getRight());	
	}

	private static Object[][] valuesForTestGetters() {
		return new Object[][] {
			{ "abc", "xyz" },				// 1
			{ "", " " },					// 2
			{ "ImLeft", "ImRight" },		// 3
			{ "same", "same" },				// 4
			{ "Hell", 20L },				// 5
			{ new Date(), "time" }			// 6
		};
	}
	
}
