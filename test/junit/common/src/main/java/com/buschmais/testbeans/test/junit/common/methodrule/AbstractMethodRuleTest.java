package com.buschmais.testbeans.test.junit.common.methodrule;

import org.junit.Rule;
import org.junit.rules.MethodRule;

import com.buschmais.testbeans.junit.common.TestBeansMethodRule;

@SuppressWarnings("deprecation")
public class AbstractMethodRuleTest {

	@Rule
	public MethodRule testRule = new TestBeansMethodRule();

}
