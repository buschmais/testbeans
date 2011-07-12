package com.buschmais.testbeans.junit.extension;

import javax.enterprise.util.AnnotationLiteral;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buschmais.testbeans.junit.extension.context.ClassContext;
import com.buschmais.testbeans.junit.extension.context.MethodContext;
import com.buschmais.testbeans.junit.extension.context.SuiteContext;

/**
 * Implementation of a JUnit {@link TestRule} which controls the life cycle of
 * <ul>
 * <li>a Weld container (http://seamframework.org/Weld) providing
 * dependency injection services.</li>
 * <li>JUnit-related contexts, see {@link SuiteScoped}, {@link ClassScoped} and
 * {@link MethodScoped}.</li>
 * </ul>
 * 
 * Usage: 
 * <pre>@Rule @ClassRule public static WeldRule weldRule = WeldRule.getInstance();</pre>
 * 
 * @author dirk.mahler
 */
public class WeldRule implements TestRule {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeldRule.class);

	/**
	 * The Weld rule is singleton instance.
	 */
	private static final WeldRule INSTANCE = new WeldRule();

	/**
	 * The suite context.
	 */
	private SuiteContext suiteContext = new SuiteContext();

	/**
	 * The class context.
	 */
	private ClassContext classContext = new ClassContext();

	/**
	 * The method scope.
	 */
	private MethodContext methodContext = new MethodContext();

	/**
	 * The {@link Weld} and {@link WeldContainer} instances managed by this
	 * rule.
	 */
	private Weld weld = null;
	private WeldContainer weldContainer = null;

	/**
	 * Static initializer.
	 * <p>
	 * The Weld container is started, the suite context initialized and a VM
	 * shutdown hook is registered for cleanup.
	 */
	static {
		LOGGER.debug("Starting Weld.");
		INSTANCE.weld = new Weld();
		INSTANCE.weldContainer = INSTANCE.weld.initialize();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				INSTANCE.suiteContext.deactivate();
				LOGGER.debug("Stopping Weld.");
				INSTANCE.weld.shutdown();
			}
		});
		INSTANCE.suiteContext.activate();
	}

	/**
	 * Private constructor.
	 */
	private WeldRule() {
	}

	/**
	 * Returns the singleton instance of this rule.
	 * 
	 * @return The singleton instance.
	 */
	public static WeldRule getInstance() {
		return INSTANCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				before(description);
				try {
					base.evaluate();
				} finally {
					after(description);
				}
			}
		};
	}

	/**
	 * This method is called before a test class or test method is executed.
	 * 
	 * @param description
	 *            The test description provided by JUnit.
	 */
	@SuppressWarnings("serial")
	private void before(Description description) {
		if (description.isTest()) {
			methodContext.activate();
		} else {
			classContext.activate();
		}
		weldContainer.event()
				.select(Description.class, new AnnotationLiteral<Before>() {
				}).fire(description);
	}

	/**
	 * This method is called after a test class or test method was executed.
	 * 
	 * @param description
	 *            The test description provided by JUnit.
	 */
	@SuppressWarnings("serial")
	private void after(Description description) {
		weldContainer.event()
				.select(Description.class, new AnnotationLiteral<After>() {
				}).fire(description);
		if (description.isTest()) {
			methodContext.deactivate();
		} else {
			classContext.deactivate();
		}
	}

	/**
	 * Returns the {@link SuiteContext}.
	 * 
	 * @return The {@link SuiteContext}.
	 */
	public SuiteContext getSuiteContext() {
		return suiteContext;
	}

	/**
	 * Returns the {@link ClassContext}.
	 * 
	 * @return The {@link ClassContext}.
	 */
	public ClassContext getClassContext() {
		return classContext;
	}

	/**
	 * Returns the {@link MethodContext}.
	 * 
	 * @return The {@link MethodContext}.
	 */
	public MethodContext getMethodContext() {
		return methodContext;
	}

	/**
	 * Returns the instance of the {@link WeldContainer}.
	 * 
	 * @return The {@link WeldContainer}.
	 */
	public WeldContainer getWeldContainer() {
		return weldContainer;
	}

}
