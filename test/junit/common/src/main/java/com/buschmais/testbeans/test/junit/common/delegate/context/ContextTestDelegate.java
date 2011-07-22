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
package com.buschmais.testbeans.test.junit.common.delegate.context;

import javax.enterprise.context.ContextNotActiveException;

import org.junit.Assert;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.test.common.bean.AbstractScopedBean;
import com.buschmais.testbeans.test.junit.common.bean.context.ClassScopedBean;
import com.buschmais.testbeans.test.junit.common.bean.context.MethodScopedBean;
import com.buschmais.testbeans.test.junit.common.bean.context.SuiteScopedBean;

/**
 * Delegate class for tests checking correct scoping of the beans.
 * 
 * @author dirk.mahler
 */
public abstract class ContextTestDelegate {

	/**
	 * BeforeClass.
	 */
	public static void beforeAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "default");
		setPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	/**
	 * Before.
	 */
	public static void beforeAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "default");
		setPayload(MethodScopedBean.class, "test");
	}

	/**
	 * Test.
	 */
	public static void testAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	/**
	 * After.
	 */
	public static void afterAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	/**
	 * AfterClass.
	 */
	public static void afterAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	/**
	 * Returns the {@link AbstractScopedBean} instance for the given class.
	 * 
	 * @param beanClass
	 *            The class.
	 * @return The instance.
	 */
	private static AbstractScopedBean getBean(Class<?> beanClass) {
		return (AbstractScopedBean) TestContextManager.getInstance().get(
				beanClass);
	}

	/**
	 * Sets the payload of a bean.
	 * 
	 * @param beanClass
	 *            The bean class.
	 * @param payload
	 *            The payload.
	 */
	private static void setPayload(Class<?> beanClass, String payload) {
		getBean(beanClass).setPayload(payload);
	}

	/**
	 * Checks the payload.
	 * 
	 * @param expectedBeanClass
	 *            The bean class.
	 * @param expectedPayload
	 *            The expected payload.
	 */
	protected static void checkPayload(Class<?> expectedBeanClass,
			String expectedPayload) {
		try {
			Assert.assertEquals(expectedPayload, getBean(expectedBeanClass)
					.getPayload());
		} catch (ContextNotActiveException e) {
			Assert.fail("expected bean " + expectedBeanClass);
		}
	}

	/**
	 * Checks if a bean cannot be accessed because context it is bound to is not
	 * activated.
	 * 
	 * @param unexpectedBeanClass
	 *            The bean class.
	 */
	protected static void checkUnexpectedBean(Class<?> unexpectedBeanClass) {
		try {
			getBean(unexpectedBeanClass).getPayload();
			Assert.fail("unexpected bean " + unexpectedBeanClass);
		} catch (ContextNotActiveException e) {
			return;
		}
	}
}
