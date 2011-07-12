package com.buschmais.testbeans.junit.extension.context;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract implementation of a test context.
 * <p>
 * Despite the contract required by {@link Context} methods are provided for
 * activation and deactivation of a context.
 * </p>
 * 
 * @author dirk.mahler
 */
public abstract class AbstractTestContext implements Context {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractTestContext.class);

	/**
	 * Represents a contextual instance.
	 * 
	 * @author dirk.mahler
	 * 
	 * @param <T>
	 *            The type of the contextual instance.
	 */
	private static class ContextualInstance<T> {

		private Contextual<T> contextual;

		private CreationalContext<T> creationalContext;

		private T instance;

		private ContextualInstance(Contextual<T> contextual,
				CreationalContext<T> creationalContext, T instance) {
			this.contextual = contextual;
			this.creationalContext = creationalContext;
			this.instance = instance;
		}

		public Contextual<T> getContextual() {
			return contextual;
		}

		public CreationalContext<T> getCreationalContext() {
			return creationalContext;
		}

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
	 * Activates the context.
	 */
	public void activate() {
		if (contextualInstances == null) {
			LOGGER.debug("Activating " + this.getClass().getSimpleName() + ".");
			contextualInstances = new HashMap<Contextual<?>, ContextualInstance<?>>();
		}
	}

	/**
	 * Deactivates the context and destroys all contextual instances.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T get(Contextual<T> contextual) {
		@SuppressWarnings("unchecked")
		ContextualInstance<T> contextualInstance = (ContextualInstance<T>) contextualInstances
				.get(contextual);
		if (contextualInstance != null) {
			return contextualInstance.getInstance();
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
}