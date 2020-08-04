package com.geektrust.challenge.got.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class ResultTest {

	private static Kingdom ruler;

	@BeforeAll
	private static void setupBeforeAll() {
		ruler = new Kingdom("SPACE", "GORILLA");
	}

	@Test
	void testNulls() {
		Throwable th = assertThrows(NullPointerException.class, () -> new Result(null, null));
		assertEquals("ruler", th.getMessage());

		Throwable th2 = assertThrows(NullPointerException.class, () -> new Result(ruler, null));
		assertEquals("kingdomsWon", th2.getMessage());

		assertDoesNotThrow(() -> new Result(ruler, Collections.emptyList()));
	}

	@Test
	void testGetters() {
		List<Kingdom> kingdomsWon = Arrays.asList(new Kingdom("AIR", "Owl"), new Kingdom("FIRE", "Dragon"));
		Result result = new Result(ruler, kingdomsWon);
		
		assertEquals(ruler, result.getRuler());
		assertEquals(kingdomsWon, result.getKingdomsWon());
	}

	@Test
	void testNewResultBuilder() {
		assertNotSame(Result.newBuilder(), Result.newBuilder());
	}
	
	@Test
	void testEquality() {
		EqualsVerifier.forClass(Result.class)
			.usingGetClass()
			.verify();
	}

}
