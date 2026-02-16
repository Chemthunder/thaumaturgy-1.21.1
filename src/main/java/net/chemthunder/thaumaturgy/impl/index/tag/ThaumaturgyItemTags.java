package net.chemthunder.thaumaturgy.impl.index.tag;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ThaumaturgyItemTags {
    TagKey<Item> ACCEPTABLE = create("acceptable");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Thaumaturgy.id(id));
    }
}
