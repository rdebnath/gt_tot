package com.geektrust.challenge.got.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ResultBuilderTest {

	@Test
	void testBuild() {
		Kingdom ruler = new Kingdom("SPACE", "GORILLA");
		List<Kingdom> kingdomsWon = Arrays.asList(new Kingdom("AIR", "Owl"), new Kingdom("FIRE", "Dragon"));
		
		Result result = new ResultBuilder()
							.setRuler(ruler)
							.setKingdomsWon(kingdomsWon)
							.build();
		assertEquals(ruler, result.getRuler());
		assertEquals(kingdomsWon, result.getKingdomsWon());
	}

	@Test
	void testSetKingdomsWon() {
		Kingdom ruler = new Kingdom("SPACE", "GORILLA");
		List<Kingdom> kingdomsWon = Arrays.asList(new Kingdom("AIR", "Owl"), new Kingdom("FIRE", "Dragon"));
		
		ResultBuilder resultBuilder = new ResultBuilder().setRuler(ruler);
		Result result = resultBuilder.setKingdomsWon(kingdomsWon).build();
		
		assertEquals(kingdomsWon, result.getKingdomsWon());
		
		List<Kingdom> kingdomsWon2 = Arrays.asList(new Kingdom("WATER", "Fish"));
		Result result2 = resultBuilder.setKingdomsWon(kingdomsWon2).build();
		
		assertEquals(kingdomsWon2, result2.getKingdomsWon());
	}
	
}
