package com.buschmais.testbeans.testng.common;

import java.util.HashMap;
import java.util.Map;

class ClassStateManager {

	private Map<Event, Map<Event, Action[]>> transitions = new HashMap<Event, Map<Event, Action[]>>();

	public ClassStateManager(Event initial) {
		this.last = initial;
	}

	private Event last = Event.BEGINCLASS;

	Action[] onEvent(Event current) {
		Map<Event, Action[]> transition = transitions.get(last);
		last = current;
		if (transitions != null) {
			Action[] actions = transition.get(current);
			if (actions != null) {
				return actions;
			}
		}
		return new Action[] {};
	}

	public From from(Event e) {
		return new From(e);
	}

	public Next next(Event e) {
		return new Next(e);
	}

	class From {

		private Event e;

		From(Event e) {
			this.e = e;
		}

		void select(Next... n) {
			for (Next next : n) {
				transitions.put(e, next.getActions());
			}
		}

		Map<Event, Map<Event, Action[]>> getTransitions() {
			return transitions;
		}
	}

	class Next {

		private Event n;

		private Map<Event, Action[]> actions = new HashMap<Event, Action[]>();

		Next(Event n) {
			this.n = n;
		}

		Next execute(Action... a) {
			actions.put(n, a);
			return this;
		}

		Map<Event, Action[]> getActions() {
			return actions;
		}
	}
}
