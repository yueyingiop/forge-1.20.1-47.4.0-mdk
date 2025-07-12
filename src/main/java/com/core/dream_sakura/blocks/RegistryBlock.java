package com.core.dream_sakura.blocks;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, dream_sakura.MODID);

    public static final RegistryObject<Block> CRYSTAL = BLOCKS.register(
        "crystal", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()  // 允许透视
                .isViewBlocking((state, world, pos) -> false) // 不阻塞视觉
                .isSuffocating((state, world, pos) -> false)  // 不会令玩家窒息
                .lightLevel(state -> 7)
        )
    );
}
