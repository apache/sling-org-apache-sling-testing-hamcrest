/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.hamcrest.matchers;

import org.apache.sling.api.resource.Resource;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher which matches whenever the type of the given resource is equal to the type in the constructor (optionally allowing also sub types).
 */
public class ResourceTypeMatcher extends TypeSafeMatcher<Resource> {

    private final String type;
    private final boolean allowSubtypes;

    public ResourceTypeMatcher(String type, boolean allowSubtypes) {
        this.type = type;
        this.allowSubtypes = allowSubtypes;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Resource with type ").appendValue(type);
    }

    @Override
    protected boolean matchesSafely(Resource resource) {
        if (allowSubtypes) {
            return resource.isResourceType(type);
        } else {
            return type.equals(resource.getResourceType());
        }
    }

    @Override
    protected void describeMismatchSafely(Resource resource, Description mismatchDescription) {
        mismatchDescription.appendText("was Resource with type ").appendValue(resource.getResourceType()).appendText(" (resource: ").appendValue(resource).appendText(")");
    }

}
