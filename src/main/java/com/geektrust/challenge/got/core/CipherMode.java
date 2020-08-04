package com.geektrust.challenge.got.core;

import java.util.function.Predicate;

/**
 * The available modes in which a {@link SeasarDecipherAlgo} algorithm works.
 * 
 * @author Rajesh Debnath
 *
 */
public enum CipherMode {

	/**
	 * Assumes messages to be in upper case only.
	 */
	UPPER(Character::isUpperCase, 'A', 'Z'),
	
	/**
	 * Assumes messages to be in lower case only.
	 */
	LOWER(Character::isLowerCase, 'a', 'z');
	
	private final Predicate<Character> validCharPredicate;
	
	private final int lowerBound;
	
	private final int upperBound;
	
	private CipherMode(Predicate<Character> validCharPredicate, int lowerBound, int upperBound) {
		this.validCharPredicate = validCharPredicate;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	/**
	 * Tells if the character is valid to be modified. For example, if the mode is
	 * {@link #UPPER} then lower case letters are not modified and vice-versa.
	 * 
	 * @param ch The character to be checked if valid for modification.
	 * @return {@literal true} if valid to modify.
	 */
	public boolean shouldModify(Character ch) {
		return validCharPredicate.test(ch);
	}
	
	/**
	 * Gets the lower bound of the all valid alphabets.
	 * 
	 * @return The lower bound of the all valid alphabets.
	 */
	public int getLowerBound() {
		return lowerBound;
	}
	
	/**
	 * Gets the upper bound of the all valid alphabets.
	 * 
	 * @return The upper bound of the all valid alphabets.
	 */
	public int getUpperBound() {
		return upperBound;
	}
	
	/**
	 * Tells if the secret key is valid.
	 * 
	 * @param key The secret key.
	 * @return {@literal true} if the key is valid i.e. in the range (0, 26),
	 *         {@literal false} otherwise.
	 */
	public boolean isKeyValid(int key) {
		return key > 0 && key < 26;
	}
	
}
