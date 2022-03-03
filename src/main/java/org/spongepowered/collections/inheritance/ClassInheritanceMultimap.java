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
import java.util.List;
import java.util.Objects;

public class ClassInheritanceMultimap<K, V> implements ListMultimap.Mutable<Class<? extends K>, V> {
    private final ListMultimap.Mutable<Class<?>, V> backing = new ArrayListMultimap<>();

    private final Class<K> base;

    public ClassInheritanceMultimap(final Class<K> base) {
        this.base = base;
    }

    public static <NK, NV> ClassInheritanceMultimap<NK, NV> create(final Class<NK> base) {
        return new ClassInheritanceMultimap<>(Objects.requireNonNull(base, "base"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<V> get(final Class<? extends K> key) {
        final List<V> values = new ArrayList<>();
        for (Class<? extends K> clazz = key; clazz != null && (this.base.isAssignableFrom(clazz) || clazz.isInterface()); clazz = (Class<? extends K>) clazz.getSuperclass()) {
            values.addAll(this.backing.get(clazz));
            for (final Class<?> iface : clazz.getInterfaces()) {
                values.addAll(this.backing.get(iface));
            }
        }
        return Collections.unmodifiableList(values);
    }

    @Override
    public boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override
    public int size() {
        return this.backing.size();
    }

    @Override
    public boolean put(final Class<? extends K> key, final V value) {
        return this.backing.put(key, value);
    }

    @Override
    public Collection<V> remove(final Class<? extends K> key) {
        return this.backing.remove(key);
    }
}
