package io.github.lumue.scored.beans;

/**
 * Created by lm on 03.11.15.
 */
public class TestBean {

	private final String text;
	private final Integer number;

	public TestBean(String text, Integer number) {
		this.text = text;
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public Integer getNumber() {
		return number;
	}
}
