package com.geektrust.challenge.got.util;

import java.awt.Point;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

class ToStringBuilderTestAddArgumentsProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		return Stream.of(new Arguments[] {
				
				Arguments.of("[Type=java.util.Date, time=200, hello=hi]", Date.class,
						Arrays.asList(Pair.of("time", 200),
								Pair.of("hello", "hi"))),
				
				Arguments.of("[Type=java.awt.Point, x=20, y=30, time=1970-01-01T00:00:00.100Z]", Point.class,
						Arrays.asList(Pair.of("x", 20),
								Pair.of("y", 30),
								Pair.of("time", Instant.ofEpochMilli(100))))
				
		});
	}

}
