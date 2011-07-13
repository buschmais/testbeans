package com.buschmais.testbeans.junit.test.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.junit.extension.WeldSuite;

/**
 * Defines a suite using {@link WeldSuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@RunWith(WeldSuite.class)
public class ContextTest {

}
