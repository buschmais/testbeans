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
package com.buschmais.testbeans.test.junit.common.bean.event;

import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.event.Observes;

import com.buschmais.testbeans.framework.event.After;
import com.buschmais.testbeans.framework.event.Before;
import com.buschmais.testbeans.framework.event.Description;
import com.buschmais.testbeans.junit.scope.SuiteScoped;

/**
 * A bean observing events.
 * 
 * @author dirk.mahler
 */
@SuiteScoped
public class Observer {

	/**
	 * The captured {@link Before} events.
	 */
	private Queue<Description> beforeEvents = new LinkedList<Description>();

	/**
	 * The captured {@link After} events.
	 */
	private Queue<Description> afterEvents = new LinkedList<Description>();

	/**
	 * {@link Before} event observer.
	 * 
	 * @param description
	 *            The {@link Description}.
	 */
	public void before(@Observes @Before Description description) {
		beforeEvents.offer(description);
	}

	/**
	 * {@link After} event observer.
	 * 
	 * @param description
	 *            The {@link Description}.
	 */
	public void after(@Observes @After Description description) {
		afterEvents.offer(description);
	}

	/**
	 * Returns the capured {@link Before} events.
	 * 
	 * @return The capured {@link Before} events.
	 */
	public Queue<Description> getBeforeEvents() {
		return beforeEvents;
	}

	/**
	 * Returns the capured {@link After} events.
	 * 
	 * @return The capured {@link After} events.
	 */
	public Queue<Description> getAfterEvents() {
		return afterEvents;
	}
}
