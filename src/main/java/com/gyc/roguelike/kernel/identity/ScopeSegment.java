package com.gyc.roguelike.kernel.identity;

import java.util.Objects;

/**
 * One segment of a hierarchical runtime scope path.
 */
public record ScopeSegment(
        String kind,
        long value
) {

    public ScopeSegment {
        Objects.requireNonNull(kind, "kind");

        if (kind.isBlank()) {
            throw new IllegalArgumentException(
                    "Scope kind cannot be blank"
            );
        }

        if (!kind.equals(kind.trim())) {
            throw new IllegalArgumentException(
                    "Scope kind cannot have leading or trailing whitespace"
            );
        }

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Scope value cannot be negative"
            );
        }
    }

    @Override
    public String toString() {
        return kind + ":" + value;
    }
}
