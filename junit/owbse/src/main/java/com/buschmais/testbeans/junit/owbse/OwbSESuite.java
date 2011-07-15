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
package com.buschmais.testbeans.junit.owbse;

import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.buschmais.testbeans.container.owbse.OwbSEContainer;
import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.framework.SuiteScoped;
import com.buschmais.testbeans.junit.common.AbstractSuite;

/**
 * A {@link Runner} which derives from {@link Suite} and controls the lifecycle
 * of the suite context for OpenWebBeans, see {@link SuiteScoped}.
 * <p>
 * This runner may be used if a suite is defined using {@link SuiteClasses}:
 * 
 * <pre>
 * &#064;SuiteClasses({MyTest1.class, MyTest2.class})
 * &#064;RunWith(OwbSESuite.class)
 * public class MySuiteTest() {
 * ...
 * }
 * </pre>
 * 
 * </p>
 * 
 * @author dirk.mahler
 */
public class OwbSESuite extends AbstractSuite {

	public OwbSESuite(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		super(klass, builder);
	}

	public OwbSESuite(RunnerBuilder builder, Class<?>[] classes)
			throws InitializationError {
		super(builder, classes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Container getContainer() {
		return OwbSEContainer.getInstance();
	}
}
