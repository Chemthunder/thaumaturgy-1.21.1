package net.chemthunder.thaumaturgy.data.data;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ThaumaturgyBlockTagGen extends FabricTagProvider.BlockTagProvider {
    public ThaumaturgyBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ThaumaturgyBlocks.INTERCEPTOR)
                .setReplace(false);
    }
}
