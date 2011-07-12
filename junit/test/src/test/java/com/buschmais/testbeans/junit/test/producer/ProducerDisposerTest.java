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
		WeldContainer weldContainer = weldRule.getWeldContainer();
		Resource resource = weldContainer.instance()
				.select(Resource.class, new AnnotationLiteral<Managed>() {
				}).get();
		Assert.assertEquals(1, resource.getId());
		ResourceProducer resourceProducer = getResourceProducer();
		Assert.assertEquals(1, resourceProducer.getResources().size());
	}

	private static ResourceProducer getResourceProducer() {
		ResourceProducer resourceProducer = weldRule.getWeldContainer()
				.instance().select(ResourceProducer.class).get();
		return resourceProducer;
	}
}
