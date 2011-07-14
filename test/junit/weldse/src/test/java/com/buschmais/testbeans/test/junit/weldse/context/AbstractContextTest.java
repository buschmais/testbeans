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
package com.buschmais.testbeans.test.junit.weldse.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.container.weldse.WeldSEContainer;
import com.buschmais.testbeans.test.junit.common.context.AbstractContextTestDelegate;
import com.buschmais.testbeans.test.junit.weldse.AbstractWeldSETest;

/**
 * Abstract base class for tests checking correct scoping of the beans.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractContextTest extends AbstractWeldSETest {

	private static AbstractContextTestDelegate delegate = new AbstractContextTestDelegate(
			WeldSEContainer.getInstance());

	@BeforeClass
	public static void beforeAbstractClass() {
		delegate.beforeAbstractClass();
	}

	@Before
	public void beforeAbstract() {
		delegate.beforeAbstract();
	}

	@Test
	public void testAbstract() {
		delegate.testAbstract();
	}

	@After
	public void afterAbstract() {
		delegate.afterAbstract();
	}

	@AfterClass
	public static void afterAbstractClass() {
		delegate.afterAbstractClass();
	}

}
