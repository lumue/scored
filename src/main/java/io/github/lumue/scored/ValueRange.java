package io.github.lumue.scored;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * a range of values with the ability to contains if it contains a certain value
 *
 * Created by lm on 03.11.15.
 */
public interface ValueRange<T>  extends Serializable{
	boolean contains(T t);
}
