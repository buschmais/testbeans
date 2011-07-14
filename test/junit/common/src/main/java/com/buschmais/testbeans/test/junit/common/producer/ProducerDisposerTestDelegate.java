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
package com.buschmais.testbeans.test.junit.common.producer;

import javax.enterprise.util.AnnotationLiteral;

import org.junit.Assert;

import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.test.common.bean.producer.Managed;
import com.buschmais.testbeans.test.common.bean.producer.Resource;
import com.buschmais.testbeans.test.common.bean.producer.ResourceProducer;

/**
 * Test checking if activation and deactivation of a scope produces and disposes
 * beans correctly.
 * 
 * @author dirk.mahler
 */
public class ProducerDisposerTestDelegate {

	private Container container;

	public ProducerDisposerTestDelegate(Container container) {
		this.container = container;
	}

	public void aroundClass() {
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertTrue(resourceProducer.getResources().isEmpty());
	}

	@SuppressWarnings("serial")
	public void testProduceDispose() {		
		Resource resource = container.get(Resource.class, new AnnotationLiteral<Managed>() {
		});
		Assert.assertEquals(1, resource.getId());
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertEquals(1, resourceProducer.getResources().size());
	}

	private ResourceProducer getResourceProducer() {
		return container.get(ResourceProducer.class);
	}
}
