package com.geektrust.challenge.got.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Supplier;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringsTest {

	/*
	 * Test case 1
	 */
	@ParameterizedTest
	@MethodSource("valuesForTestIsBlank")
	void testIsBlank(boolean expected, String str) {
		assertEquals(expected, Strings.isBlank(str));
	}

	private static Object[][] valuesForTestIsBlank() {
		return new Object[][] {
			{ Boolean.TRUE, "" },		// 1
			{ Boolean.TRUE, " " },		// 2
			{ Boolean.TRUE, "\t" },		// 3
			{ Boolean.TRUE, "\n" },		// 4
			{ Boolean.FALSE, "abc" },	// 5
			{ Boolean.FALSE, "1" },		// 6
			{ Boolean.FALSE, "n" }		// 7
		};
	}
	
	/*
	 * Test case 2
	 */
	@ParameterizedTest
	@ValueSource(strings = { "", "  ", " ", "\t", "\n", "\r" })
	@NullAndEmptySource
	void testIsBlankForNullAndEmpty(String str) {
		assertTrue(Strings.isBlank(str));
	}
	
	/*
	 * Test case 3
	 */
	@ParameterizedTest
	@CsvSource({ "abc, should not be null", "pqr, ", "hi, \"\"", "something present, non-zero length" })
	void testCheckNotBlankForGoodStrings(String inputStr, String message) {
		assertEquals(inputStr, Strings.checkNotBlank(inputStr, message));
	}
	
	/*
	 * Test case 4
	 */
	@ParameterizedTest
	@MethodSource("valuesForTestCheckNotBlankForBadStrings")
	void testCheckNotBlankForBadStrings(String inputStr, String message) {
		Throwable t = assertThrows(IllegalArgumentException.class, () -> Strings.checkNotBlank(inputStr, message));
		assertEquals(message, t.getMessage());
	}
	
	private static String[][] valuesForTestCheckNotBlankForBadStrings() {
		return new String[][] {
			{ null, "null not allowed" },	// 1
			{ "", "empty string" },			// 2
			{ "  ", "spaces" },				// 3
			{ " ", "space" },				// 4
			{ "\t", "a tab" },				// 5
			{ "\n", "new line string" },	// 6
			{ "\r", "a return" }			// 7
		};
	}

	/*
	 * Test case 5
	 */
	@ParameterizedTest
	@MethodSource("valuesForTestCheckNotBlankForBadStrings2")
	void testCheckNotBlankForBadStrings2(String inputStr, Supplier<String> message) {
		Throwable t = assertThrows(IllegalArgumentException.class, () -> Strings.checkNotBlank(inputStr, message));
		assertEquals(message.get(), t.getMessage());
	}
	
	private static Object[][] valuesForTestCheckNotBlankForBadStrings2() {
		return new Object[][] {
			{ null, messageSupplier("null not allowed") },	// 1
			{ "", messageSupplier("empty string") },		// 2
			{ "  ", messageSupplier("spaces") },			// 3
			{ " ", messageSupplier("space") },				// 4
			{ "\t", messageSupplier("a tab") },				// 5
			{ "\n", messageSupplier("new line string") },	// 6
			{ "\r", messageSupplier("a return") }			// 7
		};
	}

	private static Supplier<String> messageSupplier(String message) {
		return () -> message;
	}
	
}
