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
package com.buschmais.testbeans.container.weldse;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.container.CdiContainerAdapter;

/**
 * Controls the life cycle of Weld (http://seamframework.org/Weld) providing
 * dependency injection services.
 * 
 * @author dirk.mahler
 */
public class WeldSE implements CdiContainerAdapter {

	/**
	 * The {@link Logger} instance.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WeldSE.class);
	/**
	 * The {@link Weld} and {@link WeldContainer} instances managed by this
	 * container.
	 */
	private Weld weld = null;

	/**
	 * The {@link WeldContainer} instance.
	 */
	private WeldContainer weldContainer = null;

	/**
	 * Returns the instance of the {@link WeldContainer}.
	 * 
	 * @return The {@link WeldContainer}.
	 */
	public WeldContainer getWeldContainer() {
		return weldContainer;
	}

	/**
	 * Start the container.
	 */
	@Override
	public void start() {
		LOGGER.debug("Starting Weld.");
		weld = new Weld();
		weldContainer = weld.initialize();
	}

	/**
	 * Stop the container.
	 */
	@Override
	public void stop() {
		if (weld != null) {
			LOGGER.debug("Stopping Weld.");
			weld.shutdown();
			weld = null;
			weldContainer = null;
		}
	}
}
