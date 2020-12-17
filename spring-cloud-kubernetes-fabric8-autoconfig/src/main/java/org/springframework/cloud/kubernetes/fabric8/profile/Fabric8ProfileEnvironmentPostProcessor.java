/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.kubernetes.fabric8.profile;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;

import org.springframework.cloud.kubernetes.commons.profile.AbstractKubernetesProfileEnvironmentPostProcessor;
import org.springframework.cloud.kubernetes.fabric8.Fabric8PodUtils;
import org.springframework.core.env.Environment;

public class Fabric8ProfileEnvironmentPostProcessor extends AbstractKubernetesProfileEnvironmentPostProcessor {

	protected static final String KUBERNETES_SERVICE_ENV_VAR = "KUBERNETES_SERVICE_HOST";

	@Override
	protected boolean isInsideKubernetes(Environment environment) {
		try (DefaultKubernetesClient client = new DefaultKubernetesClient()) {
			final Fabric8PodUtils podUtils = new Fabric8PodUtils(client);
			return environment.containsProperty(KUBERNETES_SERVICE_ENV_VAR) || podUtils.isInsideKubernetes();
		}
	}

}
