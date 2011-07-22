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
package com.buschmais.testbeans.framework.context;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;

/**
 * Implementation of a {@link Extension} which registers the customs test
 * contexts managed by the {@link TestContextManager}.
 * 
 * @author dirk.mahler
 */
public class TestContextExtension implements Extension {

	/**
	 * Observes the {@link AfterBeanDiscovery} event of the CDI container.
	 * 
	 * @param event
	 *            The event.
	 * @param beanManager
	 *            The {@link BeanManager}.
	 */
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event,
			BeanManager beanManager) {
		TestContextManager testContextManager = TestContextManager
				.getInstance();
		for (TestContext testContext : testContextManager.getTestContexts()) {
			event.addContext(testContext);
		}
		testContextManager.start(beanManager);
	}

	/**
	 * Observes the {@link BeforeShutdown} event of the CDI container.
	 * 
	 * @param event
	 *            The event.
	 */
	public void beforeShutdown(@Observes BeforeShutdown event) {
		TestContextManager testContextManager = TestContextManager
				.getInstance();
		testContextManager.stop();
	}
}
