package io.github.lumue.scored;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * a scoring category tests a contains candidate beans
 * property against a set of value ranges.
 *
 * is the property value contained in the value set of a range,
 * a given number of points is added to the returned score.
 *
 * the returned score starts at 0
 *
 * CT Type of candidate bean
 * PT Type of property value
 *
 * Created by lm on 02.11.15.
 */
public class ScoringCategory<CT,PT> implements ScoreComputer<CT>,Serializable{

	private final PropertyAccessor<CT,PT> propertyAccessor;

	private final Collection<ScoredValueRange<PT>> scoredValueRanges;

	private ScoringCategory(PropertyAccessor<CT, PT> propertyAccessor, Collection<ScoredValueRange<PT>> valueRanges) {
		this.propertyAccessor = propertyAccessor;
		this.scoredValueRanges = valueRanges;
	}

	public  Integer computeScore(CT scoringCandidate) {
		PT candidateBeanValue=propertyAccessor.getValue(scoringCandidate);
		return scoredValueRanges.parallelStream().mapToInt(category -> category.computeScore(candidateBeanValue)).sum();
	}

	public static <CT,PT> ScoringCategoryBuilder<CT,PT> builder(){
		return new ScoringCategoryBuilder();
	}

	public static class ScoringCategoryBuilder<T,PT>{

		private final Collection<ScoredValueRange<PT>> scoredValueRanges =new ArrayList<>();
		private PropertyAccessor<T, PT> propertyAccessor;

		public ScoringCategoryBuilder<T,PT> addRange(ScoredValueRange<PT>... values){
			for (ScoredValueRange<PT> category:values) {
				this.scoredValueRanges.add(category);
			}
			return this;
		}

		public ScoringCategoryBuilder<T,PT> withPropertyAccessor(PropertyAccessor<T,PT> propertyAccessor){
			this.propertyAccessor=propertyAccessor;
			return this;
		}

		public ScoringCategory<T,PT> build(){
			return new ScoringCategory<>(this.propertyAccessor,this.scoredValueRanges);
		}
	}
}
