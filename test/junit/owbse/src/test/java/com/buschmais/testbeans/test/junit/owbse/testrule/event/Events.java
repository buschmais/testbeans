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
package com.buschmais.testbeans.test.junit.owbse.testrule.event;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.container.owbse.OwbSEContainer;
import com.buschmais.testbeans.test.junit.common.event.EventTestDelegate;
import com.buschmais.testbeans.test.junit.owbse.testrule.AbstractOwbSETest;

/**
 * Test implementation checking the events fired by the {@link OwbSERule}.
 * <p>
 * Note: the event fired after a test class has been finished is currently not
 * tested.
 * </p>
 * 
 * @author dirk.mahler
 */
public class Events extends AbstractOwbSETest {

	private static EventTestDelegate delegate = new EventTestDelegate(
			OwbSEContainer.getInstance());

	@BeforeClass
	public static void beforeClass() {
		delegate.beforeClass(Events.class.getName());
	}

	@Before
	public void before() {
		delegate.before();
	}

	@Test
	public void test() {
		delegate.test();
	}

	@After
	public void after() {
		delegate.after();
	}

	@AfterClass
	public static void afterClass() {
		delegate.afterClass();
	}

}