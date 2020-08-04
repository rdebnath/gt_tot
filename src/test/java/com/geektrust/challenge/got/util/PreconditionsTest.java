package com.geektrust.challenge.got.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PreconditionsTest {

	@Test
	void testCheckArgument() {
		Throwable t = assertThrows(IllegalArgumentException.class,
				() -> Preconditions.checkArgument(false, "condition is false"));
		assertEquals("condition is false", t.getMessage());

		Throwable t2 = assertThrows(IllegalArgumentException.class,
				() -> Preconditions.checkArgument(false, () -> "condition is false"));
		assertEquals("condition is false", t2.getMessage());

		assertDoesNotThrow(() -> Preconditions.checkArgument(true, "should not throw any exception"));
		assertDoesNotThrow(() -> Preconditions.checkArgument(true, () -> "should not throw any exception"));
	}

}
