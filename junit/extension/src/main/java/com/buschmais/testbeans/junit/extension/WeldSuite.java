package com.buschmais.testbeans.junit.extension;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * A {@link Runner} which derives from {@link Suite} and controls the lifecycle
 * of the suite context.
 * <p>
 * If a suite is defined using {@link SuiteClasses} this runner may be used:
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
public class WeldSuite extends Suite {

	public WeldSuite(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		super(klass, builder);
	}

	public WeldSuite(RunnerBuilder builder, Class<?>[] classes)
			throws InitializationError {
		super(builder, classes);
	}

	@Override
	public void run(RunNotifier notifier) {
		WeldManager weldManager = WeldManager.getInstance();
		try {
			weldManager.getSuiteContext().activate();
			super.run(notifier);
		} finally {
			weldManager.getSuiteContext().deactivate();
		}
	}

}
