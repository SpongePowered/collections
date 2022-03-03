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
package org.spongepowered.collections.inheritance;

import org.spongepowered.collections.multimap.ListMultimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ArrayListMultimap<K, V> implements ListMultimap.Mutable<K, V> {

    private final Map<K, List<V>> map = new HashMap<>();

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    public List<V> get(final K key) {
        return Collections.unmodifiableList(this.map.getOrDefault(Objects.requireNonNull(key, "key"), Collections.emptyList()));
    }


    @Override
    public boolean put(final K key, final V value) {
        final List<V> values = this.map.computeIfAbsent(Objects.requireNonNull(key, "key"), k -> new ArrayList<>());
        return values.add(Objects.requireNonNull(value, "value"));
    }

    @Override
    public Collection<V> remove(final K key) {
        final List<V> values = this.map.remove(key);
        if (values == null) {
            return Collections.emptyList();
        }
        return values;
    }
}
