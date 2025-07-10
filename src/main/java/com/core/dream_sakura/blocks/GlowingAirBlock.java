// package com.core.dream_sakura.blocks;

// import javax.annotation.Nonnull;

// import net.minecraft.core.BlockPos;
// import net.minecraft.world.level.BlockGetter;
// import net.minecraft.world.level.block.AirBlock;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.state.BlockState;
// import net.minecraft.world.level.block.state.StateDefinition.Builder;
// import net.minecraft.world.level.block.state.properties.IntegerProperty;;


// public class GlowingAirBlock extends AirBlock {
//     // 光亮等级
//     public static final IntegerProperty LIGHT_LEVEL = IntegerProperty.create("light_level", 0, 15);

//     public GlowingAirBlock(Properties properties) {
//         super(properties);
//         this.registerDefaultState(this.stateDefinition.any().setValue(LIGHT_LEVEL, 0));
//     }

//     @Override
//     protected void createBlockStateDefinition(@Nonnull Builder<Block, BlockState> builder) {
//         builder.add(LIGHT_LEVEL);
//     }

//     @Override
//     public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
//         return state.getValue(LIGHT_LEVEL);
//     }
// }
