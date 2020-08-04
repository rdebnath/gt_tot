package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

class FileInputMessageProviderTest {

	private static KingdomRegistry registry;
	
	@BeforeAll
	private static void setupBeforeAll() {
		final MessageProcessor<String> processor = new DefaultMessageProcessor(
				new SeasarDecipherAlgo(CipherMode.UPPER), k -> 5);
		final Kingdom ruler = new Kingdom("SPACE", "Gorilla");
		final Collection<Pair<Kingdom, MessageProcessor<String>>> kingdoms = Arrays.asList(
				Pair.of(ruler, processor),
				Pair.of(new Kingdom("LAND", "Panda"), processor),
				Pair.of(new Kingdom("WATER", "Octopus"), processor),
				Pair.of(new Kingdom("ICE", "Mammoth"), processor),
				Pair.of(new Kingdom("AIR", "Owl"), processor),
				Pair.of(new Kingdom("FIRE", "Dragon"), processor));
		
		registry = new KingdomRegistry(kingdoms, ruler);
	}
	
	@ParameterizedTest
	@MethodSource("valuesForTestLoadAngGetMessages")
	void testLoadAngGetMessages(List<Pair<Kingdom, Message<String>>> expected, String fileName) {
		FileInputMessageProvider provider = new FileInputMessageProvider(registry, new InputParser(s -> s));
		provider.load(fileName);
		List<Pair<Kingdom, Message<String>>> list = provider.getMessages();
		assertEquals(expected, list);
	}

	private static Object[][] valuesForTestLoadAngGetMessages() {
		return new Object[][] {
				{ Arrays.asList(Pair.of(new Kingdom("AIR", "Owl"), Message.of("ROZO")), 			// 1
						Pair.of(new Kingdom("LAND", "Panda"), Message.of("FAIJWJSOOFAMAU")),
						Pair.of(new Kingdom("ICE", "Mammoth"), Message.of("STHSTSTVSASOS"))),
						"src/test/resources/junit_sample/input_1.txt" },
			
				{ Arrays.asList(Pair.of(new Kingdom("AIR", "Owl"), Message.of("OWLAOWLBOWLC")), 	// 2
						Pair.of(new Kingdom("LAND", "Panda"), Message.of("OFBBMUFDICCSO")),
						Pair.of(new Kingdom("ICE", "Mammoth"), Message.of("VTBTBHTBBBOBAB")),
						Pair.of(new Kingdom("WATER", "Octopus"), Message.of("SUMMER IS COMING"))),
						"src/test/resources/junit_sample/input_2.txt" },

				{ Arrays.asList(Pair.of(new Kingdom("AIR", "Owl"), Message.of("OWLAOWLBOWLC")), 	// 3
						Pair.of(new Kingdom("LAND", "Panda"), Message.of("OFBBMUFDICCSO")),
						Pair.of(new Kingdom("ICE", "Mammoth"), Message.of("VTBTBHTBBBOBAB")),
						Pair.of(new Kingdom("WATER", "Octopus"), Message.of("SUMMER IS COMING"))),
						"src/test/resources/junit_sample/input_3.txt" },

				{ Arrays.asList(Pair.of(new Kingdom("AIR", "Owl"), Message.of("ROZO")), 			// 4
						Pair.of(new Kingdom("ICE", "Mammoth"), Message.of("STHSTSTVSASOS"))),
						"src/test/resources/junit_sample/input_4.txt" }		};
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void testLoadForBadInput(String fileName) {
		FileInputMessageProvider provider = new FileInputMessageProvider(registry, new InputParser(s -> s));
		assertThrows(Exception.class, () -> provider.load(fileName));
	}

}
