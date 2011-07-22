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
package com.buschmais.testbeans.test.junit.common.delegate.producer;

import javax.enterprise.util.AnnotationLiteral;

import org.junit.Assert;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.test.junit.common.bean.producer.Managed;
import com.buschmais.testbeans.test.junit.common.bean.producer.Resource;
import com.buschmais.testbeans.test.junit.common.bean.producer.ResourceProducer;

/**
 * Test checking if activation and deactivation of a scope produces and disposes
 * beans correctly.
 * 
 * @author dirk.mahler
 */
public final class ProducerDisposerTestDelegate {

	/**
	 * Private constructor.
	 */
	private ProducerDisposerTestDelegate() {

	}

	/**
	 * BeforeClass and AfterClass.
	 */
	public static void aroundClass() {
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertTrue(resourceProducer.getResources().isEmpty());
	}

	/**
	 * Test.
	 */
	@SuppressWarnings("serial")
	public static void testProduceDispose() {
		Resource resource = TestContextManager.getInstance().get(
				Resource.class, new AnnotationLiteral<Managed>() {
				});
		Assert.assertEquals(1, resource.getId());
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertEquals(1, resourceProducer.getResources().size());
	}

	/**
	 * Returns the {@link ResourceProducer} instance.
	 * 
	 * @return The {@link ResourceProducer} instance.
	 */
	private static ResourceProducer getResourceProducer() {
		return TestContextManager.getInstance().get(ResourceProducer.class);
	}
}
