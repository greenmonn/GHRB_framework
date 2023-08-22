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
package org.assertj.core.api.chararray;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.core.test.CharArrays.arrayOf;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.CharArrayAssert;
import org.assertj.core.api.CharArrayAssertBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link CharArrayAssert#containsSubsequence(Character[])}</code>.
 *
 * @author Lucero Garcia
 */
class CharArrayAssert_containsSubsequence_with_Character_array_Test extends CharArrayAssertBaseTest {

  @Test
  void should_fail_if_values_is_null() {
    // GIVEN
    Character[] subsequence = null;
    // WHEN
    Throwable thrown = catchThrowable(() -> assertions.containsSubsequence(subsequence));
    // THEN
    then(thrown).isInstanceOf(NullPointerException.class)
                .hasMessage(shouldNotBeNull("subsequence").create());
  }

  @Override
  protected CharArrayAssert invoke_api_method() {
    return assertions.containsSubsequence(new Character[] { 'a', 'b', 'c' });
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertContainsSubsequence(getInfo(assertions), getActual(assertions), arrayOf('a', 'b', 'c'));
  }

}
