package net.chemthunder.thaumaturgy.impl.block.entity;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class InterceptorBlockEntity extends BlockEntity {
    public InterceptorBlockEntity(BlockPos pos, BlockState state) {
        super(ThaumaturgyBlockEntities.INTERCEPTOR, pos, state);
    }
}
