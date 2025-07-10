package com.core.dream_sakura.blocks;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, dream_sakura.MODID);
    
    // // 注册发光空气方块
    // public static final RegistryObject<Block> GLOWING_AIR = BLOCKS.register(
    //     "glowing_air", 
    //     () -> new GlowingAirBlock(
    //         BlockBehaviour.Properties.of()
    //             .air()
    //             .noCollission()
    //             .noLootTable()
    //             .lightLevel(state -> state.getValue(GlowingAirBlock.LIGHT_LEVEL))
    //     )
            
    // );
}
