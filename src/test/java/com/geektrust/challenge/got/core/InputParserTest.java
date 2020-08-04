package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.geektrust.challenge.got.exception.ParseException;
import com.geektrust.challenge.got.util.Pair;

class InputParserTest {

	@ParameterizedTest
	@MethodSource("valuesForTestParseWithGoodInput")
	void testParseWithGoodInput(List<Pair<String, String>> expected, List<String> lines) {
		InputParser parser = new InputParser(s -> s);
		assertEquals(expected, parser.parse(lines));
	}

	private static Object[][] valuesForTestParseWithGoodInput() {
		return new Object[][] {
				{ Arrays.asList(Pair.of("KINGDOM_1", "SECRET_MSG_TO_KINGDOM_1"), Pair.of("AIR", "ROZO")),		// 1
						Arrays.asList("KINGDOM_1 SECRET_MSG_TO_KINGDOM_1", "AIR ROZO") },
				{ Arrays.asList(Pair.of("hh", "LOLO memo hoo")),												// 2
							Arrays.asList("hh  LOLO memo hoo") },
				{ Arrays.asList(Pair.of("AIR", "LOLO memo hoo")),												// 3
					Arrays.asList("AIR\tLOLO memo hoo") },
				{ Arrays.asList(Pair.of("AIR", "LOLO memo hoo")),												// 4
					Arrays.asList("AIR\nLOLO memo hoo") }
			};
	}
	
	@ParameterizedTest
	@MethodSource("valuesForTestParseWithBadInput")
	void testParseWithBadInput(List<String> lines) {
		InputParser parser = new InputParser(s -> s);
		assertThrows(ParseException.class, () -> parser.parse(lines));
	}

	@SuppressWarnings("unchecked")
	private static List<String>[] valuesForTestParseWithBadInput() {
		return new List[] {
				Arrays.asList(new String[] { null }),	// 1
				Arrays.asList(""),						// 2
				Arrays.asList("NOSPACE"),				// 3
			};
	}

	@ParameterizedTest
	@MethodSource("valuesForTestParseWithGoodInput2")
	void testParseWithGoodInput2(List<Pair<String, String>> expected, List<String> lines) {
		InputParser parser = new InputParser(String::toUpperCase);
		assertEquals(expected, parser.parse(lines));
	}

	private static Object[][] valuesForTestParseWithGoodInput2() {
		return new Object[][] {
				{ Arrays.asList(Pair.of("KINGDOM_1", "SECRET_MSG_TO_KINGDOM_1"), Pair.of("AIR", "ROZO")),	// 1
						Arrays.asList("KINGDOM_1 Secret_MSG_TO_KINGDOM_1", "AIr ROZO") },
				{ Arrays.asList(Pair.of("HH", "LOLO MEMO HOO")),											// 2
							Arrays.asList("hh  LOLO memo hoo") },
				{ Arrays.asList(Pair.of("AIR", "LOLO MEMO HOO")),											// 3
					Arrays.asList("AiR\tLOLO memo hoo") },
				{ Arrays.asList(Pair.of("AIR", "LOLO MEMO HOO")),											// 4
					Arrays.asList("air\nLOLO memo hoo") }
			};
	}

}
