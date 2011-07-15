package com.buschmais.testbeans.test.junit.owbse.methodrule.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.junit.owbse.OwbSESuite;

/**
 * Defines a suite using {@link OwbSESuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@RunWith(OwbSESuite.class)
public class ContextTest {

}
