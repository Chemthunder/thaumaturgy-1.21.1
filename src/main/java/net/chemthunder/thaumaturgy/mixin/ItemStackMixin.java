package net.chemthunder.thaumaturgy.mixin;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgySounds;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void disableplacing(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());

        if (context.getPlayer() != null) {
            ItemStack offStack = context.getPlayer().getOffHandStack();
            if (state.isOf(ThaumaturgyBlocks.INTERCEPTOR)) {
                if (offStack.isIn(ThaumaturgyItemTags.ACCEPTABLE) && context.getPlayer().getMainHandStack().isOf(ThaumaturgyItems.SACRIFICIAL_KNIFE)) {
                    cir.setReturnValue(ActionResult.SUCCESS_NO_ITEM_USED);
                }
            }
        }
    }
}
