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
package org.spongepowered.collections;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractIterator<T> implements Iterator<T> {

    protected State state = State.Unknown;
    private T next;

    protected enum State {
        Unknown,
        Failed,
        Ready,
        Exhausted;
    }

    @Override
    public final boolean hasNext() {
        if (this.state == State.Failed) {
            throw new IllegalStateException("Failed iterator state");
        }
        switch (this.state) {
            case Exhausted:
                return false;
            case Ready:
                return true;
            default:
        }
        return this.tryToComputeNext();
    }

    @Override
    @Nullable
    public final T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.Unknown;
        final @Nullable T result = this.next;
        this.next = null;
        return result;
    }

    private boolean tryToComputeNext() {
        this.state = State.Failed; // temporary pessimism
        this.next = this.tryComputeNext();
        if (this.state != State.Exhausted) {
            this.state = State.Ready;
            return true;
        }
        return false;
    }

    @Nullable
    protected abstract T tryComputeNext();

    protected final void complete() {
        this.state = State.Exhausted;
    }
}
