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
package com.buschmais.testbeans.junit.weldse;

import org.junit.rules.TestRule;

import com.buschmais.testbeans.container.weldse.WeldSEContainer;
import com.buschmais.testbeans.framework.ClassScoped;
import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.framework.MethodScoped;
import com.buschmais.testbeans.junit.common.AbstractMethodRule;

/**
 * Implementation of a JUnit {@link TestRule} which controls the life cycle of
 * JUnit-related contexts, see {@link ClassScoped} and {@link MethodScoped}.
 * 
 * Usage:
 * 
 * <pre>
 * &#064;Rule
 * &#064;ClassRule
 * public static TestRule weldRule = new WeldSEMethodRule();
 * </pre>
 * 
 * @author dirk.mahler
 */
@SuppressWarnings("deprecation")
@Deprecated
public class WeldSEMethodRule extends AbstractMethodRule {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Container getContainer() {
		return WeldSEContainer.getInstance();
	}

}
