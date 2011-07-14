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
package com.buschmais.testbeans.container.weldse;

import javax.enterprise.inject.spi.Extension;

import com.buschmais.testbeans.framework.Container;
import com.buschmais.testbeans.framework.context.AbstractTestExtension;

/**
 * Implementation of {@link Extension} as defined by JSR-299 which registers the
 * customs contexts controlled by the {@link WeldSEContainer}.
 * 
 * @author dirk.mahler
 */
public class WeldSETestExtension extends AbstractTestExtension {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Container getContainer() {
		return WeldSEContainer.getInstance();
	}
}
