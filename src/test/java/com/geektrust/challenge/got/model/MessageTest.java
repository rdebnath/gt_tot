package com.geektrust.challenge.got.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class MessageTest {

	@Test
	void testNulls() {
		Throwable t = assertThrows(NullPointerException.class, () -> Message.of(null));
		assertEquals("rawMessage", t.getMessage());

		Throwable t2 = assertThrows(NullPointerException.class, () -> new Message<>(null));
		assertEquals("rawMessage", t2.getMessage());
		
		assertDoesNotThrow(() -> new Message<>(new Date()));
		assertDoesNotThrow(() -> new Message<>("my message"));
		assertDoesNotThrow(() -> Message.of(20L));
		assertDoesNotThrow(() -> Message.of("mmm"));
	}

	@Test
	void testEquality() {
		EqualsVerifier.forClass(Message.class)
			.usingGetClass()
			.verify();
	}
	
}
