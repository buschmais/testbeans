package com.buschmais.testbeans.test.junit.weldse.testrule.context;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.container.weldse.WeldSE;
import com.buschmais.testbeans.framework.container.CdiContainer;
import com.buschmais.testbeans.junit.common.TestBeansSuite;
import com.buschmais.testbeans.test.junit.common.testrule.context.ContextTest1;
import com.buschmais.testbeans.test.junit.common.testrule.context.ContextTest2;

/**
 * Defines a suite using {@link TestBeansSuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses({ ContextTest1.class, ContextTest2.class })
@CdiContainer(WeldSE.class)
@RunWith(TestBeansSuite.class)
public class ContextTest {

}
