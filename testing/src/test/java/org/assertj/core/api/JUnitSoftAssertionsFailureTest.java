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
package org.assertj.core.api;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.assertj.core.test.ErrorMessagesForTest.shouldBeEqualMessage;
import static org.assertj.core.util.Lists.list;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runners.model.Statement;
import org.opentest4j.MultipleFailuresError;

class JUnitSoftAssertionsFailureTest {

  private final JUnitSoftAssertions softly = new JUnitSoftAssertions();

  @Test
  void should_report_all_errors() {
    // GIVEN
    softly.assertThat(1).isEqualTo(1);
    softly.assertThat(1).isEqualTo(2);
    softly.assertThat(list(1, 2)).containsOnly(1, 3);
    // WHEN simulating the rule
    MultipleFailuresError multipleFailuresError = catchThrowableOfType(() -> softly.apply(mock(Statement.class), null).evaluate(),
                                                                       MultipleFailuresError.class);
    // THEN
    List<Throwable> failures = multipleFailuresError.getFailures();
    assertThat(failures).hasSize(2);
    assertThat(failures.get(0)).hasMessageStartingWith(shouldBeEqualMessage("1", "2"));
    assertThat(failures.get(1)).hasMessageStartingWith(format("%n" +
                                                              "Expecting ArrayList:%n" +
                                                              "  [1, 2]%n" +
                                                              "to contain only:%n" +
                                                              "  [1, 3]%n" +
                                                              "element(s) not found:%n" +
                                                              "  [3]%n" +
                                                              "and element(s) not expected:%n" +
                                                              "  [2]%n"));
  }

}
