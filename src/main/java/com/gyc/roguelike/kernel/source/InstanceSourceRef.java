package com.gyc.roguelike.kernel.source;

import com.gyc.roguelike.kernel.identity.InstanceRef;

import java.util.Objects;

/**
 * A source represented by a concrete runtime instance.
 */
public record InstanceSourceRef(
        InstanceRef<?> instance
) implements SourceRef {

    public InstanceSourceRef {
        Objects.requireNonNull(instance, "instance");
    }

    @Override
    public String toString() {
        return "instance:" + instance;
    }
}
