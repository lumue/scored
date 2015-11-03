package io.github.lumue.scored;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Score a bean by applying a series of ScoringCategory s
 * to a scoring candidate.
 *
 * the computed scores returned from the categories are
 *  * weighed and
 *  * summed up
 *
 * Created by lm on 02.11.15.
 */
public class ScoringProfile<T> implements ScoreComputer<T>,Serializable {


	private final Collection<WeightedScoringCategory<T>> categories;

	private ScoringProfile(Collection<WeightedScoringCategory<T>> categories) {
		this.categories = categories;
	}


	public Integer computeScore(T scoringCandidate){
		return categories.parallelStream().mapToInt(category -> category.computeScore(scoringCandidate)).sum();
	}

	public static <T> ScoringProfileBuilder<T> builder(){
		return new ScoringProfileBuilder<>();
	}

	public static class ScoringProfileBuilder<T>{

		private final Collection<WeightedScoringCategory<T>> categories=new ArrayList<>();

		public ScoringProfileBuilder<T> addCategory(WeightedScoringCategory<T>... values){
			for (WeightedScoringCategory<T> category:values) {
				this.categories.add(category);
			}
			return this;
		}

		public ScoringProfile<T> build(){
			return new ScoringProfile<>(categories);
		}
	}
}
