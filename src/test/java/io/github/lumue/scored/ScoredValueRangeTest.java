package io.github.lumue.scored;

import io.github.lumue.scored.beans.TestBean;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lm on 02.11.15.
 */
public class ScoredValueRangeTest {


	@Test
	public void testCompute() throws Exception {

		DiscreteValueRange<String> valueRange = DiscreteValueRange.<String>builder().addValue("A").build();

		ScoredValueRange<String> scoredValueRange = new ScoredValueRange<>(valueRange, 10);

		Assert.assertEquals(new Integer(10),scoredValueRange.computeScore("A"));
		Assert.assertEquals(new Integer(0),scoredValueRange.computeScore("B"));
	}
}
