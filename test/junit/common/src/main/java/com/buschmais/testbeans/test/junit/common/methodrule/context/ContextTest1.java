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

import com.buschmais.testbeans.test.junit.common.delegate.context.ContextTest1Delegate;

/**
 * Concrete implementation of a test checking correct scoping of beans.
 * 
 * @author dirk.mahler
 */
public class ContextTest1 extends ContextTest {

	/**
	 * BeforeClass.
	 */
	@BeforeClass
	public static void beforeConcreteClass() {
		ContextTest1Delegate.beforeConcreteClass();
	}

	/**
	 * Before.
	 */
	@Before
	public void beforeConcrete() {
		ContextTest1Delegate.beforeConcrete();
	}

	/**
	 * Test.
	 */
	@Test
	public void testConcrete() {
		ContextTest1Delegate.testConcrete();
	}

	/**
	 * After.
	 */
	@After
	public void afterConcrete() {
		ContextTest1Delegate.afterConcrete();
	}

	/**
	 * AfterClass.
	 */
	@AfterClass
	public static void afterConcreteClass() {
		ContextTest1Delegate.afterConcreteClass();
	}

}
