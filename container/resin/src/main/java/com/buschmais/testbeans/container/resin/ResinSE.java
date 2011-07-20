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
package com.buschmais.testbeans.container.resin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.container.CdiContainerAdapter;
import com.caucho.resin.ResinBeanContainer;

/**
 * Controls the life cycle of Resin (http://www.caucho.com) providing dependency
 * injection services.
 * 
 * @author dirk.mahler
 */
public class ResinSE implements CdiContainerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResinSE.class);

	/**
	 * The Resin {@link ResinBeanContainer}.
	 */
	private ResinBeanContainer container;

	/**
	 * Start the container.
	 */
	@Override
	public void start() {
		LOGGER.debug("Starting Resin.");
		container = new ResinBeanContainer();
		container.start();
	}

	/**
	 * Stop the container.
	 */
	@Override
	public void stop() {
		if (container != null) {
			LOGGER.debug("Stopping Resin.");
			container.close();
		}
	}
}
