package com.buschmais.testbeans.junit.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.NormalScope;

/**
 * Specifies that a bean is class scoped.
 * 
 * @author dirk.mahler
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE, ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
@NormalScope
@Inherited
public @interface ClassScoped {
}