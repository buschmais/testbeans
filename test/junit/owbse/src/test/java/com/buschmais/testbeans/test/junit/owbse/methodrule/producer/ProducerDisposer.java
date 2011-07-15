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
package com.buschmais.testbeans.test.junit.owbse.methodrule.producer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.container.owbse.OwbSEContainer;
import com.buschmais.testbeans.test.junit.common.producer.ProducerDisposerTestDelegate;
import com.buschmais.testbeans.test.junit.owbse.methodrule.AbstractOwbSEMethodRuleTest;

/**
 * Test checking if activation and deactivation of a scope produces and disposes
 * beans correctly.
 * 
 * @author dirk.mahler
 */
public class ProducerDisposer extends AbstractOwbSEMethodRuleTest {

	private static ProducerDisposerTestDelegate delegate = new ProducerDisposerTestDelegate(
			OwbSEContainer.getInstance());

	@BeforeClass
	public static void beforeClass() {
		activateClassContext(ProducerDisposer.class);
		delegate.aroundClass();
	}

	@Test
	public void testProduceDispose() {
		delegate.testProduceDispose();
	}

	@AfterClass
	public static void afterClass() {
		delegate.aroundClass();
		deactivateClassContext(ProducerDisposer.class);
	}
}
