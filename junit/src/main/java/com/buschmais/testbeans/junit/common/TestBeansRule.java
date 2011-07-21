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
package com.buschmais.testbeans.junit.common;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.buschmais.testbeans.framework.ClassScoped;
import com.buschmais.testbeans.framework.MethodScoped;
import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.framework.description.MethodDescription;

/**
 * Abstract implementation of a JUnit {@link TestRule} which controls the life
 * cycle of JUnit-related contexts, see {@link ClassScoped} and
 * {@link MethodScoped}.
 * 
 * @author dirk.mahler
 */
public class TestBeansRule implements TestRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before(description);
				try {
					base.evaluate();
				} finally {
					after(description);
				}
			}
		};
	}

	/**
	 * This method is called before a test class or test method is executed.
	 * 
	 * @param description
	 *            The test description provided by JUnit.
	 */
	private void before(Description description) {
		if (description.isTest()) {
			TestContextManager.getInstance().activate(MethodScoped.class,
					new MethodDescription(description.getMethodName()));
		} else {
			TestContextManager.getInstance().activate(ClassScoped.class,
					new ClassDescription(description.getClassName()));
		}
	}

	/**
	 * This method is called after a test class or test method was executed.
	 * 
	 * @param description
	 *            The test description provided by JUnit.
	 */
	private void after(Description description) {
		if (description.isTest()) {
			TestContextManager.getInstance().deactivate(MethodScoped.class,
					new MethodDescription(description.getMethodName()));
		} else {
			TestContextManager.getInstance().deactivate(ClassScoped.class,
					new ClassDescription(description.getClassName()));
		}
	}
}
