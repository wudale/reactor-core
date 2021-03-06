/*
 * Copyright (c) 2011-2016 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.core.publisher;

import java.time.Duration;

import org.junit.Test;
import reactor.core.Exceptions;
import reactor.test.StepVerifier;

public class MonoDelayTest {

	Mono<Long> scenario_delayedSource() {
		return Mono.delay(Duration.ofSeconds(4));
	}

	@Test
	public void delayedSource() {
		StepVerifier.withVirtualTime(this::scenario_delayedSource)
		            .thenAwait(Duration.ofSeconds(4))
		            .expectNext(0L)
		            .verifyComplete();
	}

	Mono<Long> scenario_delayedSourceError() {
		return Mono.delay(Duration.ofSeconds(4));
	}

	@Test
	public void delayedSourceError() {
		StepVerifier.withVirtualTime(this::scenario_delayedSourceError, 0L)
		            .thenAwait(Duration.ofSeconds(5))
		            .verifyErrorMatches(Exceptions::isOverflow);
	}
}