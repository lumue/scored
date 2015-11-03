package io.github.lumue.scored;

import io.github.lumue.scored.beans.TestBean;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lm on 02.11.15.
 */
public class ScoringProfileTest {


	@Test
	public void testCompute() throws Exception {

		DiscreteValueRange<String> valueRange = DiscreteValueRange.<String>builder().addValue("A").build();
		ScoringCategory<TestBean, String> scoringCategory = ScoringCategory.<TestBean, String>builder()
				.addRange(new ScoredValueRange<>(valueRange, 10))
				.withPropertyAccessor(bean -> bean.getText())
				.build();

		ScoringProfile<TestBean> testProfile=ScoringProfile.<TestBean>builder().
				addCategory(new WeightedScoringCategory<>(scoringCategory,10))
				.build();

		TestBean testBeanA=new TestBean("A",1);
		TestBean testBeanB=new TestBean("B",1);


		Assert.assertEquals(new Integer(100),testProfile.computeScore(testBeanA));
		Assert.assertEquals(new Integer(0),testProfile.computeScore(testBeanB));

	}
}
