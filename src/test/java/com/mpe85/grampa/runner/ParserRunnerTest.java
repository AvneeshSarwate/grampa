package com.mpe85.grampa.runner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mpe85.grampa.parser.Parser;
import com.mpe85.grampa.rule.impl.StringRule;

public class ParserRunnerTest {
	
	@Test
	public void test_run() {
		final Parser<Void> parser = () -> new StringRule<>("foo");
		final ParseRunner<Void> runner = new ParseRunner<>(parser);
		assertTrue(runner.run("foo").matched);
		assertTrue(runner.run("foobar").matched);
		assertFalse(runner.run("bar").matched);
	}
	
}
