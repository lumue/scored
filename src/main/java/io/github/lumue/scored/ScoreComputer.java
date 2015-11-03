package io.github.lumue.scored;

/**
 * classes that compute a score for beans in some way should implement this
 *
 * Created by lm on 03.11.15.
 */
@FunctionalInterface
public interface ScoreComputer<T> {
	Integer computeScore(T candidate);
}
