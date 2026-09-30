package com.gyc.roguelike.kernel.identity;

import java.util.Objects;

/**
 * Identifies a stable game-content definition.
 *
 * @param <T> the definition type identified by this ID
 */
public record DefinitionId<T>(String value) {

    public DefinitionId {
        Objects.requireNonNull(value, "value");

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    "DefinitionId value cannot be blank"
            );
        }

        if (!value.equals(value.trim())) {
            throw new IllegalArgumentException(
                    "DefinitionId value cannot have leading or trailing whitespace"
            );
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
