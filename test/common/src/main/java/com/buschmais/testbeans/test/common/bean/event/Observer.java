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
package com.buschmais.testbeans.test.common.bean.event;

import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.event.Observes;

import com.buschmais.testbeans.framework.After;
import com.buschmais.testbeans.framework.Before;
import com.buschmais.testbeans.framework.ClassScoped;
import com.buschmais.testbeans.framework.description.Description;

/**
 * A bean observing events.
 * 
 * @author dirk.mahler
 */
@ClassScoped
public class Observer {

	private Queue<Description> beforeEvents = new LinkedList<Description>();

	private Queue<Description> afterEvents = new LinkedList<Description>();

	public void before(@Observes @Before Description description) {
		beforeEvents.offer(description);
	}

	public void after(@Observes @After Description description) {
		afterEvents.offer(description);
	}

	public Queue<Description> getBeforeEvents() {
		return beforeEvents;
	}

	public Queue<Description> getAfterEvents() {
		return afterEvents;
	}
}
