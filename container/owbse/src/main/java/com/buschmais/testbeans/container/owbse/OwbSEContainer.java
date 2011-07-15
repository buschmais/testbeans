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

import javax.enterprise.inject.spi.BeanManager;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.Container;

/**
 * Controls the life cycle of OpenWebBeans (http://openwebbeans.apache.org)
 * providing dependency injection services.
 * 
 * @author dirk.mahler
 */
public class OwbSEContainer extends Container {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(OwbSEContainer.class);

	/**
	 * The {@link OwbSEContainer} is a singleton instance.
	 */
	private static final OwbSEContainer INSTANCE = new OwbSEContainer();

	private ContainerLifecycle containerLifecycle = null;

	/**
	 * Static initializer.
	 * <p>
	 * The OWB container is started and a VM shutdown hook is registered for
	 * cleanup.
	 */
	static {
		LOGGER.debug("Starting OpenWebBeans.");
		INSTANCE.startContainer();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				LOGGER.debug("Stopping OpenWebBeans.");
				INSTANCE.stopContainer();
			}
		});
	}

	/**
	 * Private constructor.
	 */
	private OwbSEContainer() {
	}

	/**
	 * Returns the singleton instance of this container.
	 * 
	 * @return The singleton instance.
	 */
	public static OwbSEContainer getInstance() {
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanManager getBeanManager() {
		return containerLifecycle.getBeanManager();
	}

	/**
	 * Start the container.
	 */
	@Override
	public void startContainer() {
		INSTANCE.containerLifecycle = WebBeansContext.currentInstance()
				.getService(ContainerLifecycle.class);
		containerLifecycle.startApplication(null);
	}

	/**
	 * Stop the container.
	 */
	@Override
	public void stopContainer() {
		containerLifecycle.stopApplication(null);
	}
}
