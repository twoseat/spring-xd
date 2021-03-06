/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xd.dirt.rest;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.xd.dirt.job.StepExecutionInfo;
import org.springframework.xd.rest.domain.StepExecutionInfoResource;


/**
 * Knows how to build a REST resource out of our domain model {@link StepExecutionInfo}.
 * 
 * @author Gunnar Hillert
 * @since 1.0
 */
public class StepExecutionInfoResourceAssembler extends
		ResourceAssemblerSupport<StepExecutionInfo, StepExecutionInfoResource> {

	public StepExecutionInfoResourceAssembler() {
		super(BatchStepExecutionsController.class, StepExecutionInfoResource.class);
	}

	@Override
	public StepExecutionInfoResource toResource(StepExecutionInfo entity) {
		return createResourceWithId(entity.getId(), entity, new Object[] { entity.getJobExecutionId() });
	}

	@Override
	protected StepExecutionInfoResource instantiateResource(StepExecutionInfo entity) {
		return new StepExecutionInfoResource(entity.getJobExecutionId(), entity.getStepExecution(), entity.getStepType());
	}
}
