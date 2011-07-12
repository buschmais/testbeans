package com.buschmais.testbeans.junit.test.bean.producer;

/**
 * A resource which is produced/disposed by the {@link ResourceProducer}.
 * 
 * @author dirk.mahler
 */
public class Resource {

	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
