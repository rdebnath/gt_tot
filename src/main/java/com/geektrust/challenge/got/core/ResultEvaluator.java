package com.geektrust.challenge.got.core;

import com.geektrust.challenge.got.model.Result;

/**
 * Evaluates the given result if it's successful or failure and produces an
 * output.
 * 
 * @author Rajesh Debnath
 *
 */
public interface ResultEvaluator {

	/**
	 * Evaluates the given {@code result} and produces an output.
	 * 
	 * @param result The result to be evaluated and produce an output.
	 * @return An output which depends on how the result has evaluated to i.e.
	 *         either success or failure.
	 */
	String evaluate(Result result);
	
}
