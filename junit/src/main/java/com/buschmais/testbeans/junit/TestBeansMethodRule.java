package com.buschmais.testbeans.junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.junit.event.MethodDescription;
import com.buschmais.testbeans.junit.scope.MethodScoped;

/**
 * Abstract implementation of a JUnit {@link MethodRule} which controls the life
 * cycle of JUnit-related contexts, see {@link MethodScoped}.
 * 
 * @author dirk.mahler
 */
@SuppressWarnings("deprecation")
@Deprecated
public class TestBeansMethodRule implements MethodRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement apply(final Statement base, final FrameworkMethod method,
			final Object target) {

		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				try {
					TestContextManager.getInstance().activate(
							MethodScoped.class,
							new MethodDescription(method.getName()));
					base.evaluate();
				} catch (Throwable t) {
					throw t;
				} finally {
					TestContextManager.getInstance().deactivate(
							MethodScoped.class,
							new MethodDescription(method.getName()));
				}
			}
		};
	}
}
