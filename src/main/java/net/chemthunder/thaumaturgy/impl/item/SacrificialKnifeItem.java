package net.chemthunder.thaumaturgy.impl.item;

import net.chemthunder.thaumaturgy.api.item.ThaumaturgicalItem;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SacrificialKnifeItem extends ThaumaturgicalItem {
    public SacrificialKnifeItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        for (int i = 0; i < 2; i++) {
            tooltip.add(Text.translatable("knife.desc_" + i).formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 2.5F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.3F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        assert player != null;
        if (player.getMainHandStack().isOf(this) && player.getOffHandStack().isOf(Items.RED_DYE)) {
            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.LODESTONE)) {
                context.getWorld().setBlockState(context.getBlockPos(), ThaumaturgyBlocks.INTERCEPTOR.getDefaultState());

                player.getOffHandStack().decrement(1);
                player.swingHand(Hand.MAIN_HAND);

                player.playSoundToPlayer(SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1, 1);
            }
        }
        return super.useOnBlock(context);
    }
}
