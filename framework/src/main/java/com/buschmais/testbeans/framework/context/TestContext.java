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
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of a test context.
 * <p>
 * Despite the contract required by {@link Context} methods are provided for
 * activation and deactivation of a context.
 * </p>
 * 
 * @author dirk.mahler
 */
public class TestContext implements Context {

	/**
	 * The {@link Logger} instance.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TestContext.class);

	/**
	 * Represents a contextual instance.
	 * 
	 * @author dirk.mahler
	 * 
	 * @param <T>
	 *            The type of the contextual instance.
	 */
	private static final class ContextualInstance<T> {

		/**
		 * The {@link Contextual}.
		 */
		private Contextual<T> contextual;

		/**
		 * The {@link CreationalContext}.
		 */
		private CreationalContext<T> creationalContext;

		/**
		 * The instance.
		 */
		private T instance;

		/**
		 * Constructs a {@link ContextualInstance}.
		 * 
		 * @param contextual
		 *            The {@link Contextual}.
		 * @param creationalContext
		 *            The {@link CreationalContext}.
		 * @param instance
		 *            The instance.
		 */
		private ContextualInstance(Contextual<T> contextual,
				CreationalContext<T> creationalContext, T instance) {
			this.contextual = contextual;
			this.creationalContext = creationalContext;
			this.instance = instance;
		}

		/**
		 * Returns the {@link Contextual}.
		 * 
		 * @return The {@link Contextual}.
		 */
		public Contextual<T> getContextual() {
			return contextual;
		}

		/**
		 * Returns the {@link CreationalContext}.
		 * 
		 * @return The {@link CreationalContext}.
		 */
		public CreationalContext<T> getCreationalContext() {
			return creationalContext;
		}

		/**
		 * Returns the instance.
		 * 
		 * @return The instance.
		 */
		public T getInstance() {
			return instance;
		}

	}

	/**
	 * A map of contextual instances which this context holds. Is set to
	 * <code>null</code> if the context is not active.
	 */
	private Map<Contextual<?>, ContextualInstance<?>> contextualInstances = null;

	/**
	 * The scope which is bound to this {@link Context}.
	 */
	private Class<? extends Annotation> scope;

	/**
	 * Constructs the {@link TestContext}.
	 * 
	 * @param scope
	 *            The scope which is bound to this {@link Context}.
	 */
	public TestContext(Class<? extends Annotation> scope) {
		this.scope = scope;
	}

	/**
	 * Activates the {@link TestContext}.
	 */
	public void activate() {
		if (contextualInstances == null) {
			LOGGER.debug("Activating " + this.getClass().getSimpleName() + ".");
			contextualInstances = new HashMap<Contextual<?>, ContextualInstance<?>>();
		}
	}

	/**
	 * Deactivates the {@link TestContext}.
	 */
	public void deactivate() {
		if (contextualInstances != null) {
			LOGGER.debug("Deactivating " + this.getClass().getSimpleName()
					+ ".");
			for (ContextualInstance<?> contextualInstance : contextualInstances
					.values()) {
				destroy(contextualInstance);
			}
			contextualInstances = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Contextual<T> contextual,
			CreationalContext<T> creationalContext) {
		if (contextualInstances == null) {
			return null;
		} else {
			ContextualInstance<T> contextualInstance;
			if (contextualInstances.containsKey(contextual)) {
				contextualInstance = (ContextualInstance<T>) contextualInstances
						.get(contextual);
			} else {
				T instance = contextual.create(creationalContext);
				contextualInstance = new ContextualInstance<T>(contextual,
						creationalContext, instance);
				contextualInstances.put(contextual, contextualInstance);
			}
			return contextualInstance.getInstance();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T get(Contextual<T> contextual) {
		if (contextualInstances != null) {
			@SuppressWarnings("unchecked")
			ContextualInstance<T> contextualInstance = (ContextualInstance<T>) contextualInstances
					.get(contextual);
			if (contextualInstance != null) {
				return contextualInstance.getInstance();
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActive() {
		return contextualInstances != null;
	}

	/**
	 * Destroys a contextual instance.
	 * 
	 * @param <T>
	 *            The type of the contextual instance.
	 * @param contextualInstance
	 *            The contextual instance.
	 */
	private <T> void destroy(ContextualInstance<?> contextualInstance) {
		@SuppressWarnings("unchecked")
		ContextualInstance<T> instance = (ContextualInstance<T>) contextualInstance;
		instance.getContextual().destroy(instance.getInstance(),
				instance.getCreationalContext());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return this.scope;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TestContext [scope=" + scope + "]";
	}
}
