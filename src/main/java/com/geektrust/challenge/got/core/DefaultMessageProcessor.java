package com.geektrust.challenge.got.core;

import static com.geektrust.challenge.got.util.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

/**
 * The default implementation allows a Kingdom to be won if each character-count
 * in the (decrypted) message out numbers the character-count of the emblem.
 * 
 * For example, secret (but decrypted) message to the Land kingdom (emblem:
 * Panda) needs to have the letter '<b>p</b>','<b>n</b>','<b>d</b>' at-least
 * once and '<b>a</b>' at least twice. If he sends
 * <b>a</b>h<b>d</b>vv<b>n</b>xxx<b>a</b>utu<b>p</b> to the Land kingdom, he
 * will win them over.
 * 
 * @author Rajesh Debnath
 *
 */
class DefaultMessageProcessor implements MessageProcessor<String> {

	private final DecipherAlgo<String, Integer> decipherAlgo;

	private final ToIntFunction<Kingdom> keyGen;
	
	DefaultMessageProcessor(DecipherAlgo<String, Integer> decipherAlgo, ToIntFunction<Kingdom> keyGen) {
		this.decipherAlgo = requireNonNull(decipherAlgo, "decipherAlgo");
		this.keyGen = requireNonNull(keyGen, "keyGen");
	}

	@Override
	public Optional<Kingdom> sendMessage(Kingdom from, Pair<Kingdom, Message<String>> toKingdomMessagePair) {
		Kingdom to = toKingdomMessagePair.getLeft();
		checkArgument(!Objects.equals(from, to), () -> "Cannot send message to self: " + from);
		
		Message<String> encMessage = toKingdomMessagePair.getRight();
		
		Map<Character, Long> messageMap = extractCharacterCountMap(
				decipherAlgo.decipher(encMessage.getRawMessage(), keyGen.applyAsInt(to)));
		Map<Character, Long> emblemMap = extractCharacterCountMap(to.getEmblem());

		if (checkIfWon(emblemMap, messageMap)) {
			return Optional.of(to);
		}

		return Optional.empty();
	}

	private Map<Character, Long> extractCharacterCountMap(String str) {
		return str.chars().mapToObj(c -> (char) c)
				.map(Character::valueOf)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
	}

	private boolean checkIfWon(Map<Character, Long> emblemMap, Map<Character, Long> messageMap) {
		int size = emblemMap.size();
		if (size > messageMap.size()) {
			return false;
		}
		return emblemMap.entrySet().stream()
				.filter(e -> messageMap.containsKey(e.getKey()))
				.filter(e -> messageMap.get(e.getKey()) >= e.getValue())
				.count() == size;
	}

}
