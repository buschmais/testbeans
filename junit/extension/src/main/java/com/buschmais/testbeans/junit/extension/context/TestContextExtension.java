package com.buschmais.testbeans.junit.extension.context;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

import com.buschmais.testbeans.junit.extension.WeldRule;

/**
 * Implementation of {@link Exception} as defined by JSR-299 which registers the
 * customs contexts controlled by the {@link WeldRule}.
 * 
 * @author dirk.mahler
 */
public class TestContextExtension implements Extension {

	/**
	 * {@inheritDoc}
	 */
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event,
			BeanManager manager) {
		WeldRule cdiRule = WeldRule.getInstance();
		event.addContext(cdiRule.getMethodContext());
		event.addContext(cdiRule.getClassContext());
		event.addContext(cdiRule.getSuiteContext());
	}
}
