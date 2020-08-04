package com.geektrust.challenge.got.core;

/**
 * This interface represents a Decipher algorithm which needs a secret key.
 * 
 * @author Rajesh Debnath
 *
 * @param <M> Type of the message.
 * @param <K> Type of the secret key.
 */
public interface DecipherAlgo<M, K> {

	/**
	 * Deciphers the given {@code message} using the secret {@code key}.
	 * 
	 * @param message The message to be deciphered.
	 * @param key The secret key used to decipher.
	 * @return The deciphered/decoded message.
	 */
	M decipher(M message, K key);

}
