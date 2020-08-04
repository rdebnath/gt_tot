package com.geektrust.challenge.got.core;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Result;

public class DefaultResultEvaluator implements ResultEvaluator {

	private static final String NONE = "NONE";
	
	private static final String SPACE = " ";

	private final Predicate<Result> resultPredicate;
	
	public DefaultResultEvaluator(Predicate<Result> resultPredicate) {
		this.resultPredicate = requireNonNull(resultPredicate, "resultPredicate");
	}
	
	@Override
	public String evaluate(Result result) {
		if(resultPredicate.test(result)) {
			String names = result.getKingdomsWon().stream()
							.map(Kingdom::getName)
							.collect(Collectors.joining(SPACE));
			return (result.getRuler().getName() + SPACE + names).trim();
		}
		return NONE;
	}

}
