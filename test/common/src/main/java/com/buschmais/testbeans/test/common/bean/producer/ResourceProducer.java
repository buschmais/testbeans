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
package com.buschmais.testbeans.test.common.bean.producer;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import com.buschmais.testbeans.core.ClassScoped;
import com.buschmais.testbeans.core.MethodScoped;

/**
 * Produces and disposes {@link Resource}s.
 * 
 * @author dirk.mahler
 */
@ClassScoped
public class ResourceProducer {

	/**
	 * Holds all resource which have been produced but not yet disposed.
	 */
	private Set<Resource> resources = new HashSet<Resource>();

	private int sequence = 0;

	/**
	 * Returns the produced but not yet disposed resources.
	 * 
	 * @return The resources.
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * Produces a method scoped resources.
	 * 
	 * @return The method scoped resource.
	 */
	@Produces
	@Managed
	@Default
	@MethodScoped
	public Resource getResource() {
		Resource resource = new Resource();
		resource.setId(++sequence);
		resources.add(resource);
		return resource;
	}

	/**
	 * Disposes a method scoped resource.
	 * 
	 * @param resource
	 *            The resource.
	 */
	public void close(@Disposes @Default @Managed Resource resource) {
		resources.remove(resource);
	}
}
