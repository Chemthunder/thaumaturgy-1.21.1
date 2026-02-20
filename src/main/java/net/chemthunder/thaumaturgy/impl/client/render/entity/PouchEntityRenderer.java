package net.chemthunder.thaumaturgy.impl.client.render.entity;

import net.chemthunder.thaumaturgy.impl.entity.PouchEntity;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyDataComponents;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class PouchEntityRenderer extends EntityRenderer<PouchEntity> {
    public PouchEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public Identifier getTexture(PouchEntity entity) {
        return null;
    }

    public void render(PouchEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack stack = new ItemStack(ThaumaturgyItems.POUCH);
        stack.set(ThaumaturgyDataComponents.HELD_ITEM, new ItemStack(Items.POPPY));
        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();

        matrices.push();

        matrices.multiply(this.dispatcher.getRotation());
        renderer.renderItem(stack,
                ModelTransformationMode.GROUND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                entity.getId()
        );

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
