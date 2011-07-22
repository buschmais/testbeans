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
package com.buschmais.testbeans.container.owbse;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.container.CdiContainerAdapter;

/**
 * Controls the life cycle of OpenWebBeans (http://openwebbeans.apache.org)
 * providing dependency injection services.
 * 
 * @author dirk.mahler
 */
public class OpenWebBeansSE implements CdiContainerAdapter {

	/**
	 * The {@link Logger} instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OpenWebBeansSE.class);

	/**
	 * The Apache OpenWebBeans container life cycle.
	 */
	private ContainerLifecycle containerLifecycle = null;

	/**
	 * Start the container.
	 */
	@Override
	public void start() {
		LOGGER.debug("Starting OpenWebBeans.");
		containerLifecycle = WebBeansContext.currentInstance().getService(
				ContainerLifecycle.class);
		containerLifecycle.startApplication(null);
	}

	/**
	 * Stop the container.
	 */
	@Override
	public void stop() {
		LOGGER.debug("Stopping OpenWebBeans.");
		containerLifecycle.stopApplication(null);
	}
}
