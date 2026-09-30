package com.gyc.roguelike.kernel.source;

import com.gyc.roguelike.kernel.identity.DefinitionId;

import java.util.Objects;

/**
 * A source represented directly by a stable content definition.
 */
public record DefinitionSourceRef(
        DefinitionId<?> definition
) implements SourceRef {

    public DefinitionSourceRef {
        Objects.requireNonNull(definition, "definition");
    }

    @Override
    public String toString() {
        return "definition:" + definition;
    }
}
