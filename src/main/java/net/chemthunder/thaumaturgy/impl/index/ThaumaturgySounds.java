package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public interface ThaumaturgySounds {
    SoundEvent POUCH_EXPLODE = create("item.pouch_explode");

    private static SoundEvent create(String name) {
        Identifier id = Thaumaturgy.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    static void init() {
        // Sound Events are Registered Statically
    }
}
