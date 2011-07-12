package com.buschmais.testbeans.junit.test.bean.producer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Managed {

}
