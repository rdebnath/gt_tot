package com.geektrust.challenge.got.core;

import static com.geektrust.challenge.got.util.Strings.checkNotBlank;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.exception.RuntimeIOException;
import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;
import com.geektrust.challenge.got.util.Strings;

/**
 * A specific message provider which reads from the given file. 
 * 
 * @author Rajesh Debnath
 *
 */
class FileInputMessageProvider implements MessageProvider<String> {

	private static final String COMMENT = "#";
	
	private static final String[] EMPTY = new String[0];

	private final AtomicReference<List<Pair<Kingdom, Message<String>>>> messageListRef = new AtomicReference<>();

	private final KingdomRegistry registry;
	
	private final InputParser parser;
	
	/**
	 * Create instance with supplied arguments. Does not load anything yet, just
	 * initializes the object.
	 * 
	 * @param registry The kingdom registry.
	 * @param filePath Path of the file to be read (Absolute/Relative).
	 * @param parser The parser to be used to parse the input file.
	 * @throws RuntimeIOException If there is any error reading the file.
	 */
	FileInputMessageProvider(KingdomRegistry registry, InputParser parser) {
		this.registry = requireNonNull(registry, "registry");
		this.parser = requireNonNull(parser, "parser");
	}
	
	/**
	 * Source here is path of the file to be read (Absolute/Relative).
	 * 
	 * @param source Path of the file from which messages are to be loaded.
	 * @throws NullPointerException If {@code source} is null.
	 * @throws IllegalArgumentException If {@code source}.toString() is blank. 
	 */
	@Override
	public void load(Object source) {
		requireNonNull(source, "source");
		String filePath = source.toString();
		checkNotBlank(filePath, "file path is blank");
		messageListRef.getAndUpdate(list -> readListIfRequired(filePath, list));
	}

	private List<Pair<Kingdom, Message<String>>> readListIfRequired(String filePath,
			List<Pair<Kingdom, Message<String>>> list) {
		if (list == null) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(filePath, EMPTY)).stream()
						.filter(Strings::isNotBlank)
						.filter(s -> !s.startsWith(COMMENT))
						.collect(Collectors.toList());
				
				return Collections.unmodifiableList(parser.parse(lines)
						.stream()
						.filter(p -> registry.contains(p.getLeft()))
						.map(this::convertToPair)
						.collect(Collectors.toList()));
			} catch (IOException e) {
				throw new RuntimeIOException(e);
			}
		}

		return list;
	}
	
	private Pair<Kingdom, Message<String>> convertToPair(Pair<String, String> rawPair) {
		Kingdom k = registry.getKingdomByName(rawPair.getLeft())
				.orElseThrow(() -> new IllegalArgumentException("A kingdom not found: " + rawPair.getLeft()));
		return Pair.of(k, Message.of(rawPair.getRight()));
	}
	
	@Override
	public List<Pair<Kingdom, Message<String>>> getMessages() {
		return messageListRef.get();
	}

}
