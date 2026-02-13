package net.chemthunder.thaumaturgy.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ThaumaturgyModelGen extends FabricModelProvider {
    public ThaumaturgyModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        //
    }

    public void generateItemModels(ItemModelGenerator generator) {

    }
}
