package com.buschmais.testbeans.junit.extension.context;

import java.lang.annotation.Annotation;

import com.buschmais.testbeans.junit.extension.MethodScoped;

/**
 * The method context.
 * 
 * @author dirk.mahler
 */
public class MethodContext extends AbstractTestContext {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return MethodScoped.class;
	}
}
