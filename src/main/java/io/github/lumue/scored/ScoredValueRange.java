package io.github.lumue.scored;

/**
 * associate  a score with a value range.
 *
 * the assigned score is returned for elements that exist in the value range
 *
 * Created by lm on 03.11.15.
 */
public class ScoredValueRange<T> implements ScoreComputer<T>{

	private final ValueRange<T> valueRange;
	private final int score;

	public ScoredValueRange(ValueRange<T> valueRange, int score) {

		if(valueRange==null)
			throw new NullPointerException("parameter valueRange must not be null");

		this.valueRange = valueRange;
		this.score = score;
	}

	@Override
	public Integer computeScore(T candidate) {
		return valueRange.contains(candidate)?score:0;
	}
}
