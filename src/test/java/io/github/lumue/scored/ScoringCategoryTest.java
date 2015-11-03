package io.github.lumue.scored;


import io.github.lumue.scored.beans.TestBean;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by lm on 03.11.15.
 */
public class ScoringCategoryTest {

	@Test
	public void testComputeScore() throws Exception {

		DiscreteValueRange<String> valueRange = DiscreteValueRange.<String>builder().addValue("A").build();
		ScoringCategory<TestBean, String> scoringCategory = ScoringCategory.<TestBean, String>builder()
				.addRange(new ScoredValueRange<>(valueRange, 10))
				.withPropertyAccessor(bean -> bean.getText())
				.build();
		TestBean testBeanA=new TestBean("A",1);
		TestBean testBeanB=new TestBean("B",1);

		Assert.assertEquals(new Integer(10),scoringCategory.computeScore(testBeanA));
		Assert.assertEquals(new Integer(0),scoringCategory.computeScore(testBeanB));
	}
}
