package net.chemthunder.thaumaturgy.impl.cca.entity;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class GlowingEntityComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<GlowingEntityComponent> KEY = ComponentRegistry.getOrCreate(Thaumaturgy.id("glowing"), GlowingEntityComponent.class);
    private final LivingEntity living;
    public int glowTicks = 0;

    public GlowingEntityComponent(LivingEntity living) {
        this.living = living;
    }

    public void sync() {
        KEY.sync(living);
    }

    public void tick() {
        if (glowTicks > 0) {
            glowTicks--;
            living.setGlowing(true);
            if (glowTicks == 0) {
                living.setGlowing(false);
                sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.glowTicks = nbtCompound.getInt("glowTicks");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("glowTicks", glowTicks);
    }
}
