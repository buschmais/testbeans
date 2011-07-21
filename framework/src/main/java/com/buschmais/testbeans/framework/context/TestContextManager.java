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
import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.framework.After;
import com.buschmais.testbeans.framework.Before;
import com.buschmais.testbeans.framework.description.ClassDescription;
import com.buschmais.testbeans.framework.description.Description;
import com.buschmais.testbeans.framework.description.MethodDescription;
import com.buschmais.testbeans.framework.description.SuiteDescription;

/**
 * 
 * Allows lifecycle control of the test contexts.
 * 
 * @author dirk.mahler
 */
public class TestContextManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestContextManager.class);

	private static final TestContextManager instance = new TestContextManager();

	/**
	 * The {@link BeanManager}.
	 */
	private BeanManager beanManager = null;

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
	 * Returns the {@link TestContextManager} instance.
	 */
	public static TestContextManager getInstance() {
		return instance;
	}

	/**
	 * Set the {@link BeanManager}.
	 * 
	 * @param beanManager
	 *            The {@link BeanManager}.
	 */
	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	/**
	 * Activates the suite context;
	 */
	public void activateSuiteContext(SuiteDescription suiteDescription) {
		activate(suiteContext, suiteDescription);
	}

	/**
	 * Deactivates the suite context;
	 */
	public void deactivateSuiteContext(SuiteDescription suiteDescription) {
		deactivate(suiteContext, suiteDescription);
	}

	/**
	 * Activates the class context;
	 */
	public void activateClassContext(ClassDescription classDescription) {
		activate(classContext, classDescription);
	}

	/**
	 * Deactivates the class context;
	 */
	public void deactivateClassContext(ClassDescription classDescription) {
		deactivate(classContext, classDescription);
	}

	/**
	 * Activates the method context;
	 */
	public void activateMethodContext(MethodDescription methodDescription) {
		activate(methodContext, methodDescription);
	}

	/**
	 * Deactivates the method context;
	 */
	public void deactivateMethodContext(MethodDescription methodDescription) {
		deactivate(methodContext, methodDescription);
	}

	/**
	 * Returns a list of all managed test contexts.
	 * 
	 * @return A list of all managed test contexts.
	 */
	public List<TestContext> getTestContexts() {
		return Arrays.asList(new TestContext[] { suiteContext, classContext,
				methodContext });
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
		Bean<?> bean = beanManager.getBeans(type, qualifier).iterator().next();
		return (T) beanManager.getReference(bean, type,
				beanManager.createCreationalContext(bean));
	}

	/**
	 * Activates a given {@link TestContext}.
	 * 
	 * @param testContext
	 *            The {@link TestContext}.
	 * @param description
	 *            The {@link Description}.
	 */
	@SuppressWarnings("serial")
	private void activate(TestContext testContext, Description description) {
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
	 * @param testContext
	 *            The {@link TestContext}.
	 * @param description
	 *            The {@link Description}.
	 */
	@SuppressWarnings("serial")
	private void deactivate(TestContext testContext, Description description) {
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
	 * Fires an event using the provided description and qualifier.
	 * 
	 * @param description
	 *            The {@link Description}.
	 * @param qualifier
	 *            The qualifier.
	 */
	private void fireEvent(Description description,
			AnnotationLiteral<? extends Annotation> qualifier) {
		if (beanManager != null) {
			beanManager.fireEvent(description, qualifier);
		}
	}
}
