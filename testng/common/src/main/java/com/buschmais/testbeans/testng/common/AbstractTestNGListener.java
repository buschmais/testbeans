package com.buschmais.testbeans.testng.common;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.buschmais.testbeans.framework.Container;

public abstract class AbstractTestNGListener implements IInvokedMethodListener,
		ISuiteListener {

	private static ClassStateManager classStateManager = getStateManager();

	private static ITestNGMethod lastMethod = null;
	private static ITestNGMethod nextMethod = null;

	@Override
	public void onStart(ISuite suite) {
		execute(classStateManager.onEvent(Event.BEGINSUITE));
	}

	@Override
	public void onFinish(ISuite suite) {
		execute(classStateManager.onEvent(Event.ENDSUITE));
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		nextMethod = method.getTestMethod();
		// class state
		Class<?> currentClass = nextMethod.getConstructorOrMethod()
				.getDeclaringClass();
		if (lastMethod == null
				|| !lastMethod.getConstructorOrMethod().getDeclaringClass()
						.equals(currentClass)) {
			if (lastMethod != null) {
				execute(classStateManager.onEvent(Event.ENDCLASS));
			}
			execute(classStateManager.onEvent(Event.BEGINCLASS));
		}
		// method state
		Action[] actions = null;
		if (nextMethod.isBeforeClassConfiguration()) {
			actions = classStateManager.onEvent(Event.BEFORECLASS);
		} else if (nextMethod.isBeforeMethodConfiguration()) {
			actions = classStateManager.onEvent(Event.BEFOREMETHOD);
		} else if (nextMethod.isTest()) {
			actions = classStateManager.onEvent(Event.METHOD);
		} else if (nextMethod.isBeforeClassConfiguration()) {
			actions = classStateManager.onEvent(Event.AFTERMETHOD);
		} else if (nextMethod.isBeforeClassConfiguration()) {
			actions = classStateManager.onEvent(Event.AFTERCLASS);
		}
		if (actions != null) {
			execute(actions);
		}

	}

	private void execute(Action... actions) {

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	}

	protected abstract Container getContainer();

	private static ClassStateManager getStateManager() {
		ClassStateManager sm = new ClassStateManager(Event.BEGINCLASS);
		sm.from(Event.BEGINCLASS)
				.select(sm.next(Event.BEFORECLASS).execute(
						Action.activateClassContext),
						sm.next(Event.BEFOREMETHOD).execute(
								Action.activateClassContext,
								Action.activateMethodContext),
						sm.next(Event.METHOD).execute(
								Action.activateClassContext,
								Action.activateMethodContext));
		sm.from(Event.BEFORECLASS).select(
				sm.next(Event.BEFOREMETHOD).execute(
						Action.activateMethodContext),
				sm.next(Event.METHOD).execute(Action.activateMethodContext));
		sm.from(Event.METHOD).select(
				sm.next(Event.AFTERCLASS).execute(
						Action.deactivateMethodContext),
				sm.next(Event.ENDCLASS).execute(Action.deactivateMethodContext,
						Action.deactivateClassContext));
		sm.from(Event.AFTERMETHOD).select(
				sm.next(Event.AFTERCLASS).execute(
						Action.deactivateMethodContext),
				sm.next(Event.ENDCLASS).execute(Action.deactivateMethodContext,
						Action.deactivateClassContext));
		sm.from(Event.AFTERCLASS).select(
				sm.next(Event.ENDCLASS).execute(Action.deactivateClassContext));
		return sm;
	}
}
