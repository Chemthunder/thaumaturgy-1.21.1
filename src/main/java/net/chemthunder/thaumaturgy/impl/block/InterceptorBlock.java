package net.chemthunder.thaumaturgy.impl.block;

import com.mojang.serialization.MapCodec;
import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.block.entity.InterceptorBlockEntity;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InterceptorBlock extends BlockWithEntity {
    public static final MapCodec<InterceptorBlock> CODEC = createCodec(InterceptorBlock::new);
    protected MapCodec<? extends BlockWithEntity> getCodec() {return CODEC;}

    public InterceptorBlock(Settings settings) {
        super(settings);
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InterceptorBlockEntity(pos, state);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack mainStack = player.getMainHandStack();
        ItemStack offStack = player.getOffHandStack();
        if (world instanceof ServerWorld serverWorld) {
            if (world.getBlockEntity(pos) instanceof InterceptorBlockEntity be) {
                if (mainStack.isOf(ThaumaturgyItems.SACRIFICIAL_KNIFE) && offStack.isIn(ThaumaturgyItemTags.ACCEPTABLE)) {
                    world.playSound(null, pos, SoundEvents.BLOCK_MANGROVE_ROOTS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    world.playSound(null, pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1, 1);
                    world.playSound(null, pos, SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.BLOCKS, 1, 3);

                    serverWorld.spawnParticles(ParticleTypes.END_ROD,
                            pos.getX() + 0.5f,
                            pos.getY() + 1.5f,
                            pos.getZ() + 0.5f,
                            9,
                            0.02,
                            0.02,
                            0.02,
                            0.03
                    );

                    RitualUtils.RitualVariation variation = RitualUtils.getRitualVariation(offStack);
                    if (!player.isInCreativeMode()) offStack.split(1);
                    if (variation != null) {
                        be.startRitual(world, pos, player, be, variation);
                        Thaumaturgy.LOGGER.info("Ran ritual: {}", variation.asString());
                    }
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
