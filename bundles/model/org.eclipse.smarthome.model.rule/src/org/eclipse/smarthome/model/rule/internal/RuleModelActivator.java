/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.model.rule.internal;

import org.eclipse.smarthome.core.scriptengine.ScriptEngine;
import org.eclipse.smarthome.model.core.ModelRepository;
import org.eclipse.smarthome.model.rule.RulesStandaloneSetup;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Extension of the default OSGi bundle activator
 * 
 * @author Kai Kreuzer - Initial contribution and API
 */
public class RuleModelActivator implements BundleActivator {

	private final static Logger logger = LoggerFactory.getLogger(RuleModelActivator.class);

	public static ServiceTracker<ModelRepository, ModelRepository> modelRepositoryTracker;
	public static ServiceTracker<ScriptEngine, ScriptEngine> scriptEngineTracker;

	public void start(BundleContext bc) throws Exception {
		modelRepositoryTracker = new ServiceTracker<ModelRepository, ModelRepository>(bc, ModelRepository.class, null);
		modelRepositoryTracker.open();

		scriptEngineTracker = new ServiceTracker<ScriptEngine, ScriptEngine>(bc, ScriptEngine.class, null);
		scriptEngineTracker.open();		

		RulesStandaloneSetup.doSetup();
		logger.debug("Registered 'rules' configuration parser");	
	}

	public void stop(BundleContext context) throws Exception {
	}

}
