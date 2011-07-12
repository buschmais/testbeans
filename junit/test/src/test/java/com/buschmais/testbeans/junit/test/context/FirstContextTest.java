package com.buschmais.testbeans.junit.test.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.testbeans.junit.test.bean.context.ClassScopedBean;
import com.buschmais.testbeans.junit.test.bean.context.MethodScopedBean;
import com.buschmais.testbeans.junit.test.bean.context.SuiteScopedBean;

/**
 * Concrete implementation of a test checking correct scoping of beans.
 * 
 * @author dirk.mahler
 */
public class FirstContextTest extends AbstractContextTest {

	@BeforeClass
	public static void beforeConcreteClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

	@Before
	public void beforeConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	@Test
	public void testConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	@After
	public void afterConcrete() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkPayload(MethodScopedBean.class, "test");
	}

	@AfterClass
	public static void afterConcreteClass() {
		checkPayload(SuiteScopedBean.class, "default");
		checkPayload(ClassScopedBean.class, "class");
		checkUnexpectedBean(MethodScopedBean.class);
	}

}
