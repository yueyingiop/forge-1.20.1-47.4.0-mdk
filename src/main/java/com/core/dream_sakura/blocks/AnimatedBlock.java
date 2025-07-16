package com.core.dream_sakura.blocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.core.dream_sakura.blocks.entity.RegistryBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AnimatedBlock extends BaseEntityBlock {
    protected AnimatedBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState state) {
        return RegistryBlockEntity.CRYSTAL_ENTITY.get().create(blockPos, state);
    }

    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

}
