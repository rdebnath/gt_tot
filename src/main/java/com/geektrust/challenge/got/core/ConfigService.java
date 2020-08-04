package com.geektrust.challenge.got.core;

/**
 * A configuration service which provides configuration accross the application.
 * Application must access configuration through this interface only.
 * 
 * @author Rajesh Debnath
 *
 */
public interface ConfigService {

	/**
	 * Get {@link String} value associated with {@code key}. If no key exists or if value is
	 * {@literal null}, returns {@code defaultValue}.
	 * 
	 * @param key          Key for which associated value to be fetched.
	 * @param defaultValue Value to be returned when no key or null value is found.
	 * @return value associated with {@code key}. If no key exists or if value is
	 *         {@literal null}, returns {@code defaultValue}.
	 */
	String getString(String key, String defaultValue);

	/**
	 * Get {@link Integer} value associated with {@code key}. If no key exists or if value is
	 * {@literal null}, returns {@code defaultValue}.
	 * 
	 * @param key          Key for which associated value to be fetched.
	 * @param defaultValue Value to be returned when no key or null value is found.
	 * @return value associated with {@code key}. If no key exists or if value is
	 *         {@literal null}, returns {@code defaultValue}.
	 */
	Integer getInteger(String key, Integer defaultValue);

	/**
	 * Get {@link Boolean} value associated with {@code key}. If no key exists or if value is
	 * {@literal null}, returns {@code defaultValue}.
	 * 
	 * @param key          Key for which associated value to be fetched.
	 * @param defaultValue Value to be returned when no key or null value is found.
	 * @return value associated with {@code key}. If no key exists or if value is
	 *         {@literal null}, returns {@code defaultValue}.
	 */
	Boolean getBoolean(String key, Boolean defaultValue);

}
