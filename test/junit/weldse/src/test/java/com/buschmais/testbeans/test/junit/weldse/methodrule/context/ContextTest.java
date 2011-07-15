package com.buschmais.testbeans.test.junit.weldse.methodrule.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.junit.weldse.WeldSESuite;

/**
 * Defines a suite using {@link WeldSESuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@RunWith(WeldSESuite.class)
public class ContextTest {

}
