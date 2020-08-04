package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.util.Pair;

class KingdomRegistryTest {

	@Test
	void testGetters() {
		final MessageProcessor<String> processor = new DefaultMessageProcessor(new SeasarDecipherAlgo(CipherMode.UPPER),
				k -> k.getEmblem().length());
		Kingdom k1 = new Kingdom("SPACE", "Gorilla");
		Kingdom k2 = new Kingdom("AIR", "Owl");
		Kingdom k3 = new Kingdom("LAND", "Panda");
		
		Pair<Kingdom, MessageProcessor<String>> p1 = Pair.of(k1, processor);
		Pair<Kingdom, MessageProcessor<String>> p2 = Pair.of(k2, processor);
		Pair<Kingdom, MessageProcessor<String>> p3 = Pair.of(k3, processor);
		
		final Collection<Pair<Kingdom, MessageProcessor<String>>> kingdoms = Arrays.asList(p1, p2, p3);
		
		KingdomRegistry registry = new KingdomRegistry(kingdoms, k1);

		assertEquals(k1, registry.getKingdomByName("SPACE").orElseThrow(() -> new NullPointerException("SPACE")));
		assertEquals(k2, registry.getKingdomByName("AIR").orElseThrow(() -> new NullPointerException("AIR")));
		assertEquals(k3, registry.getKingdomByName("LAND").orElseThrow(() -> new NullPointerException("LAND")));
		assertFalse(registry.getKingdomByName("Land").isPresent());

		assertEquals(p1, registry.getKingdomProcessorPairByName("SPACE").orElseThrow(() -> new NullPointerException("SPACE")));
		assertEquals(p2, registry.getKingdomProcessorPairByName("AIR").orElseThrow(() -> new NullPointerException("AIR")));
		assertEquals(p3, registry.getKingdomProcessorPairByName("LAND").orElseThrow(() -> new NullPointerException("LAND")));
		assertFalse(registry.getKingdomProcessorPairByName("Land").isPresent());
		
		assertTrue(registry.contains("SPACE"));
		assertTrue(registry.contains("AIR"));
		assertTrue(registry.contains("LAND"));
		assertFalse(registry.contains("Land"));
		
		assertEquals(k1, registry.getRuler());
	}

	@Test
	void testExceptions() {
		Throwable th = assertThrows(NullPointerException.class, () -> new KingdomRegistry(null, null));
		assertEquals("kingdoms", th.getMessage());

		Throwable th2 = assertThrows(NullPointerException.class, () -> new KingdomRegistry(Collections.emptyList(), null));
		assertEquals("ruler", th2.getMessage());
		
		assertDoesNotThrow(() -> new KingdomRegistry(Collections.emptyList(), new Kingdom("SPACE", "Gorilla")));
	}

}
