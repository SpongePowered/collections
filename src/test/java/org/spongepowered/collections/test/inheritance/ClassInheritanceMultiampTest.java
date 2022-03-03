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
package org.spongepowered.collections.test.inheritance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.spongepowered.collections.inheritance.ClassInheritanceMultimap;

import java.util.Arrays;
import java.util.List;

public class ClassInheritanceMultiampTest {

    @Test
    public void testCreation() {
        final ClassInheritanceMultimap<DemoInterface, String> map = ClassInheritanceMultimap.create(DemoInterface.class);
        final String value = "demo";
        final String second = "second";
        Assertions.assertTrue(map.put(DemoInterface.class, value));
        Assertions.assertTrue(map.put(DemoInterface.class, second));
    }

    @Test
    public void testRetrieval() {
        final ClassInheritanceMultimap<DemoInterface, String> map = ClassInheritanceMultimap.create(DemoInterface.class);
        final String value = "demo";
        final String second = "second";
        Assertions.assertTrue(map.put(DemoInterface.class, value));
        Assertions.assertTrue(map.put(DemoInterface.class, second));
        final List<String> actual = map.get(DemoInterface.class);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(Arrays.asList(value, second), actual);
    }


    @Test
    public void testNestedRetrieval() {
        final ClassInheritanceMultimap<DemoInterface, String> map = ClassInheritanceMultimap.create(DemoInterface.class);
        final String value = "demo";
        final String second = "second";
        Assertions.assertTrue(map.put(DemoInterface.class, value));
        Assertions.assertTrue(map.put(DemoInterface.class, second));
        final String parent = "parent";
        Assertions.assertTrue(map.put(Parent.class, parent));
        final List<String> actual = map.get(Parent.class);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(Arrays.asList(parent, value, second), actual);
    }

    @Test
    public void testIndirectRetrieval() {
        final ClassInheritanceMultimap<DemoInterface, String> map = ClassInheritanceMultimap.create(DemoInterface.class);
        final String value = "demo";
        final String second = "second";
        {
            Assertions.assertTrue(map.put(DemoInterface.class, value));
            Assertions.assertTrue(map.put(DemoInterface.class, second));
        }

        final String parent = "parent";
        {
            Assertions.assertTrue(map.put(Parent.class, parent));
        }

        final String another = "another";
        {
            Assertions.assertTrue(map.put(Parent.Another.class, another));
        }

        final String child = "child";
        {
            Assertions.assertTrue(map.put(Parent.ThirdChild.class, child));
        }

        final List<String> actual = map.get(Parent.ThirdChild.class);
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(Arrays.asList(child, another, parent, value, second), actual);

        final List<String> parentValues = map.get(Parent.class);
        Assertions.assertFalse(parentValues.isEmpty());
        Assertions.assertEquals(Arrays.asList(parent, value, second), parentValues);

        final List<String> anotherValues = map.get(Parent.Another.class);
        Assertions.assertFalse(anotherValues.isEmpty());
        Assertions.assertEquals(Arrays.asList(another, value, second), anotherValues);
    }

    @Test
    public void testRemovals() {
        final ClassInheritanceMultimap<DemoInterface, String> map = ClassInheritanceMultimap.create(DemoInterface.class);
        final String value = "demo";
        final String second = "second";
        Assertions.assertTrue(map.put(DemoInterface.class, value));
        Assertions.assertTrue(map.put(DemoInterface.class, second));
        Assertions.assertEquals(Arrays.asList(value, second), map.remove(DemoInterface.class));
    }
}
