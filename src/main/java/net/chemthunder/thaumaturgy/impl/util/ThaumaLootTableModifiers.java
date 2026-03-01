package net.chemthunder.thaumaturgy.impl.util;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

public class ThaumaLootTableModifiers implements LootTableEvents.Modify {
    public static void init() {
        LootTableEvents.MODIFY.register(new ThaumaLootTableModifiers());
    }

    public void modifyLootTable(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) {
        if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0F, 1.0F))
                    .conditionally(RandomChanceLootCondition.builder(0.6F))
                    .with(ItemEntry.builder(ThaumaturgyItems.SACRIFICIAL_KNIFE));

            tableBuilder.pool(poolBuilder);
        }
    }
}