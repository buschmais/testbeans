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
package com.buschmais.testbeans.junit.test.producer;

import javax.enterprise.util.AnnotationLiteral;

import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.junit.test.AbstractCdiTest;
import com.buschmais.testbeans.junit.test.bean.producer.Managed;
import com.buschmais.testbeans.junit.test.bean.producer.Resource;
import com.buschmais.testbeans.junit.test.bean.producer.ResourceProducer;

/**
 * Test checking if activation and deactivation of a scope produces and disposes
 * beans correctly.
 * 
 * @author dirk.mahler
 */
public class ProducerDisposerTest extends AbstractCdiTest {

	@BeforeClass
	@AfterClass
	public static void beforeClass() {
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertTrue(resourceProducer.getResources().isEmpty());
	}

	@Test
	@SuppressWarnings("serial")
	public void testProduceDispose() {
		WeldContainer weldContainer = getWeldContainer();
		Resource resource = weldContainer.instance()
				.select(Resource.class, new AnnotationLiteral<Managed>() {
				}).get();
		Assert.assertEquals(1, resource.getId());
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertEquals(1, resourceProducer.getResources().size());
	}

	private static ResourceProducer getResourceProducer() {
		ResourceProducer resourceProducer = getWeldContainer().instance()
				.select(ResourceProducer.class).get();
		return resourceProducer;
	}
}
