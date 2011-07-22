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
package com.buschmais.testbeans.test.junit.weldse.testrule.producer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.buschmais.testbeans.container.weldse.WeldSE;
import com.buschmais.testbeans.framework.container.CdiContainer;
import com.buschmais.testbeans.junit.TestBeansSuite;

/**
 * Defines a suite using {@link TestBeansSuite}.
 * 
 * @author dirk.mahler
 */
@SuiteClasses(com.buschmais.testbeans.test.junit.common.testrule.producer.ProducerDisposerTest.class)
@CdiContainer(WeldSE.class)
@RunWith(TestBeansSuite.class)
public class ProducerDisposerTest {
}
