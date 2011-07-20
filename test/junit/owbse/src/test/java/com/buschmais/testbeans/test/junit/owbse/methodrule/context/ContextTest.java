package com.buschmais.testbeans.test.junit.owbse.methodrule.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.container.owbse.OpenWebBeansSE;
import com.buschmais.testbeans.framework.container.CdiContainer;
import com.buschmais.testbeans.junit.common.TestBeansSuite;
import com.buschmais.testbeans.test.junit.common.methodrule.context.ContextTest1;
import com.buschmais.testbeans.test.junit.common.methodrule.context.ContextTest2;

/**
 * Defines a suite using {@link TestBeansSuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@CdiContainer(OpenWebBeansSE.class)
@RunWith(TestBeansSuite.class)
public class ContextTest {

}
