package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.client.render.entity.PouchEntityRenderer;
import net.chemthunder.thaumaturgy.impl.entity.PouchEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ThaumaturgyEntities {
    EntityType<PouchEntity> POUCH = create("pouch", EntityType.Builder.create(
            PouchEntity::new,
            SpawnGroup.MISC
    ).dimensions(0.5F, 0.5F));

    private static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, Thaumaturgy.id(name), builder.build());
    }

    static void init() {
        //
    }

    static void clientInit() {
          EntityRendererRegistry.register(POUCH, PouchEntityRenderer::new);
    }
}
