package com.gyc.roguelike.kernel.identity;

import java.util.Objects;

/**
 * Deterministically allocates runtime instance references
 * for one instance kind within one runtime scope.
 *
 * @param <T> the runtime instance type
 */
public final class IdAllocator<T> {

    private final RuntimeScopeId scope;
    private final InstanceKind<T> kind;

    private long nextValue;

    public IdAllocator(
            RuntimeScopeId scope,
            InstanceKind<T> kind
    ) {
        this(scope, kind, 0);
    }

    public IdAllocator(
            RuntimeScopeId scope,
            InstanceKind<T> kind,
            long initialValue
    ) {
        this.scope =
                Objects.requireNonNull(scope, "scope");

        this.kind =
                Objects.requireNonNull(kind, "kind");

        if (initialValue < 0) {
            throw new IllegalArgumentException(
                    "Initial ID value cannot be negative"
            );
        }

        this.nextValue = initialValue;
    }

    public InstanceRef<T> next() {
        if (nextValue == Long.MAX_VALUE) {
            throw new IllegalStateException(
                    "Instance ID space exhausted"
            );
        }

        LocalInstanceId<T> localId =
                new LocalInstanceId<>(nextValue++);

        return new InstanceRef<>(
                scope,
                kind,
                localId
        );
    }

    public RuntimeScopeId scope() {
        return scope;
    }

    public InstanceKind<T> kind() {
        return kind;
    }

    public long nextValue() {
        return nextValue;
    }
}