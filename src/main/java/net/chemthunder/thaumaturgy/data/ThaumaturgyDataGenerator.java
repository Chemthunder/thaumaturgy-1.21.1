package net.chemthunder.thaumaturgy.data;

import net.chemthunder.thaumaturgy.data.data.ThaumaturgyItemTagGen;
import net.chemthunder.thaumaturgy.data.resources.ThaumaturgyLangGen;
import net.chemthunder.thaumaturgy.data.resources.ThaumaturgyModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ThaumaturgyDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ThaumaturgyModelGen::new);
        pack.addProvider(ThaumaturgyItemTagGen::new);
        pack.addProvider(ThaumaturgyLangGen::new);
	}
}
