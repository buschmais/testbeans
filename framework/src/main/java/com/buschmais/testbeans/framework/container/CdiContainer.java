package com.buschmais.testbeans.framework.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the CDI container. 
 * @author dirk.mahler
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CdiContainer {

	/**
	 * The class of the CDI {@link CdiContainerAdapter} which will control the life
	 * cycle of the CDI container.
	 */
	Class<? extends CdiContainerAdapter> value();
}
