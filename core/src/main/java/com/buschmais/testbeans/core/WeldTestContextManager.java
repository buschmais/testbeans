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
package com.buschmais.testbeans.core;

import javax.enterprise.inject.spi.BeanManager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controls the life cycle of Weld (http://seamframework.org/Weld) providing
 * dependency injection services.
 * <p>
 * Methods are provided to access the various contexts and the weld container.
 * </p>
 * 
 * @author dirk.mahler
 */
public class WeldTestContextManager extends TestContextManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeldTestContextManager.class);

	/**
	 * The Weld rule is singleton instance.
	 */
	private static final WeldTestContextManager INSTANCE = new WeldTestContextManager();

	/**
	 * The {@link Weld} and {@link WeldContainer} instances managed by this
	 * rule.
	 */
	private Weld weld = null;
	private WeldContainer weldContainer = null;

	/**
	 * Static initializer.
	 * <p>
	 * The Weld container is started and a VM shutdown hook is registered for
	 * cleanup.
	 */
	static {
		LOGGER.debug("Starting Weld.");
		INSTANCE.startContainer();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				LOGGER.debug("Stopping Weld.");
				INSTANCE.stopContainer();
			}
		});
	}

	/**
	 * Private constructor.
	 */
	private WeldTestContextManager() {
	}

	/**
	 * Returns the singleton instance of this rule.
	 * 
	 * @return The singleton instance.
	 */
	public static WeldTestContextManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the instance of the {@link WeldContainer}.
	 * 
	 * @return The {@link WeldContainer}.
	 */
	public WeldContainer getWeldContainer() {
		return weldContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BeanManager getBeanManager() {
		return weldContainer.getBeanManager();
	}

	/**
	 * Start the container.
	 */
	@Override
	public void startContainer() {
		weld = new Weld();
		weldContainer = weld.initialize();
	}

	/**
	 * Stop the container.
	 */
	@Override
	public void stopContainer() {
		if (weld != null) {
			weld.shutdown();
			weld = null;
			weldContainer = null;
		}
	}
}
