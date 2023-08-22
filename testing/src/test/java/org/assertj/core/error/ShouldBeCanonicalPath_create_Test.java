/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.error;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeCanonicalPath.SHOULD_BE_CANONICAL;
import static org.assertj.core.error.ShouldBeCanonicalPath.shouldBeCanonicalPath;
import static org.mockito.Mockito.mock;

import java.nio.file.Path;

import org.assertj.core.description.TextDescription;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.jupiter.api.Test;

class ShouldBeCanonicalPath_create_Test {

  @Test
  void should_create_error_message() {
    // GIVEN
    final Path actual = mock(Path.class);
    ErrorMessageFactory factory = shouldBeCanonicalPath(actual);
    // WHEN
    String actualMessage = factory.create(new TextDescription("Test"), new StandardRepresentation());
    // THEN
    then(actualMessage).isEqualTo(format("[Test] " + SHOULD_BE_CANONICAL, actual));
  }
}
