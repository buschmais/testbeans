package com.buschmais.testbeans.junit.test;

import org.junit.ClassRule;
import org.junit.Rule;

import com.buschmais.testbeans.junit.extension.WeldRule;

/**
 * Abstract base class for CDI tests.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractCdiTest {

	/**
	 * The {@link WeldRule}.
	 */
	@Rule
	@ClassRule
	public static WeldRule weldRule = WeldRule.getInstance();

}
