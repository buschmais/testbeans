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
package com.buschmais.testbeans.junit.extension;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.junit.extension.context.ClassContext;
import com.buschmais.testbeans.junit.extension.context.MethodContext;
import com.buschmais.testbeans.junit.extension.context.SuiteContext;

/**
 * Controls the life cycle of Weld (http://seamframework.org/Weld) providing
 * dependency injection services.
 * <p>
 * Methods are provided to access the various contexts and the weld container.
 * </p>
 * 
 * @author dirk.mahler
 */
public class WeldManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeldManager.class);

	/**
	 * The Weld rule is singleton instance.
	 */
	private static final WeldManager INSTANCE = new WeldManager();

	/**
	 * The suite context.
	 */
	private SuiteContext suiteContext = new SuiteContext();

	/**
	 * The class context.
	 */
	private ClassContext classContext = new ClassContext();

	/**
	 * The method scope.
	 */
	private MethodContext methodContext = new MethodContext();

	/**
	 * The {@link Weld} and {@link WeldContainer} instances managed by this
	 * rule.
	 */
	private Weld weld = null;
	private WeldContainer weldContainer = null;

	/**
	 * Static initializer.
	 * <p>
	 * The Weld container is started, the suite context initialized and a VM
	 * shutdown hook is registered for cleanup.
	 */
	static {
		LOGGER.debug("Starting Weld.");
		INSTANCE.weld = new Weld();
		INSTANCE.weldContainer = INSTANCE.weld.initialize();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				LOGGER.debug("Stopping Weld.");
				INSTANCE.weld.shutdown();
			}
		});
	}

	/**
	 * Private constructor.
	 */
	private WeldManager() {
	}

	/**
	 * Returns the singleton instance of this rule.
	 * 
	 * @return The singleton instance.
	 */
	public static WeldManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the {@link SuiteContext}.
	 * 
	 * @return The {@link SuiteContext}.
	 */
	public SuiteContext getSuiteContext() {
		return suiteContext;
	}

	/**
	 * Returns the {@link ClassContext}.
	 * 
	 * @return The {@link ClassContext}.
	 */
	public ClassContext getClassContext() {
		return classContext;
	}

	/**
	 * Returns the {@link MethodContext}.
	 * 
	 * @return The {@link MethodContext}.
	 */
	public MethodContext getMethodContext() {
		return methodContext;
	}

	/**
	 * Returns the instance of the {@link WeldContainer}.
	 * 
	 * @return The {@link WeldContainer}.
	 */
	public WeldContainer getWeldContainer() {
		return weldContainer;
	}

}
