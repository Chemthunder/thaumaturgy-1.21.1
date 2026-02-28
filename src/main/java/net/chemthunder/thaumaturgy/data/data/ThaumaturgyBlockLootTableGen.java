package net.chemthunder.thaumaturgy.data.data;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ThaumaturgyBlockLootTableGen extends FabricBlockLootTableProvider {
    public ThaumaturgyBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        this.addDrop(ThaumaturgyBlocks.INTERCEPTOR);
    }
}
