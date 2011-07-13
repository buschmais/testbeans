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
package com.buschmais.testbeans.junit.test.event;

import java.util.Queue;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Description;

import com.buschmais.testbeans.junit.extension.WeldRule;
import com.buschmais.testbeans.junit.test.AbstractCdiTest;
import com.buschmais.testbeans.junit.test.bean.event.Observer;

/**
 * Test implementation checking the events fired by the {@link WeldRule}.
 * <p>
 * Note: the event fired after a test class has been finished is currently not
 * tested.
 * </p>
 * 
 * @author dirk.mahler
 */
public class EventTest extends AbstractCdiTest {

	@BeforeClass
	public static void beforeClass() {
		Description description = getBeforeEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(description.isSuite());
		Assert.assertEquals(EventTest.class, description.getTestClass());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	@Before
	public void before() {
		Description description = getBeforeEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(description.isTest());
		Assert.assertEquals("test", description.getMethodName());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	@Test
	public void test() {
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	@After
	public void after() {
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	@AfterClass
	public static void afterClass() {
		Description description = getAfterEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(description.isTest());
		Assert.assertEquals("test", description.getMethodName());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	private static Observer getObserver() {
		Observer observer = getWeldContainer().instance()
				.select(Observer.class).get();
		return observer;
	}

	private static Queue<Description> getBeforeEvents() {
		return getObserver().getBeforeEvents();
	}

	private static Queue<Description> getAfterEvents() {
		return getObserver().getAfterEvents();
	}
}
