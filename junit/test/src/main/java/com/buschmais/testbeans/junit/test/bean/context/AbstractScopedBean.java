package com.buschmais.testbeans.junit.test.bean.context;

import com.buschmais.testbeans.junit.extension.WeldRule;

/**
 * Abstract implemenation of a scoped bean used for testing the {@link WeldRule}.
 */
public class AbstractScopedBean {

	private String payload = "default";

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
