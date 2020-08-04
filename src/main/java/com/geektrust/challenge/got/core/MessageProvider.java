package com.geektrust.challenge.got.core;

import java.util.List;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.util.Pair;

/**
 * Provides raw input messages.
 * 
 * @author Rajesh Debnath
 *
 * @param <T> The type of the message.
 */
public interface MessageProvider<T> {

	/**
	 * Loads or reads the messages from the input {@code source}. Source might be a
	 * file, database or any other datasource.
	 * 
	 * @param source The datasource from where message are to be loaded.
	 */
	void load(Object source);
	
	/**
	 * Gets the list of messages to be sent and to the receiver Kingdom.
	 * 
	 * @return The list of messages to be sent and to receiver Kingdom.
	 */
	List<Pair<Kingdom, Message<T>>> getMessages();
	
}
