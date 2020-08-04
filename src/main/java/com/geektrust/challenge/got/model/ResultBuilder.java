package com.geektrust.challenge.got.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.geektrust.challenge.got.annotation.NotThreadSafe;

/**
 * A convenient builder to build {@link Result} object.
 * 
 * @author Rajesh Debnath
 *
 */
@NotThreadSafe
public class ResultBuilder {

	private final List<Kingdom> kingdomsWon = new ArrayList<>();
	
	private Kingdom ruler;

	/**
	 * Sets the ruler.
	 * 
	 * @param ruler The ruler.
	 * @return {@literal this} builder.
	 */
	public ResultBuilder setRuler(Kingdom ruler) {
		this.ruler = ruler;
		return this;
	}
	
	/**
	 * Sets the {@code kingdoms} as the list of Kingdom's won.
	 * 
	 * @param kingdoms The kingdoms to be added.
	 * @return {@literal this} builder.
	 */
	public ResultBuilder setKingdomsWon(Collection<Kingdom> kingdoms) {
		kingdomsWon.clear();
		kingdomsWon.addAll(kingdoms);
		return this;
	}
	
	public Result build() {
		return new Result(ruler, kingdomsWon);
	}
	
}
