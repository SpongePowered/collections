/*
 * This file is part of collections, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.collections.test.multimap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spongepowered.collections.inheritance.ArrayListMultimap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListMultimapTest {

    @Test
    public void testCreation() {
        final ArrayListMultimap<String, String> map = new ArrayListMultimap<>();
        Assertions.assertTrue(map.isEmpty());
        Assertions.assertEquals(0, map.size());
    }

    @Test
    public void testInsertion() {
        final String key = "key";
        final String value = "value";
        final ArrayListMultimap<String, String> map = new ArrayListMultimap<>();
        Assertions.assertTrue(map.put(key, value));
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());
    }

    @Test
    public void testRetrieval() {
        final String key = "key";
        final String value = "value";
        final ArrayListMultimap<String, String> map = new ArrayListMultimap<>();
        Assertions.assertTrue(map.put(key, value));
        final List<String> values = map.get(key);
        Assertions.assertFalse(values.isEmpty());
        Assertions.assertEquals(1, values.size());
    }

    @Test
    public void testMultiInsertion() {
        final String key = "key";
        final String value = "value";
        final ArrayListMultimap<String, String> map = new ArrayListMultimap<>();
        Assertions.assertTrue(map.put(key, value));
        final List<String> values = map.get(key);
        final String second = "second";
        Assertions.assertTrue(map.put(key, second));
        Assertions.assertFalse(values.isEmpty());
        Assertions.assertEquals(2, values.size());
    }

    @Test
    public void testRemoval() {
        final String key = "key";
        final String value = "value";
        final ArrayListMultimap<String, String> map = new ArrayListMultimap<>();
        Assertions.assertTrue(map.put(key, value));
        Assertions.assertEquals(Collections.singletonList(value), map.remove(key));
    }

}
