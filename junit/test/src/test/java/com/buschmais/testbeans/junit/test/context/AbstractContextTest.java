package com.buschmais.testbeans.junit.test.context;

import javax.enterprise.context.ContextNotActiveException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.junit.test.AbstractCdiTest;
import com.buschmais.testbeans.junit.test.bean.context.AbstractScopedBean;
import com.buschmais.testbeans.junit.test.bean.context.ClassScopedBean;
import com.buschmais.testbeans.junit.test.bean.context.MethodScopedBean;
import com.buschmais.testbeans.junit.test.bean.context.SuiteScopedBean;

/**
 * Abstract base class for tests checking correct scoping of the beans.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractContextTest extends AbstractCdiTest {

	@BeforeClass
	public static void beforeAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "default");
		setPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	@Before
	public void beforeAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "default");
		setPayload(MethodScopedBean.class, "test");
	}

	@Test
	public void testAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	@After
	public void afterAbstract() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	@AfterClass
	public static void afterAbstractClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	private static AbstractScopedBean getBean(Class<?> beanClass) {
		return (AbstractScopedBean) weldRule.getWeldContainer().instance()
				.select(beanClass).get();
	}

	private static void setPayload(Class<?> beanClass, String payload) {
		getBean(beanClass).setPayload(payload);
	}

	protected static void checkPayload(Class<?> expectedBeanClass,
			String expectedPayload) {
		try {
			Assert.assertEquals(expectedPayload, getBean(expectedBeanClass)
					.getPayload());
		} catch (ContextNotActiveException e) {
			Assert.fail("expected bean " + expectedBeanClass);
		}
	}

	protected static void checkUnexpectedBean(Class<?> unexpectedBeanClass) {
		try {
			getBean(unexpectedBeanClass).getPayload();
			Assert.fail("unexpected bean " + unexpectedBeanClass);
		} catch (ContextNotActiveException e) {
		}
	}
}
