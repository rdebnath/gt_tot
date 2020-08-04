package com.geektrust.challenge.got.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import static com.geektrust.challenge.got.util.Strings.newToStringBuilder;

import com.geektrust.challenge.got.annotation.Immutable;
import com.geektrust.challenge.got.annotation.ImmutableType;

@Immutable(ImmutableType.WEAK)
public final class Message<T> {

	private final T rawMessage;
	
	public Message(T rawMessage) {
		this.rawMessage = requireNonNull(rawMessage, "rawMessage");
	}
	
	public T getRawMessage() {
		return rawMessage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(rawMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Message<?> other = (Message<?>) obj;
		return Objects.equals(rawMessage, other.rawMessage);
	}

	@Override
	public String toString() {
		return newToStringBuilder(this)
				.add("rawMessage", rawMessage)
				.toString();
	}
	
	public static <T> Message<T> of(T rawMessage) {
		return new Message<>(rawMessage);
	}
	
}
