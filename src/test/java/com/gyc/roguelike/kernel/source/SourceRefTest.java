package com.gyc.roguelike.kernel.source;

import com.gyc.roguelike.kernel.identity.IdAllocator;
import com.gyc.roguelike.kernel.identity.InstanceKind;
import com.gyc.roguelike.kernel.identity.RuntimeScopeId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceRefTest {

    @Test
    void differentRuntimeInstancesShouldRemainDifferentSources() {
        RuntimeScopeId scope =
                RuntimeScopeId.root("run", 0)
                        .child("battle", 0);

        InstanceKind<Object> kind =
                new InstanceKind<>("battle.entity");

        IdAllocator<Object> allocator =
                new IdAllocator<>(scope, kind);

        SourceRef first =
                new InstanceSourceRef(allocator.next());

        SourceRef second =
                new InstanceSourceRef(allocator.next());

        assertNotEquals(first, second);
    }

    @Test
    void systemSourceShouldPreserveItsRuntimeIdentity() {
        RuntimeScopeId scope =
                RuntimeScopeId.root("run", 0)
                        .child("battle", 0);

        InstanceKind<Object> systemActorKind =
                new InstanceKind<>("battle.system-actor");

        IdAllocator<Object> allocator =
                new IdAllocator<>(scope, systemActorKind);

        SystemSourceRef source =
                new SystemSourceRef(allocator.next());

        assertEquals(
                "battle.system-actor",
                source.instance().kind().value()
        );
    }
}
