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
package com.buschmais.testbeans.framework.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.NormalScope;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.event.After;
import com.buschmais.testbeans.framework.event.Before;
import com.buschmais.testbeans.framework.event.Description;

/**
 * 
 * Allows lifecycle control of the test contexts.
 * 
 * @author dirk.mahler
 */
public class TestContextManager {

	/**
	 * The {@link Logger} instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestContextManager.class);

	/**
	 * The {@link TestContextManager} singleton.
	 */
	private static final TestContextManager INSTANCE = new TestContextManager();

	/**
	 * The {@link BeanManager}.
	 */
	private BeanManager beanManager = null;

	/**
	 * The {@link TestContext}s managed by this {@link TestContextManager}.
	 */
	private Map<Class<? extends Annotation>, TestContext> testContexts = new HashMap<Class<? extends Annotation>, TestContext>();

	/**
	 * Returns the {@link TestContextManager} instance.
	 * 
	 * @return The {@link TestContextManager} instance.
	 */
	public static TestContextManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates {@link TestContext}s for the given scopes.
	 * 
	 * @param scopes
	 *            The scopes.
	 */
	public void create(Class<? extends Annotation>... scopes) {
		assertStarted(false);
		for (Class<? extends Annotation> scope : scopes) {
			if (scope.getAnnotation(NormalScope.class) != null) {
				testContexts.put(scope, new TestContext(scope));
			} else {
				throw new IllegalArgumentException(scope.getName()
						+ " is not annotated with " + NormalScope.class);
			}
		}
	}

	/**
	 * Starts the {@link TestContextManager}.
	 * 
	 * @param manager
	 *            The {@link BeanManager}.
	 */
	public void start(BeanManager manager) {
		assertStarted(false);
		this.beanManager = manager;
	}

	/**
	 * Stops the {@link TestContextManager}.
	 */
	public void stop() {
		assertStarted(true);
		this.beanManager = null;
		this.testContexts.clear();
	}

	/**
	 * Returns a {@link Collection} of all managed test contexts.
	 * 
	 * @return A {@link Collection} of all managed test contexts.
	 */
	public Collection<TestContext> getTestContexts() {
		return testContexts.values();
	}

	/**
	 * Returns an instance of the given type.
	 * 
	 * @param <T>
	 *            The type.
	 * @param type
	 *            The Type.
	 * @return The instance.
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public <T> T get(Type type) {
		return (T) this.get(type, new AnnotationLiteral<Default>() {
		});
	}

	/**
	 * Returns an instance of the given type.
	 * 
	 * @param <T>
	 *            The type.
	 * @param type
	 *            The Type.
	 * @param qualifier
	 *            The qualifiers.
	 * @return The instance.
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Type type, Annotation... qualifier) {
		assertStarted(true);
		Iterator<Bean<?>> iterator = beanManager.getBeans(type, qualifier)
				.iterator();
		if (iterator.hasNext()) {
			Bean<?> bean = iterator.next();
			return (T) beanManager.getReference(bean, type,
					beanManager.createCreationalContext(bean));
		}
		return null;
	}

	/**
	 * Fires an event using the provided value and qualifier.
	 * 
	 * @param value
	 *            The value.
	 * @param qualifier
	 *            The qualifier.
	 */
	public void fireEvent(Object value,
			AnnotationLiteral<? extends Annotation> qualifier) {
		assertStarted(true);
		beanManager.fireEvent(value, qualifier);
	}

	/**
	 * Checks wether the {@link TestContextManager} is started/stopped and logs
	 * a warning.
	 * 
	 * @param started
	 *            <code>true</code>, if the {@link TestContextManager} is
	 *            assumed to be started.
	 */
	private void assertStarted(boolean started) {
		if ((started && this.beanManager == null)
				|| (!started && this.beanManager != null)) {
			LOGGER.warn(TestContextManager.class.getSimpleName() + " is not "
					+ (started ? "started" : "stopped"));
		}
	}

	/**
	 * Activates a given {@link TestContext}.
	 * 
	 * @param scope
	 *            The scope of the {@link TestContext} to activate.
	 * @param description
	 *            The {@link Description}.
	 */
	@SuppressWarnings("serial")
	public void activate(Class<? extends Annotation> scope,
			Description description) {
		assertStarted(true);
		TestContext testContext = this.getTestContext(scope);
		if (!testContext.isActive()) {
			testContext.activate();
			fireEvent(description, new AnnotationLiteral<Before>() {
			});
		} else {
			LOGGER.warn("Cannot activate " + testContext + ", it is active.");
		}
	}

	/**
	 * Deactivates a given {@link TestContext}.
	 * 
	 * @param scope
	 *            The scope of the {@link TestContext} to deactivate.
	 * @param description
	 *            The {@link Description}.
	 */
	@SuppressWarnings("serial")
	public void deactivate(Class<? extends Annotation> scope,
			Description description) {
		assertStarted(true);
		TestContext testContext = this.getTestContext(scope);
		if (testContext.isActive()) {
			fireEvent(description, new AnnotationLiteral<After>() {
			});
			testContext.deactivate();
		} else {
			LOGGER.warn("Cannot deactivate " + testContext
					+ ", it is not active.");
		}
	}

	/**
	 * Returns the {@link TestContext} for the given scope.
	 * 
	 * @param scope
	 *            The scope.
	 * @return The {@link TestContext}.
	 */
	private TestContext getTestContext(Class<? extends Annotation> scope) {
		TestContext testContext = testContexts.get(scope);
		if (testContext == null) {
			throw new IllegalArgumentException("Unknown scope "
					+ scope.getName());
		}
		return testContext;
	}

}
