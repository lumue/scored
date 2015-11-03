package io.github.lumue.scored;

/**
 * access a beans property
 *
 * Created by lm on 03.11.15.
 */
@FunctionalInterface
public interface PropertyAccessor<T,R> {
	R getValue(T bean);
}
