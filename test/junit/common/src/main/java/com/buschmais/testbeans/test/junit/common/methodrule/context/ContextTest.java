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
package com.buschmais.testbeans.test.junit.common.methodrule.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.framework.ClassScoped;
import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.test.junit.common.delegate.context.ContextTestDelegate;
import com.buschmais.testbeans.test.junit.common.methodrule.AbstractMethodRuleTest;

/**
 * Abstract base class for tests checking correct scoping of the beans.
 * 
 * @author dirk.mahler
 */
public class ContextTest extends AbstractMethodRuleTest {

	@BeforeClass
	public static void beforeAbstractClass() {
		TestContextManager.getInstance().activate(ClassScoped.class,
				new ClassDescription(ContextTest.class.getName()));
		ContextTestDelegate.beforeAbstractClass();
	}

	@Before
	public void beforeAbstract() {
		ContextTestDelegate.beforeAbstract();
	}

	@Test
	public void testAbstract() {
		ContextTestDelegate.testAbstract();
	}

	@After
	public void afterAbstract() {
		ContextTestDelegate.afterAbstract();
	}

	@AfterClass
	public static void afterAbstractClass() {
		ContextTestDelegate.afterAbstractClass();
		TestContextManager.getInstance().deactivate(ClassScoped.class,
				new ClassDescription(ContextTest.class.getName()));
	}

}
