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
package com.buschmais.testbeans.framework.description;

/**
 * Describes a test class.
 * 
 * @author dirk.mahler
 */
public class MethodDescription extends Description {

	private String methodName;

	/**
	 * Constructor.
	 * 
	 * @param methodName
	 *            The name of the test method.
	 */
	public MethodDescription(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Returns the name of the test method.
	 * 
	 * @return The name of the test method.
	 */
	public String getMethodName() {
		return methodName;
	}

}