package com.geektrust.challenge.got.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class ToStringBuilderTest {

	@Test
	void testNulls() {
		Throwable th = assertThrows(NullPointerException.class, () -> new ToStringBuilder(null));
		assertEquals("type", th.getMessage());

		assertDoesNotThrow(() -> new ToStringBuilder(Date.class));
		assertDoesNotThrow(() -> new ToStringBuilder(String.class));
	}

	@ParameterizedTest
	@ArgumentsSource(ToStringBuilderTestAddArgumentsProvider.class)
	void testAdd(String expected, Class<?> type, List<? extends Pair<String, Object>> variables) {
		ToStringBuilder strBuilder = new ToStringBuilder(type);
		variables.forEach(pair -> strBuilder.add(pair.getLeft(), pair.getRight()));
		assertEquals(expected, strBuilder.toString());
	}

}
