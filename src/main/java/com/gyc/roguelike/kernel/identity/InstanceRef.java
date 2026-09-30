package com.gyc.roguelike.kernel.identity;

import java.util.Objects;

/**
 * Fully identifies a runtime instance.
 *
 * <p>Runtime identity consists of:</p>
 *
 * <pre>
 * scope + instance kind + local ID
 * </pre>
 *
 * @param <T> the runtime instance type
 */
public record InstanceRef<T>(
        RuntimeScopeId scope,
        InstanceKind<T> kind,
        LocalInstanceId<T> localId
) {

    public InstanceRef {
        Objects.requireNonNull(scope, "scope");
        Objects.requireNonNull(kind, "kind");
        Objects.requireNonNull(localId, "localId");
    }

    @Override
    public String toString() {
        return scope
                + "/"
                + kind
                + ":"
                + localId;
    }
}