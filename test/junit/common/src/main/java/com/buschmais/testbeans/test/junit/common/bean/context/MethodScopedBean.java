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
package com.buschmais.testbeans.test.junit.common.bean.context;

import com.buschmais.testbeans.junit.scope.MethodScoped;
import com.buschmais.testbeans.test.common.bean.AbstractScopedBean;


/**
 * A method scoped bean.
 * 
 * @author dirk.mahler
 */
@MethodScoped
public class MethodScopedBean extends AbstractScopedBean {
}
