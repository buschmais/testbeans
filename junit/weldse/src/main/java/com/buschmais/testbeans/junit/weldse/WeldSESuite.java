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
package com.buschmais.testbeans.junit.weldse;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.buschmais.testbeans.framework.SuiteScoped;
import com.buschmais.testbeans.weldse.WeldSEContainer;

/**
 * A {@link Runner} which derives from {@link Suite} and controls the lifecycle
 * of the suite context, see {@link SuiteScoped}.
 * <p>
 * This runner may be used if a suite is defined using {@link SuiteClasses}:
 * 
 * <pre>
 * &#064;SuiteClasses({MyTest1.class, MyTest2.class})
 * &#064;RunWith(WeldSuite.class)
 * public class MySuiteTest() {
 * ...
 * }
 * </pre>
 * 
 * </p>
 * 
 * @author dirk.mahler
 */
public class WeldSESuite extends Suite {

	public WeldSESuite(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		super(klass, builder);
	}

	public WeldSESuite(RunnerBuilder builder, Class<?>[] classes)
			throws InitializationError {
		super(builder, classes);
	}

	@Override
	public void run(RunNotifier notifier) {
		WeldSEContainer weldManager = WeldSEContainer
				.getInstance();
		try {
			weldManager.getSuiteContext().activate();
			super.run(notifier);
		} finally {
			weldManager.getSuiteContext().deactivate();
		}
	}

}
