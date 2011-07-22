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
package com.buschmais.testbeans.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.buschmais.testbeans.framework.container.CdiContainer;
import com.buschmais.testbeans.framework.container.CdiContainerAdapter;
import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.junit.event.SuiteDescription;
import com.buschmais.testbeans.junit.scope.SuiteScoped;

/**
 * Suite implementation which derives from JUnit's {@link Suite} and controls
 * the life cycle of the suite context, see {@link SuiteScoped}.
 * 
 * @author dirk.mahler
 */
public class TestBeansSuite extends Suite {

	/**
	 * The {@link CdiContainer} annotation as specified on the suite class.
	 */
	private CdiContainer container = null;

	/**
	 * Constructs the suite.
	 * 
	 * @param builder
	 *            The builder.
	 * @param klass
	 *            The class.
	 * @throws InitializationError
	 *             If initialization fails.
	 */
	public TestBeansSuite(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		super(klass, builder);
		container = klass.getAnnotation(CdiContainer.class);
	}

	/**
	 * Constructs the suite.
	 * 
	 * @param builder
	 *            The builder.
	 * @param classes
	 *            The classes.
	 * @throws InitializationError
	 *             If initialization fails.
	 */
	public TestBeansSuite(RunnerBuilder builder, Class<?>[] classes)
			throws InitializationError {
		super(builder, classes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(RunNotifier notifier) {
		TestContextManager testContextManager = TestContextManager
				.getInstance();
		CdiContainerAdapter containerAdapter = this.getContainerAdapter();
		SuiteDescription suiteDescription = new SuiteDescription();
		try {
			if (containerAdapter != null) {
				containerAdapter.start();
			}
			testContextManager.activate(SuiteScoped.class, suiteDescription);
			super.run(notifier);
		} finally {
			testContextManager.deactivate(SuiteScoped.class, suiteDescription);
			if (containerAdapter != null) {
				containerAdapter.stop();
			}
		}
	}

	/**
	 * Creates an instance of {@link CdiContainerAdapter} specified by the
	 * argument of the annotation {@link CdiContainer}.
	 * 
	 * @return The instance of the {@link CdiContainerAdapter} or
	 *         <code>null</code>.
	 */
	private CdiContainerAdapter getContainerAdapter() {
		CdiContainerAdapter containerManager = null;
		if (container != null) {
			Class<? extends CdiContainerAdapter> value = container.value();
			try {
				containerManager = value.newInstance();
			} catch (Exception e) {
				throw new IllegalStateException(
						"Cannot create CDI container instance for", e);
			}
		}
		return containerManager;
	}
}