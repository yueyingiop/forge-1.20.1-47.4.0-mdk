package com.core.dream_sakura.blocks.entity;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.blocks.RegistryBlock;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, dream_sakura.MODID);

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> ANIMATED_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
        "animated_block_entity",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL.get()
        ).build(null)
    );
}
