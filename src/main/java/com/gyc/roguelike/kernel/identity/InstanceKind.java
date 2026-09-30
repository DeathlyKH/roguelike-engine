package com.gyc.roguelike.kernel.identity;

import java.util.Objects;

/**
 * Identifies a runtime instance category.
 *
 * <p>The kernel does not define concrete kinds. Domains provide them,
 * such as "battle.entity" or "expedition.collectible".</p>
 *
 * @param <T> the runtime instance type
 */
public record InstanceKind<T>(String value) {

    public InstanceKind {
        Objects.requireNonNull(value, "value");

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    "InstanceKind value cannot be blank"
            );
        }

        if (!value.equals(value.trim())) {
            throw new IllegalArgumentException(
                    "InstanceKind value cannot have leading or trailing whitespace"
            );
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
