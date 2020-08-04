package com.geektrust.challenge.got.core;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.exception.ParseException;
import com.geektrust.challenge.got.util.Pair;

/**
 * Parser to parse the given input of ruler and messages.
 * 
 * @author Rajesh Debnath
 *
 */
public class InputParser {

	private final UnaryOperator<String> preProcessor;
	
	public InputParser(UnaryOperator<String> preProcessor) {
		this.preProcessor = requireNonNull(preProcessor, "preProcessor");
	}
	
	/**
	 * Parses the given lines and creates a list of pairs (of Kingdom's name and secret message).
	 * <p>
	 * Examples:
	 * Input String						Pair
	 * -----------------------------------------------------------
	 * AIR ROZO							[AIR, ROZO]
	 * XYZ_1 PPPPP                      [XYZ_1, PPPPP]
	 * LAND SOME OTHER TEXT				[LAND, SOME OTHER TEXT]
	 * 
	 * @param lines The strings to be parsed split by space.
	 * @return List of pairs first word rest of the string.
	 * @throws ParseException If the input cannot be parsed.
	 */
	public List<Pair<String, String>> parse(List<String> lines) {
		return lines.stream()
				.map(this::splitNameMessage)
				.collect(Collectors.toList());
	}

	private Pair<String, String> splitNameMessage(String str) {
		try {
			String[] parts = str.split("\\s+", 2);
			return Pair.of(preProcessor.apply(parts[0]), preProcessor.apply(parts[1]));
		} catch(RuntimeException e) {
			throw new ParseException(e);
		}
	}
	
}
