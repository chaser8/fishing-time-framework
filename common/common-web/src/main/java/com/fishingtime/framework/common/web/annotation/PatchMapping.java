/*
 * Copyright 2002-2016 the original author or authors.
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

package com.fishingtime.framework.common.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * Annotation for mapping HTTP {@code PATCH} requests onto specific handler
 * methods.
 *
 * <p>Specifically, {@code @PatchMapping} is a <em>composed annotation</em> that
 * acts as a shortcut for {@code @RequestMapping(method = RequestMethod.PATCH)}.
 *
 * @author Sam Brannen
 * @since 4.3
 * @see GetMapping
 * @see PostMapping
 * @see PutMapping
 * @see DeleteMapping
 * @see RequestMapping
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.PATCH)
public @interface PatchMapping {

	/**
	 * Alias for {@link RequestMapping#name}.
	 */
	@AliasFor(annotation = RequestMapping.class)
	String name() default "";

	/**
	 * Alias for {@link RequestMapping#value}.
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] value() default {};

	/**
	 * Alias for {@link RequestMapping#path}.
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] path() default {};

	/**
	 * Alias for {@link RequestMapping#params}.
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] params() default {};

	/**
	 * Alias for {@link RequestMapping#headers}.
	 */
	@AliasFor(annotation = RequestMapping.class)
	String[] headers() default {};

    /**
     * Alias for {@link RequestMapping#consumes}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] consumes() default {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE};

    /**
     * Alias for {@link RequestMapping#produces}.
     */
    @AliasFor(annotation = RequestMapping.class)
    String[] produces() default {MediaType.APPLICATION_JSON_UTF8_VALUE};

}
