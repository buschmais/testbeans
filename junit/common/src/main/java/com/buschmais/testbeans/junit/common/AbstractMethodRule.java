package com.buschmais.testbeans.junit.common;

import org.junit.rules.MethodRule;
import org.junit.rules.TestRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.framework.MethodScoped;
import com.buschmais.testbeans.framework.description.MethodDescription;

/**
 * Abstract implementation of a JUnit {@link TestRule} which controls the life
 * cycle of JUnit-related contexts, see {@link MethodScoped}.
 * 
 * @author dirk.mahler
 */
@SuppressWarnings("deprecation")
@Deprecated
public abstract class AbstractMethodRule implements MethodRule {

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
					before(method, target);
					base.evaluate();
				} catch (Throwable t) {
					throw t;
				} finally {
					after(method, target);
				}
			}
		};
	}

	private void before(FrameworkMethod method, Object target) {
		Container container = getContainer();
		container
				.activateMethodContext(new MethodDescription(method.getName()));
	}

	private void after(FrameworkMethod method, Object target) {
		Container container = getContainer();
		container.deactivateMethodContext(new MethodDescription(method
				.getName()));

	}

	/**
	 * Returns the {@link Container}.
	 * 
	 * @return The {@link Container}.
	 */
	protected abstract Container getContainer();
}
