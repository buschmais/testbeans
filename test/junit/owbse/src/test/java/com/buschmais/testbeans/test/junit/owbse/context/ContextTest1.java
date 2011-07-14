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
package com.buschmais.testbeans.test.junit.owbse.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.container.owbse.OpenWebBeansSEContainer;
import com.buschmais.testbeans.test.junit.common.context.ContextTest1Delegate;

/**
 * Concrete implementation of a test checking correct scoping of beans.
 * 
 * @author dirk.mahler
 */
public class ContextTest1 extends AbstractContextTest {

	private static ContextTest1Delegate delegate = new ContextTest1Delegate(
			OpenWebBeansSEContainer.getInstance());

	@BeforeClass
	public static void beforeConcreteClass() {
		delegate.beforeConcreteClass();
	}

	@Before
	public void beforeConcrete() {
		delegate.beforeConcrete();
	}

	@Test
	public void testConcrete() {
		delegate.testConcrete();
	}

	@After
	public void afterConcrete() {
		delegate.afterConcrete();
	}

	@AfterClass
	public static void afterConcreteClass() {
		delegate.afterConcreteClass();
	}

}
