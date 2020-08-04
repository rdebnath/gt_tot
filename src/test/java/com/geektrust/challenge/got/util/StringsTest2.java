package com.geektrust.challenge.got.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class StringsTest2 {

	/*
	 * Test case 1
	 */
	@ParameterizedTest
	@MethodSource("valuesForTestNewToStringBuilder")
	void testNewToStringBuilder(String expected, Class<?> type) {
		assertEquals(expected, Strings.newToStringBuilder(type).toString());
	}

	private static Object[][] valuesForTestNewToStringBuilder() {
		return new Object[][] {
			{ "[Type=java.lang.String]", String.class },							// 1
			{ "[Type=java.awt.Point]", Point.class },								// 2
			{ "[Type=java.lang.InstantiationError]", InstantiationError.class }		// 3
		};
	}
	
	/*
	 * Test case 2
	 */
	@ParameterizedTest
	@MethodSource("valuesForTestNewToStringBuilder2")
	void testNewToStringBuilder2(String expected, Object obj) {
		assertEquals(expected, Strings.newToStringBuilder(obj).toString());
	}

	private static Object[][] valuesForTestNewToStringBuilder2() {
		return new Object[][] {
			{ "[Type=java.lang.String]", "doesntmatter" },							// 1
			{ "[Type=java.awt.Point]", new Point(1, 2) },							// 2
			{ "[Type=java.lang.InstantiationError]", new InstantiationError() }		// 3
		};
	}
	
}
