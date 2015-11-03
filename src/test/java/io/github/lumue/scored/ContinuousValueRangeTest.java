package io.github.lumue.scored;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lm on 03.11.15.
 */
public class ContinuousValueRangeTest {

	@Test
	public void testContains() throws Exception{

		ContinuousValueRange<Integer> valueRange = ContinuousValueRange.<Integer>builder()
				.withLowerBoundary(0)
				.withUpperBoundary(10)
				.withComparator((o1, o2) -> o1.compareTo(o2))
				.build();

		Assert.assertTrue(valueRange.contains(0));
		Assert.assertTrue(valueRange.contains(5));
		Assert.assertTrue(valueRange.contains(10));

		Assert.assertFalse(valueRange.contains(-1));
		Assert.assertFalse(valueRange.contains(11));
	}

}
