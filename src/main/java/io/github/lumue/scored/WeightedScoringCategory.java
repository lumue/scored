package io.github.lumue.scored;

import java.io.Serializable;

/**
 * a weighted scoring category
 *
 * computes the score for a scoring category by
 * multiplying its result by a weight
 *
 * Created by lm on 03.11.15.
 */
public class WeightedScoringCategory<T> implements ScoreComputer<T>, Serializable {
	private final ScoringCategory<T, ?> category;
	private Integer weight;

	public WeightedScoringCategory(ScoringCategory<T, ?> category, Integer weight) {

		if(category==null)
			throw new NullPointerException(("parameter category must not be null"));

		this.category = category;
		this.weight = weight!=null?weight:new Integer(0);
	}


	@Override
	public Integer computeScore(T candidate) {
		return weight * category.computeScore(candidate);
	}
}
