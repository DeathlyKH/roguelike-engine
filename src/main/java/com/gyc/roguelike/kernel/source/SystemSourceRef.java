package com.gyc.roguelike.kernel.source;

import com.gyc.roguelike.kernel.identity.InstanceRef;

import java.util.Objects;

/**
 * A source represented by an internal runtime system actor.
 *
 * <p>A system actor is still a real runtime participant and may
 * have state, receive requests, react to events and be affected
 * by rules or effects.</p>
 */
public record SystemSourceRef(
        InstanceRef<?> instance
) implements SourceRef {

    public SystemSourceRef {
        Objects.requireNonNull(instance, "instance");
    }

    @Override
    public String toString() {
        return "system:" + instance;
    }
}
