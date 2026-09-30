package com.gyc.roguelike.kernel.source;

/**
 * Identifies the immediate semantic source of a rule, request,
 * effect or event.
 */
public sealed interface SourceRef
        permits InstanceSourceRef,
        DefinitionSourceRef,
        SystemSourceRef {
}
