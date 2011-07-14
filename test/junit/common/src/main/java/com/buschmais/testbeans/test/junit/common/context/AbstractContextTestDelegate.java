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
package com.buschmais.testbeans.test.junit.common.context;

import javax.enterprise.context.ContextNotActiveException;

import org.junit.Assert;

import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.test.common.bean.context.AbstractScopedBean;
import com.buschmais.testbeans.test.common.bean.context.ClassScopedBean;
import com.buschmais.testbeans.test.common.bean.context.MethodScopedBean;
import com.buschmais.testbeans.test.common.bean.context.SuiteScopedBean;

/**
 * Abstract base class for tests checking correct scoping of the beans.
 * 
 * @author dirk.mahler
 */
public class AbstractContextTestDelegate {

	protected Container container;

	public AbstractContextTestDelegate(Container container) {
		this.container = container;
	}

	public void beforeAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "default");
		setPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	public void beforeAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "default");
		setPayload(MethodScopedBean.class, "test");
	}

	public void testAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	public void afterAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	public void afterAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	private AbstractScopedBean getBean(Class<?> beanClass) {
		return (AbstractScopedBean) container.get(beanClass);
	}

	private void setPayload(Class<?> beanClass, String payload) {
		getBean(beanClass).setPayload(payload);
	}

	protected void checkPayload(Class<?> expectedBeanClass,
			String expectedPayload) {
		try {
			Assert.assertEquals(expectedPayload, getBean(expectedBeanClass)
					.getPayload());
		} catch (ContextNotActiveException e) {
			Assert.fail("expected bean " + expectedBeanClass);
		}
	}

	protected void checkUnexpectedBean(Class<?> unexpectedBeanClass) {
		try {
			getBean(unexpectedBeanClass).getPayload();
			Assert.fail("unexpected bean " + unexpectedBeanClass);
		} catch (ContextNotActiveException e) {
		}
	}
}
