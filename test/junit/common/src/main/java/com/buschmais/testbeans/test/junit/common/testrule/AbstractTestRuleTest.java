package com.buschmais.testbeans.test.junit.common.testrule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;

import com.buschmais.testbeans.junit.common.TestBeansRule;

public class AbstractTestRuleTest {

	@ClassRule
	@Rule
	public static TestRule testRule = new TestBeansRule();

}
