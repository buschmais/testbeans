package com.buschmais.testbeans.junit.extension.context;

import java.lang.annotation.Annotation;

import com.buschmais.testbeans.junit.extension.SuiteScoped;

/**
 * The suite context.
 * 
 * @author dirk.mahler
 */
public class SuiteContext extends AbstractTestContext {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends Annotation> getScope() {
		return SuiteScoped.class;
	}
}
