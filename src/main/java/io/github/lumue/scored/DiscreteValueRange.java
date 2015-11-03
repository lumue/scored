package io.github.lumue.scored;

import java.util.HashSet;
import java.util.Set;

/**
 * contains a value against a discrete set of valueSet for containment
 *
 * Created by lm on 03.11.15.
 */
public class DiscreteValueRange<T> implements ValueRange<T>{

	private final Set<T> valueSet;

	private DiscreteValueRange(Set<T> values) {
		if(values==null)
			throw new NullPointerException("parameter valueSet must not be null");
		this.valueSet = values;
	}

	public static <T> DiscreteValueRangeBuilder<T> builder(){
		return new DiscreteValueRangeBuilder<>();
	}

	@Override
	public boolean contains(T t) {
		return valueSet.contains(t);
	}

	public static class DiscreteValueRangeBuilder<T>{

		private final Set<T> valueSet=new HashSet<>();

		public DiscreteValueRangeBuilder<T> addValue(T... values){

			for (T value : values) {
				valueSet.add(value);
			}

			return this;
		}

		public DiscreteValueRange<T> build(){
			return new DiscreteValueRange<>(this.valueSet);
		}

	}
}
