package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import static net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems.POUCH;
import static net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems.SACRIFICIAL_KNIFE;

public interface ThaumaturgyItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Thaumaturgy.id("thaumaturgy"));
    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SACRIFICIAL_KNIFE))
            .displayName(Text.translatable("itemGroup.thaumaturgy").styled(style -> style.withColor(0x1c5b39)))
            .build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, MAIN);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(ThaumaturgyItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(SACRIFICIAL_KNIFE);
        itemGroup.add(POUCH);

        itemGroup.add(ThaumaturgyBlocks.INTERCEPTOR);

        itemGroup.add(Items.POPPY);
        itemGroup.add(Items.CORNFLOWER);
        itemGroup.add(Items.DANDELION);
        itemGroup.add(Items.PEONY);
        itemGroup.add(Items.ALLIUM);
        itemGroup.add(Items.TORCHFLOWER);
        itemGroup.add(Items.WITHER_ROSE);
    }
}
