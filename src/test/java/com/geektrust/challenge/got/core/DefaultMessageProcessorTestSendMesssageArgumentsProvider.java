package com.geektrust.challenge.got.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

class DefaultMessageProcessorTestSendMesssageArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		Kingdom to1 = new Kingdom("AIR", "BLUE");
		Kingdom to2 = new Kingdom("SPACE", "PANDA");
		
		return Stream.of(new Arguments[] {
				Arguments.of(to1, new Kingdom("SPACE", "GORILLA"),					// 1
						Pair.of(to1, new Message<>("MALBXIOEQSUK"))),
				
				Arguments.of(to2, new Kingdom("AIR", "BLUE"),						// 2
						Pair.of(to2, new Message<>("ABCDMMNDDAP"))),

				Arguments.of(null, new Kingdom("AIR", "BLUE"),						// 3
						Pair.of(to2, new Message<>("BCDMMNDDAP"))),

				Arguments.of(null, new Kingdom("SPACE", "GORILLA"),					// 4
						Pair.of(to1, new Message<>("BLU"))),
			});
	}

}
