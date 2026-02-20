package net.chemthunder.thaumaturgy.data.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags.ACCEPTABLE;

public class ThaumaturgyItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ThaumaturgyItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ACCEPTABLE)
                .add(Items.POPPY)
                .add(Items.DANDELION)
                .add(Items.PEONY)
                .add(Items.CORNFLOWER)
                .add(Items.ALLIUM)
                .add(Items.WITHER_ROSE)
                .add(Items.TORCHFLOWER)
                .setReplace(true);
    }
}
