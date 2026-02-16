package net.chemthunder.thaumaturgy.data.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems.*;

public class ThaumaturgyLangGen extends FabricLanguageProvider {
    public ThaumaturgyLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        // items
        translationBuilder.add(POUCH, "Pouch");



        // misc
        translationBuilder.add("pouch.fail_load", "This item cannot be used for Pouches!");
    }
}
