package net.chemthunder.thaumaturgy.data.resources;

import net.chemthunder.thaumaturgy.impl.index.data.ThaumaturgyDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
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
        translationBuilder.add(SACRIFICIAL_KNIFE, "Sacrificial Knife");
        translationBuilder.add(FLOWERY_DOLL, "Flowery Doll");
        translationBuilder.add(INTERCEPTOR_ITEM, "Interceptor");


        // misc
        translationBuilder.add("pouch.fail_load", "This item cannot be used for Pouches!");
        translationBuilder.add("hidden.text.hold_alt.1", "Hold down [ALT] to see");
        translationBuilder.add("hidden.text.hold_alt.2", "thaumaturgical properties.");
        translationBuilder.add("thaumaturgy.property", "Property - ");
        translationBuilder.add("itemGroup.thaumaturgy", "Thaumaturgy");

        // damage type
        registerDamageType(translationBuilder,
                ThaumaturgyDamageTypes.SACRIFICE,
                "%1$s bled out",
                "%1$s bled out whilst fighting %2$s wielding %3$s",
                "%1$s bled out %2$s"
        );

        // midnight config
        translationBuilder.add("thaumaturgy.midnightconfig.title", "Title");
        translationBuilder.add("thaumaturgy.midnightconfig.showPronouns", "Show pronouns for ingredients");
    }

    public void registerDamageType(TranslationBuilder builder, RegistryKey<DamageType> registryKey, String normal, String item, String player) {
        String key = "death.attack." + registryKey.getValue().getPath();
        builder.add(key, normal);
        builder.add(key + ".item", item);
        builder.add(key + ".player", player);
    }
}
