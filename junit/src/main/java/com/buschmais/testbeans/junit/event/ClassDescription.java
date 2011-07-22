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
package com.buschmais.testbeans.junit.event;

import com.buschmais.testbeans.framework.event.Description;

/**
 * Describes a test class.
 * 
 * @author dirk.mahler
 */
public class ClassDescription implements Description {

	private String className;

	/**
	 * Constructor.
	 * 
	 * @param className
	 *            The name of the test class.
	 */
	public ClassDescription(String className) {
		this.className = className;
	}

	/**
	 * Returns the name of the test class.
	 * 
	 * @return The name of the test class.
	 */
	public String getClassName() {
		return className;
	}
}
