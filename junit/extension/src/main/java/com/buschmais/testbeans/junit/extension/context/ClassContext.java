package com.buschmais.testbeans.junit.extension.context;

import java.lang.annotation.Annotation;

import com.buschmais.testbeans.junit.extension.ClassScoped;

/**
 * A class context.
 * <p>
 * </p>
 * 
 * @author dirk.mahler
 */
public class ClassContext extends AbstractTestContext {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return ClassScoped.class;
	}
}
