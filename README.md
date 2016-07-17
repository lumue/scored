#scored 

[![Build Status](https://travis-ci.org/lumue/scored.svg?branch=master)](https://travis-ci.org/lumue/scored)

a small scoring library for java.

##purpose

used to calculate a score for a java bean and a scoring profile. a scoring profile consists of a number of weighted scoring categories.
see also [this german wikipedia article on scoring or "Nutzwertanalyse"](https://de.wikipedia.org/wiki/Nutzwertanalyse).

###building

the project is hosted on github, and can be built using gradle:

    git clone git@github.com:lumue/scored.git
    gradle build

###usage

example usage:


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
