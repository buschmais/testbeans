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
package com.buschmais.testbeans.test.junit.common.testrule.event;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.junit.TestBeansRule;
import com.buschmais.testbeans.test.junit.common.delegate.event.EventTestDelegate;
import com.buschmais.testbeans.test.junit.common.testrule.AbstractTestRuleTest;

/**
 * Test implementation checking the events fired by the {@link TestBeansRule}.
 * <p>
 * Note: the event fired after a test class has been finished is currently not
 * tested.
 * </p>
 * 
 * @author dirk.mahler
 */
public class EventsTest extends AbstractTestRuleTest {

	@BeforeClass
	public static void beforeClass() {
		EventTestDelegate.beforeClass(EventsTest.class.getName());
	}

	@Before
	public void before() {
		EventTestDelegate.before();
	}

	@Test
	public void test() {
		EventTestDelegate.test();
	}

	@After
	public void after() {
		EventTestDelegate.after();
	}

	@AfterClass
	public static void afterClass() {
		EventTestDelegate.afterClass();
	}
}
