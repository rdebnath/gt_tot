package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

class DefaultMessageProcessorTest {

	private static DecipherAlgo<String, Integer> decipherAlgo;

	@BeforeAll
	private static void setupBeforeAll() {
		decipherAlgo = (m, k) -> m;
	}

	@ParameterizedTest
	@ArgumentsSource(DefaultMessageProcessorTestSendMesssageArgumentsProvider.class)
	void testSendMessage(Kingdom expected, Kingdom from,
			Pair<Kingdom, Message<String>> toKingdomMessagePair) {
		DefaultMessageProcessor processor = new DefaultMessageProcessor(decipherAlgo, k -> 10);
		Optional<Kingdom> actual = processor.sendMessage(from, toKingdomMessagePair);
		assertEquals(Optional.ofNullable(expected), actual);
	}

	@Test
	void testSendMessageToSelf() {
		DefaultMessageProcessor processor = new DefaultMessageProcessor(decipherAlgo, k -> 10);

		Kingdom ruler = new Kingdom("SPACE", "PANDA");

		Throwable th = assertThrows(IllegalArgumentException.class,
				() -> processor.sendMessage(ruler, Pair.of(ruler, Message.of("SOMETHING"))));
		assertEquals(
				"Cannot send message to self: [Type=com.geektrust.challenge.got.model.Kingdom, name=SPACE, emblem=PANDA]",
				th.getMessage());
		
		Kingdom receiver = new Kingdom("AIR", "CROW");
		assertDoesNotThrow(
				() -> processor.sendMessage(ruler, Pair.of(receiver, Message.of("SOMETHING"))));
	}
	
}
