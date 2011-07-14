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
package com.buschmais.testbeans.junit.test;

import org.jboss.weld.environment.se.WeldContainer;
import org.junit.ClassRule;
import org.junit.Rule;

import com.buschmais.testbeans.junit.extension.WeldRule;
import com.buschmais.testbeans.weldse.WeldSETestContextManager;

/**
 * Abstract base class for CDI tests.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractCdiTest {

	/**
	 * The {@link WeldRule}.
	 */
	@Rule
	@ClassRule
	public static WeldRule weldRule = new WeldRule();

	/**
	 * Returns the instance of the {@link WeldContainer}.
	 * 
	 * @return The {@link WeldContainer}.
	 */
	public static WeldContainer getWeldContainer() {
		return WeldSETestContextManager.getInstance().getWeldContainer();
	}
}
