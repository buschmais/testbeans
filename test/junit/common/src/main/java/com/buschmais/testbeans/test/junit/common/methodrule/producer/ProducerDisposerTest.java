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
package com.buschmais.testbeans.test.junit.common.methodrule.producer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.test.junit.common.delegate.producer.ProducerDisposerTestDelegate;
import com.buschmais.testbeans.test.junit.common.methodrule.AbstractMethodRuleTest;

/**
 * Test checking if activation and deactivation of a scope produces and disposes
 * beans correctly.
 * 
 * @author dirk.mahler
 */
public class ProducerDisposerTest extends AbstractMethodRuleTest {

	@BeforeClass
	public static void beforeClass() {
		TestContextManager.getInstance().activateClassContext(
				new ClassDescription(ProducerDisposerTest.class.getName()));
		ProducerDisposerTestDelegate.aroundClass();
	}

	@Test
	public void testProduceDispose() {
		ProducerDisposerTestDelegate.testProduceDispose();
	}

	@AfterClass
	public static void afterClass() {
		ProducerDisposerTestDelegate.aroundClass();
		TestContextManager.getInstance().deactivateClassContext(
				new ClassDescription(ProducerDisposerTest.class.getName()));
	}
}