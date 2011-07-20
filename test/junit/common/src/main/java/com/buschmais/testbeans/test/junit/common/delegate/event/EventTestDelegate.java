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
package com.buschmais.testbeans.test.junit.common.delegate.event;

import java.util.Queue;

import junit.framework.Assert;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.framework.description.Description;
import com.buschmais.testbeans.framework.description.MethodDescription;
import com.buschmais.testbeans.framework.description.SuiteDescription;
import com.buschmais.testbeans.test.common.bean.event.Observer;

/**
 * Test implementation checking the events.
 * <p>
 * Note: the event fired after a test class has been finished is currently not
 * tested.
 * </p>
 * 
 * @author dirk.mahler
 */
public class EventTestDelegate {

	public static void beforeClass(String className) {
		Description description = getBeforeEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(SuiteDescription.class.isAssignableFrom(description
				.getClass()));
		description = getBeforeEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(ClassDescription.class.isAssignableFrom(description
				.getClass()));
		Assert.assertEquals(className,
				((ClassDescription) description).getClassName());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	public static void before() {
		Description description = getBeforeEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(MethodDescription.class.isAssignableFrom(description
				.getClass()));
		Assert.assertEquals("test",
				((MethodDescription) description).getMethodName());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	public static void test() {
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	public static void after() {
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	public static void afterClass() {
		Description description = getAfterEvents().poll();
		Assert.assertNotNull(description);
		Assert.assertTrue(MethodDescription.class.isAssignableFrom(description
				.getClass()));
		Assert.assertEquals("test",
				((MethodDescription) description).getMethodName());
		Assert.assertNull(getBeforeEvents().peek());
		Assert.assertNull(getAfterEvents().peek());
	}

	private static Queue<Description> getBeforeEvents() {
		Observer observer = TestContextManager.getInstance()
				.get(Observer.class);
		return observer.getBeforeEvents();
	}

	private static Queue<Description> getAfterEvents() {
		Observer observer = TestContextManager.getInstance()
				.get(Observer.class);
		return observer.getAfterEvents();
	}
}
