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
package com.buschmais.testbeans.test.junit.weldse.testrule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;

import com.buschmais.testbeans.junit.weldse.WeldSERule;

/**
 * Abstract base class for tests using {@link WeldSERule}.
 * 
 * @author dirk.mahler
 */
public abstract class AbstractWeldSETest {

	/**
	 * The {@link WeldSERule}.
	 */
	@Rule
	@ClassRule
	public static TestRule weldRule = new WeldSERule();

}
