package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SystemPropertyConfigServiceTest {

	@BeforeAll
	private static void setUpBeforeClass() {
		System.setProperty("strKey", "Hello");
		System.setProperty("intKey", "100");
		System.setProperty("boolKey", "true");
		System.setProperty("bool2", "false");
	}

	@AfterAll
	private static void tearDownAfterClass() {
		System.clearProperty("strKey");
		System.clearProperty("intKey");
		System.clearProperty("boolKey");
		System.clearProperty("bool2");
	}

	@Test
	void testGetters() {
		SystemPropertyConfigService config = new SystemPropertyConfigService();

		assertEquals("Hello", config.getString("strKey", "hi"));
		assertEquals("hi", config.getString("doesnotexist", "hi"));
		
		assertEquals(100, config.getInteger("intKey", 10));
		assertEquals(10, config.getInteger("notthere", 10));
		
		assertTrue(config.getBoolean("boolKey", false));
		assertFalse(config.getBoolean("bool2", true));
		assertTrue(config.getBoolean("nothing", true));
		assertFalse(config.getBoolean("nothing", false));
	}

}
