package com.geektrust.challenge.got.core;

import static com.geektrust.challenge.got.util.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

/**
 * A decipher algorithm which moves each letter of the given message (of type
 * {@link String}) by provided key (of {@link Integer} type). The algorithm works
 * in modes mentioned in {@link CipherMode}.
 * 
 * <p>
 * Examples:
 * <pre> CIPHER TEXT  KEY  DECIPHERED TEXT
 * --------------------------------------------------------------
 * ROZO         3     OLWL
 * AZXPM        2     YXVNK
 * ABYZ	      25    BCZA
 * rozo         3     olwl
 * azxpm        2     yxvnk
 * abyz	      25    bcza
 * </pre>
 * 
 * @author Rajesh Debnath
 *
 */
public class SeasarDecipherAlgo implements DecipherAlgo<String, Integer> {

	private final CipherMode mode;
	
	private final int lowerBound;
	
	private final int upperBound;
	
	/**
	 * Creates the decipher algo instance as per the supplied {@code mode}.
	 * 
	 * @param mode The mode in which the algo should work.
	 */
	public SeasarDecipherAlgo(CipherMode mode) {
		this.mode = requireNonNull(mode, "mode");
		this.lowerBound = mode.getLowerBound();
		this.upperBound = mode.getUpperBound();
	}
	
	@Override
	public String decipher(String message, Integer key) {
		int iKey = key;
		checkArgument(mode.isKeyValid(iKey), () -> "Invalid secret key");
		return message.chars()
				.mapToObj(i -> moveBy(i, iKey))
				.collect(Collectors.joining());
	}

	private String moveBy(int current, int key) {
		if (mode.shouldModify((char) current)) {
			final int newPos = current - key;
			if (newPos < lowerBound) {
				return String.valueOf((char) (newPos - lowerBound + 1 + upperBound));
			} else {
				return String.valueOf((char) newPos);
			}
		} else {
			return String.valueOf((char) current);
		}
	}

}
