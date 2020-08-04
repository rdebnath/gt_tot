package com.geektrust.challenge.got.core;

import java.util.Optional;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

/**
 * A message processor which can be used to send encrypted message from one
 * kingdom to another.
 * 
 * @author Rajesh Debnath
 *
 * @param <T> The raw type of the message.
 */
public interface MessageProcessor<T> {

	/**
	 * Sends message {@code from} a Kingdom {@code to} another Kingdom.
	 * 
	 * @param from                 The kingdom which wants to send message.
	 * @param toKingdomMessagePair The pair of receiver kingdom and the (encrypted)
	 *                             message which is to be sent to it.
	 * @return An {@link Optional} containing the receiver Kingdom is returned if
	 *         the Kingdom is won. If the Kingdom is not won, empty {@link Optional}
	 *         is returned.
	 */
	Optional<Kingdom> sendMessage(Kingdom from, Pair<Kingdom, Message<T>> toKingdomMessagePair);

}
