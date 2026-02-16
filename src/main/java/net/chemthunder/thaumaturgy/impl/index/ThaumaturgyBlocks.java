package net.chemthunder.thaumaturgy.impl.index;

import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.block.InterceptorBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public interface ThaumaturgyBlocks {
    Block INTERCEPTOR = createWithItem("interceptor", InterceptorBlock::new, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.LODESTONE), new Item.Settings());

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, Thaumaturgy.id(name), block);
    }

    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, Item.Settings itemSetting) {
        Block block = create(name, factory, settings);
        ThaumaturgyItems.create(name, itemSettings -> new TranslationBlockItem(block, itemSettings), itemSetting);
        return block;
    }

    static void init() {
        //
    }

    static void clientInit() {
        //
    }
}
