package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public interface ThaumaturgyDataComponents {
    ComponentType<ItemStack> HELD_ITEM = create("held_item", builder -> builder.codec(ItemStack.CODEC));

    static <T> ComponentType<T> create(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Thaumaturgy.id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    static void init() {
        //
    }
}
