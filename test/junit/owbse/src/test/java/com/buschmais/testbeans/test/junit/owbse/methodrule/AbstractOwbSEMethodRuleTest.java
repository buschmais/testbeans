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
package com.buschmais.testbeans.test.junit.owbse.methodrule;

import org.junit.Rule;
import org.junit.rules.MethodRule;

import com.buschmais.testbeans.container.owbse.OwbSEContainer;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.junit.owbse.OwbSEMethodRule;

/**
 * Abstract superclass for tests using {@link OwbSEMethodRule}.
 * 
 * @author dirk.mahler
 */
@SuppressWarnings("deprecation")
public abstract class AbstractOwbSEMethodRuleTest {

	@Rule
	public static MethodRule owbRule = new OwbSEMethodRule();

	protected static void activateClassContext(
			Class<? extends AbstractOwbSEMethodRuleTest> testclass) {
		OwbSEContainer.getInstance().activateClassContext(
				new ClassDescription(testclass.getName()));
	}

	protected static void deactivateClassContext(
			Class<? extends AbstractOwbSEMethodRuleTest> testclass) {
		OwbSEContainer.getInstance().deactivateClassContext(
				new ClassDescription(testclass.getName()));
	}

}
