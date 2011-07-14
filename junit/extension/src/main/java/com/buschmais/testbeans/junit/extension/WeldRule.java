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
package com.buschmais.testbeans.junit.extension;

import javax.enterprise.util.AnnotationLiteral;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.buschmais.testbeans.core.After;
import com.buschmais.testbeans.core.Before;
import com.buschmais.testbeans.core.ClassScoped;
import com.buschmais.testbeans.core.MethodScoped;
import com.buschmais.testbeans.core.WeldManager;

/**
 * Implementation of a JUnit {@link TestRule} which controls the life cycle of
 * JUnit-related contexts, see {@link ClassScoped} and {@link MethodScoped}.
 * 
 * Usage:
 * 
 * <pre>
 * &#064;Rule
 * &#064;ClassRule
 * public static WeldRule weldRule = new WeldRule();
 * </pre>
 * 
 * @author dirk.mahler
 */
public class WeldRule implements TestRule {

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
	@SuppressWarnings("serial")
	private void before(Description description) {
		WeldManager weldManager = WeldManager.getInstance();
		if (description.isTest()) {
			weldManager.getMethodContext().activate();
		} else {
			weldManager.getClassContext().activate();
		}
		weldManager.getWeldContainer().event()
				.select(Description.class, new AnnotationLiteral<Before>() {
				}).fire(description);
	}

	/**
	 * This method is called after a test class or test method was executed.
	 * 
	 * @param description
	 *            The test description provided by JUnit.
	 */
	@SuppressWarnings("serial")
	private void after(Description description) {
		WeldManager weldManager = WeldManager.getInstance();
		weldManager.getWeldContainer().event()
				.select(Description.class, new AnnotationLiteral<After>() {
				}).fire(description);
		if (description.isTest()) {
			weldManager.getMethodContext().deactivate();
		} else {
			weldManager.getClassContext().deactivate();
		}
	}
}
