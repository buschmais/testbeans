/*
 * Copyright 2011 buschmais GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.buschmais.testbeans.test.junit.common.methodrule;

import org.junit.Rule;
import org.junit.rules.MethodRule;

import com.buschmais.testbeans.junit.TestBeansMethodRule;

/**
 * Abstract class for all tests using the {@link TestBeansMethodRule}.
 * 
 * @author dirk.mahler
 * 
 */
@SuppressWarnings("deprecation")
public class AbstractMethodRuleTest {

	/**
	 * The rule.
	 */
	@Rule
	public MethodRule testRule = new TestBeansMethodRule(); // CS-IGNORE:VisibilityModifierCheck

}
