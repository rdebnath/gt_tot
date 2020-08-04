package com.geektrust.challenge.got.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Result;

class DefaultResultEvaluatorTest {

	@ParameterizedTest
	@ArgumentsSource(DefaultResultEvaluatorTestEvaluateArgumentsProvider.class)
	void testEvaluate(String expected, Predicate<Result> pred, Kingdom ruler, List<Kingdom> kingdomsWon) {
		ResultEvaluator formatter = new DefaultResultEvaluator(pred);
		Result result = new Result(ruler, kingdomsWon);
		assertEquals(expected, formatter.evaluate(result));
	}

}
