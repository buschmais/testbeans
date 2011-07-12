package com.buschmais.testbeans.junit.test.bean.event;

import java.util.LinkedList;
import java.util.Queue;

import javax.enterprise.event.Observes;

import org.junit.runner.Description;

import com.buschmais.testbeans.junit.extension.After;
import com.buschmais.testbeans.junit.extension.Before;
import com.buschmais.testbeans.junit.extension.ClassScoped;
import com.buschmais.testbeans.junit.extension.WeldRule;

/**
 * A bean observing events fired by the {@link WeldRule}.
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
