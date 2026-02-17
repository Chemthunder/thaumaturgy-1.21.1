package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.block.item.InterceptorBlockItem;
import net.chemthunder.thaumaturgy.impl.item.FloweryDollItem;
import net.chemthunder.thaumaturgy.impl.item.PouchItem;
import net.chemthunder.thaumaturgy.impl.item.SacrificialKnifeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

public interface ThaumaturgyItems {
    Item FLOWERY_DOLL = create("flowery_doll", FloweryDollItem::new, new Item.Settings().maxCount(1));
    Item POUCH = create("pouch", PouchItem::new, new Item.Settings().maxCount(1));
    Item SACRIFICIAL_KNIFE = create("sacrificial_knife", SacrificialKnifeItem::new, new Item.Settings().maxCount(1));


    Item INTERCEPTOR_ITEM = create("interceptor", settings -> new InterceptorBlockItem(ThaumaturgyBlocks.INTERCEPTOR, settings), new Item.Settings());

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, Thaumaturgy.id(name), item);
    }

    static void init() {

    }
}
