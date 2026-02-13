package net.chemthunder.thaumaturgy.impl.cca.world;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class ToLiveEternityWorldEventComponent implements AutoSyncedComponent {
    public static final ComponentKey<ToLiveEternityWorldEventComponent> KEY = ComponentRegistry.getOrCreate(Thaumaturgy.id("eternity"), ToLiveEternityWorldEventComponent.class);
    private final World world;
    public boolean isActive = false;

    public void sync() {
        KEY.sync(this.world);
    }

    public ToLiveEternityWorldEventComponent(World world) {
        this.world = world;
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.isActive = nbtCompound.getBoolean("isActive");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("isActive", isActive);
    }
}
