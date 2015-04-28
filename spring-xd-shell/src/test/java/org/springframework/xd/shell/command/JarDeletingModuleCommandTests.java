/*
 * Copyright 2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.xd.shell.command;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.xd.module.ModuleType.processor;
import static org.springframework.xd.module.ModuleType.source;
import static org.springframework.xd.module.core.CompositeModule.OPTION_SEPARATOR;
import static org.springframework.xd.shell.command.fixtures.XDMatchers.eventually;
import static org.springframework.xd.shell.command.fixtures.XDMatchers.hasContentsThat;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.junit.ClassRule;
import org.junit.Test;

import org.springframework.shell.core.CommandResult;
import org.springframework.xd.module.ModuleType;
import org.springframework.xd.shell.command.fixtures.HttpSource;
import org.springframework.xd.shell.util.Table;
import org.springframework.xd.shell.util.TableRow;
import org.springframework.xd.test.HostNotWindowsRule;
import org.springframework.xd.test.fixtures.FileSink;

/**
 * Test module commands that require deleting an uploaded module. Such tests fail on Windows due to OS file locking.
 *
 * @author David Turanski
 */
public class JarDeletingModuleCommandTests extends AbstractStreamIntegrationTest {
	@ClassRule
	public static HostNotWindowsRule hostNotWindowsRule = new HostNotWindowsRule();

	@Test
	public void testDeleteUploadedModuleUsedByStream() throws IOException {
		File moduleSource = new File("src/test/resources/spring-xd/xd/modules/processor/siDslModule.jar");
		module().upload("siDslModule2", processor, moduleSource);
		stream().createDontDeploy("foo", "http | siDslModule2 --prefix=foo | log");
		assertFalse(module().delete("siDslModule2", processor));
	}
}
