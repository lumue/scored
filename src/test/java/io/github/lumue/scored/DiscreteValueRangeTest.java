package io.github.lumue.scored;


import org.junit.Assert;
import org.junit.Test;


/**
 * Created by lm on 03.11.15.
 */
public class DiscreteValueRangeTest {

	@Test
	public void testContains() throws Exception {
		DiscreteValueRange<String> valueRange = DiscreteValueRange.<String>builder().addValue("A").build();
		Assert.assertTrue(valueRange.contains("A"));
		Assert.assertFalse(valueRange.contains("B"));
	}
}
