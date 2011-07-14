package com.buschmais.testbeans.test.junit.owbse.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.junit.owbse.OpenWebBeansSESuite;

/**
 * Defines a suite using {@link OpenWebBeansSESuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@RunWith(OpenWebBeansSESuite.class)
public class ContextTest {

}
