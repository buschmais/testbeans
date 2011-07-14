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
package com.buschmais.testbeans.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

import com.buschmais.testbeans.core.context.ClassContext;
import com.buschmais.testbeans.core.context.MethodContext;
import com.buschmais.testbeans.core.context.SuiteContext;
import com.buschmais.testbeans.core.description.ClassDescription;
import com.buschmais.testbeans.core.description.MethodDescription;
import com.buschmais.testbeans.core.description.SuiteDescription;

/**
 * 
 * Abstract implementation which provides access to a CDI container and allows
 * lifecycle control of the test contexts.
 * 
 * @author dirk.mahler
 * 
 */
public abstract class Container {
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
	 * Activates the suite context;
	 */
	@SuppressWarnings("serial")
	public void activateSuiteContext(SuiteDescription suiteDescription) {
		this.suiteContext.activate();
		this.getBeanManager().fireEvent(suiteDescription,
				new AnnotationLiteral<Before>() {
				});
	}

	/**
	 * Deactivates the suite context;
	 */
	@SuppressWarnings("serial")
	public void deactivateSuiteContext(SuiteDescription suiteDescription) {
		this.getBeanManager().fireEvent(suiteDescription,
				new AnnotationLiteral<After>() {
				});
		this.suiteContext.deactivate();
	}

	/**
	 * Activates the class context;
	 */
	@SuppressWarnings("serial")
	public void activateClassContext(ClassDescription classDescription) {
		this.classContext.activate();
		this.getBeanManager().fireEvent(classDescription,
				new AnnotationLiteral<Before>() {
				});
	}

	/**
	 * Deactivates the class context;
	 */
	@SuppressWarnings("serial")
	public void deactivateClassContext(ClassDescription classDescription) {
		this.getBeanManager().fireEvent(classDescription,
				new AnnotationLiteral<After>() {
				});
		this.classContext.deactivate();
	}

	/**
	 * Activates the method context;
	 */
	@SuppressWarnings("serial")
	public void activateMethodContext(MethodDescription methodDescription) {
		this.methodContext.activate();
		this.getBeanManager().fireEvent(methodDescription,
				new AnnotationLiteral<Before>() {
				});
	}

	/**
	 * Deactivates the method context;
	 */
	@SuppressWarnings("serial")
	public void deactivateMethodContext(MethodDescription methodDescription) {
		this.getBeanManager().fireEvent(methodDescription,
				new AnnotationLiteral<After>() {
				});
		this.methodContext.deactivate();
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
	@SuppressWarnings("serial")
	public <T> T get(Type type) {
		return this.get(type, new AnnotationLiteral<Default>() {
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
		BeanManager beanManager = this.getBeanManager();
		Bean<?> bean = beanManager.getBeans(type, qualifier).iterator().next();
		return (T) beanManager.getReference(bean, type,
				beanManager.createCreationalContext(bean));
	}

	/**
	 * Start the container.
	 */
	public abstract void startContainer();

	/**
	 * Stop the container.
	 */
	public abstract void stopContainer();

	/**
	 * Returns the {@link BeanManager} provided by the container.
	 * 
	 * @return The {@link BeanManager}.
	 */
	public abstract BeanManager getBeanManager();

}