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

import com.buschmais.testbeans.test.junit.common.bean.context.ClassScopedBean;
import com.buschmais.testbeans.test.junit.common.bean.context.MethodScopedBean;
import com.buschmais.testbeans.test.junit.common.bean.context.SuiteScopedBean;

/**
 * Delegate implementation of a test checking correct scoping of beans.
 * 
 * @author dirk.mahler
 */
public final class ContextTest1Delegate extends ContextTestDelegate {

	/**
	 * BeforeClass.
	 */
	public static void beforeConcreteClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	/**
	 * Before.
	 */
	public static void beforeConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	/**
	 * Test.
	 */
	public static void testConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	/**
	 * After.
	 */
	public static void afterConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	/**
	 * AfterClass.
	 */
	public static void afterConcreteClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

}
