package com.gyc.roguelike.kernel.identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Identifies a deterministic hierarchical runtime scope.
 */
public record RuntimeScopeId(List<ScopeSegment> segments) {

    public RuntimeScopeId {
        Objects.requireNonNull(segments, "segments");

        if (segments.isEmpty()) {
            throw new IllegalArgumentException(
                    "RuntimeScopeId must contain at least one segment"
            );
        }

        segments = List.copyOf(segments);
    }

    public static RuntimeScopeId root(
            String kind,
            long value
    ) {
        return new RuntimeScopeId(
                List.of(new ScopeSegment(kind, value))
        );
    }

    public RuntimeScopeId child(
            String kind,
            long value
    ) {
        List<ScopeSegment> childSegments =
                new ArrayList<>(segments);

        childSegments.add(
                new ScopeSegment(kind, value)
        );

        return new RuntimeScopeId(childSegments);
    }

    @Override
    public String toString() {
        return segments.stream()
                .map(ScopeSegment::toString)
                .collect(Collectors.joining("/"));
    }
}
