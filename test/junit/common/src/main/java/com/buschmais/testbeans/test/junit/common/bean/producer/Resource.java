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
package com.buschmais.testbeans.test.junit.common.bean.producer;

/**
 * A resource which is produced/disposed by the {@link ResourceProducer}.
 * 
 * @author dirk.mahler
 */
public class Resource {

	/**
	 * The id of the resource.
	 */
	private int id;

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            The id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the id.
	 * 
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
}
