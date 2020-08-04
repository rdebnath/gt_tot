package com.geektrust.challenge.got.core;

import java.util.function.Function;

import com.geektrust.challenge.got.annotation.JustForTheChallenge;

/**
 * An implementation of {@link ConfigService} which reads System properties.
 * 
 * @author Rajesh Debnath
 *
 */
@JustForTheChallenge("Typesafe")
class SystemPropertyConfigService implements ConfigService {

	@Override
	public String getString(String key, String defaultValue) {
		return System.getProperty(key, defaultValue);
	}

	@Override
	public Integer getInteger(String key, Integer defaultValue) {
		 return convertValue(Integer::valueOf, key, defaultValue);
	}

	@Override
	public Boolean getBoolean(String key, Boolean defaultValue) {
		return convertValue(Boolean::valueOf, key, defaultValue);
	}
	
	/*
	 * A useful general purpose converter written to facilitate other getters of
	 * primitive types (e.g. getLong(), getDouble() etc.)
	 */
	private <R> R convertValue(Function<String, R> valueConvertor, String key, R defaultValue) {
		String propVal = System.getProperty(key);
		if(propVal == null) {
			return defaultValue;
		}
		return valueConvertor.apply(System.getProperty(key));
	}
	
}
