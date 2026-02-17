package net.chemthunder.thaumaturgy.data;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ThaumaturgyDynamicRegistryGen extends FabricDynamicRegistryProvider {
    public ThaumaturgyDynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE));
    }

    public String getName() {
        return Thaumaturgy.MOD_ID + "_dynamic";
    }
}
