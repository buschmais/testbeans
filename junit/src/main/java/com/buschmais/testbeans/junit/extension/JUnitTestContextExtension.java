/*
 * Copyright 2011 buschmais GbR
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.buschmais.testbeans.junit.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

import com.buschmais.testbeans.framework.context.TestContextManager;
import com.buschmais.testbeans.junit.scope.ClassScoped;
import com.buschmais.testbeans.junit.scope.MethodScoped;
import com.buschmais.testbeans.junit.scope.SuiteScoped;

/**
 * Implementation of a {@link Extension} which registers the JUnit specific test
 * contexts to the {@link TestContextManager}.
 * 
 * @author dirk.mahler
 */
public class JUnitTestContextExtension implements Extension {

	/**
	 * Observes the {@link BeforeBeanDiscovery} event of the CDI container.
	 */
	@SuppressWarnings("unchecked")
	public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event) {
		TestContextManager.getInstance().create(SuiteScoped.class,
				ClassScoped.class, MethodScoped.class);
	}
}
