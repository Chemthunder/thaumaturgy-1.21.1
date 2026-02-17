package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.block.entity.InterceptorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public interface ThaumaturgyBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap<>();

    BlockEntityType<InterceptorBlockEntity> INTERCEPTOR = create("interceptor", FabricBlockEntityTypeBuilder
            .create(InterceptorBlockEntity::new)
            .addBlocks(ThaumaturgyBlocks.INTERCEPTOR)
            .build());

    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, Thaumaturgy.id(name));
        return blockEntity;
    }

    static void init() {
        BLOCK_ENTITIES.keySet().forEach(blockEntity -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntity), blockEntity);
        });
    }

    static void clientInit() {
      //  setupBlockEntityRenderers();
    }

    private static void setupBlockEntityRenderers() {
       // BlockEntityRendererFactories.register(CORNERSTONE, context -> new CornerstoneBlockEntityRenderer());
    }
}
