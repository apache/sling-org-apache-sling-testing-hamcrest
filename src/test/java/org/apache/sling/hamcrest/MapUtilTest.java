/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.hamcrest;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapUtilTest {

    private static final ImmutableMap<String, Object> EXPECTED_MAP =
            ImmutableMap.<String, Object>of("param1", "var1", "param2", 123, "param3", true);

    @Test
    public void testMapObjectVarargs() {
        Map<String, Object> convertedMap = MapUtil.toMap("param1", "var1", "param2", 123, "param3", true);

        assertEquals(EXPECTED_MAP, convertedMap);
    }

    @Test
    public void testMapObjectMap() {
        Map<String, Object> convertedMap = MapUtil.toMap(EXPECTED_MAP);

        assertEquals(EXPECTED_MAP, convertedMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapObjectVarArgs_NotMap() {
        MapUtil.toMap("param1", "var1", "param2", 123, "param3");
    }

    @Test
    public void testMapObjectVarargs_EmptyArgs() {
        Map<String, Object> convertedMap = MapUtil.toMap();

        assertEquals(Collections.emptyMap(), convertedMap);
    }

    @Test
    public void testMapObjectVarargs_Null() {
        Map<String, Object> convertedMap = MapUtil.toMap((Object[]) null);

        assertEquals(Collections.emptyMap(), convertedMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapObjectVarArgs_OddNumberOfArgs() {
        MapUtil.toMap("param1", "var1", "param2", 123, "param3");
    }
}
