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
package com.buschmais.testbeans.test.junit.common.testrule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;

import com.buschmais.testbeans.junit.TestBeansRule;

/**
 * Abstract class for all tests using the {@link TestBeansRule}.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractTestRuleTest { // CS-IGNORE:HideUtilityClassConstructor

	/**
	 * The rule.
	 */
	@ClassRule
	@Rule
	public static TestRule testRule = new TestBeansRule(); // CS-IGNORE:VisibilityModifierCheck

}
