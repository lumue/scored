package io.github.lumue.scored;

import java.util.Comparator;
import java.util.Optional;

/**
 * test a value against a continuous range of values for inclusion
 *
 * the range is defined as an interval with upper and lower boundaries
 * null for a boundary means unlimited
 *
 * Created by lm on 03.11.15.
 */
public class ContinuousValueRange<T> implements ValueRange<T>{

	private final Optional<T> lowerBoundary;

	private final Optional<T> upperBoundary;

	private final Comparator<T> comparator;

	private ContinuousValueRange(T min, T max, Comparator<T> comparator) {

		if(comparator==null)
			throw new NullPointerException("parameter comparator must not be null");
		this.comparator=comparator;

		this.lowerBoundary =Optional.ofNullable(min);
		this.upperBoundary =Optional.ofNullable(max);
	}


	@Override
	public boolean contains(T t) {
		return testLowerBoundary(t) && testUpperBoundary(t);
	}

	private boolean testLowerBoundary(T t) {
		return lowerBoundary.map(boundary -> new Boolean(comparator.compare(t, boundary)>=0))
							.orElseGet(() -> Boolean.TRUE);
	}

	private boolean testUpperBoundary(T t) {
		return upperBoundary.map(boundary -> new Boolean(comparator.compare(t, boundary)<=0))
				.orElseGet(() -> Boolean.TRUE);
	}

	public static <T> ContinuousValueRangeBuilder<T> builder(){
		return new ContinuousValueRangeBuilder();
	}


	public static class ContinuousValueRangeBuilder<T>{

		private Comparator<T> comparator;
		private T lowerBoundary;
		private T upperBoundary;

		public ContinuousValueRangeBuilder<T> withComparator(Comparator<T> comparator){
			this.comparator=comparator;
			return this;
		}

		public ContinuousValueRangeBuilder<T> withLowerBoundary(T lowerBoundary){
			this.lowerBoundary=lowerBoundary;
			return this;
		}

		public ContinuousValueRangeBuilder<T> withUpperBoundary(T upperBoundary){
			this.upperBoundary=upperBoundary;
			return this;
		}


		public ContinuousValueRange<T> build(){
			return new ContinuousValueRange<>(this.lowerBoundary,this.upperBoundary,this.comparator
			);
		}

	}
}
