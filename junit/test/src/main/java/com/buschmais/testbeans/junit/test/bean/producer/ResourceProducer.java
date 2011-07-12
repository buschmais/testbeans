package com.buschmais.testbeans.junit.test.bean.producer;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import com.buschmais.testbeans.junit.extension.ClassScoped;
import com.buschmais.testbeans.junit.extension.MethodScoped;

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
