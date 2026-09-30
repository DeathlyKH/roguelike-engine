package com.gyc.roguelike.kernel.identity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityModelTest {

    @Test
    void sameLocalIdInDifferentScopesShouldNotCollide() {
        RuntimeScopeId runScope =
                RuntimeScopeId.root("run", 0);

        RuntimeScopeId battleScope =
                runScope
                        .child("encounter", 0)
                        .child("battle", 0);

        InstanceKind<Object> kind =
                new InstanceKind<>("test.instance");

        InstanceRef<Object> runInstance =
                new InstanceRef<>(
                        runScope,
                        kind,
                        new LocalInstanceId<>(3)
                );

        InstanceRef<Object> battleInstance =
                new InstanceRef<>(
                        battleScope,
                        kind,
                        new LocalInstanceId<>(3)
                );

        assertNotEquals(runInstance, battleInstance);
    }

    @Test
    void sameLocalIdInDifferentKindsShouldNotCollide() {
        RuntimeScopeId scope =
                RuntimeScopeId.root("run", 0);

        InstanceKind<Object> collectibleKind =
                new InstanceKind<>("expedition.collectible");

        InstanceKind<Object> effectKind =
                new InstanceKind<>("battle.effect");

        InstanceRef<Object> collectible =
                new InstanceRef<>(
                        scope,
                        collectibleKind,
                        new LocalInstanceId<>(3)
                );

        InstanceRef<Object> effect =
                new InstanceRef<>(
                        scope,
                        effectKind,
                        new LocalInstanceId<>(3)
                );

        assertNotEquals(collectible, effect);
    }

    @Test
    void allocatorShouldProduceDeterministicSequentialReferences() {
        RuntimeScopeId scope =
                RuntimeScopeId.root("run", 0)
                        .child("encounter", 2)
                        .child("battle", 0);

        InstanceKind<Object> kind =
                new InstanceKind<>("battle.entity");

        IdAllocator<Object> first =
                new IdAllocator<>(scope, kind);

        IdAllocator<Object> second =
                new IdAllocator<>(scope, kind);

        assertEquals(first.next(), second.next());
        assertEquals(first.next(), second.next());
        assertEquals(first.next(), second.next());
    }

    @Test
    void childScopeShouldNotModifyParentScope() {
        RuntimeScopeId parent =
                RuntimeScopeId.root("run", 0);

        RuntimeScopeId child =
                parent.child("encounter", 5);

        assertEquals(
                "run:0",
                parent.toString()
        );

        assertEquals(
                "run:0/encounter:5",
                child.toString()
        );
    }

    @Test
    void invalidIdentityValuesShouldBeRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DefinitionId<Object>("   ")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new InstanceKind<Object>(" ")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new LocalInstanceId<Object>(-1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> RuntimeScopeId.root("run", -1)
        );
    }
}
