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
package com.buschmais.testbeans.core.context;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

import com.buschmais.testbeans.core.WeldManager;

/**
 * Implementation of {@link Extension} as defined by JSR-299 which registers the
 * customs contexts controlled by the {@link WeldManager}.
 * 
 * @author dirk.mahler
 */
public class TestContextExtension implements Extension {

	/**
	 * {@inheritDoc}
	 */
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event,
			BeanManager manager) {
		WeldManager weldManager = WeldManager.getInstance();
		event.addContext(weldManager.getMethodContext());
		event.addContext(weldManager.getClassContext());
		event.addContext(weldManager.getSuiteContext());
	}
}
