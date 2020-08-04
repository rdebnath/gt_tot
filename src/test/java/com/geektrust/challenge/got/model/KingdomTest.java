package com.geektrust.challenge.got.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class KingdomTest {

	@Test
	void testNulls() {
		Throwable th = assertThrows(IllegalArgumentException.class, () -> new Kingdom(null, null));
		assertEquals("name", th.getMessage());

		Throwable th2 = assertThrows(IllegalArgumentException.class, () -> new Kingdom("", null));
		assertEquals("name", th2.getMessage());

		Throwable th3 = assertThrows(IllegalArgumentException.class, () -> new Kingdom("  ", null));
		assertEquals("name", th3.getMessage());

		Throwable th4 = assertThrows(IllegalArgumentException.class, () -> new Kingdom("LAND", null));
		assertEquals("emblem", th4.getMessage());

		Throwable th5 = assertThrows(IllegalArgumentException.class, () -> new Kingdom("xyz", ""));
		assertEquals("emblem", th5.getMessage());

		Throwable th6 = assertThrows(IllegalArgumentException.class, () -> new Kingdom("HHH", "  "));
		assertEquals("emblem", th6.getMessage());

		assertDoesNotThrow(() -> new Kingdom("LAND", "GORILLA"));
		assertDoesNotThrow(() -> new Kingdom("AIR", "Owl"));
	}

	@Test
	void testEquality() {
		EqualsVerifier.forClass(Kingdom.class)
			.usingGetClass()
			.verify();
	}
	
	@Test
	void testGetters() {
		Kingdom k = new Kingdom("abc", "xyz");
		assertEquals("abc", k.getName());
		assertEquals("xyz", k.getEmblem());
	}
	
}
