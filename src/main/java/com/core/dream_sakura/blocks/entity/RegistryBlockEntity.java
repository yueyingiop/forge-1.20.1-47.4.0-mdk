package com.core.dream_sakura.blocks.entity;

import java.util.Arrays;
import java.util.List;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.blocks.RegistryBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, dream_sakura.MODID);



    // 获取所有水晶方块的RegistryObject列表
    private static final List<RegistryObject<Block>> CRYSTAL_BLOCKS = Arrays.asList(
        RegistryBlock.CRYSTAL,
        RegistryBlock.CRYSTAL_BLACK,
        RegistryBlock.CRYSTAL_BLUE,
        RegistryBlock.CRYSTAL_BROWN,
        RegistryBlock.CRYSTAL_CYAN,
        RegistryBlock.CRYSTAL_GRAY,
        RegistryBlock.CRYSTAL_GREEN,
        RegistryBlock.CRYSTAL_LIGHT_BLUE,
        RegistryBlock.CRYSTAL_LIGHT_GRAY,
        RegistryBlock.CRYSTAL_LIME,
        RegistryBlock.CRYSTAL_MAGENTA,
        RegistryBlock.CRYSTAL_ORANGE,
        RegistryBlock.CRYSTAL_PINK,
        RegistryBlock.CRYSTAL_PURPLE,
        RegistryBlock.CRYSTAL_RED,
        RegistryBlock.CRYSTAL_WHITE,
        RegistryBlock.CRYSTAL_YELLOW
    );

    // 统一注册BlockEntityType
    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_ENTITY = 
        BLOCK_ENTITY_TYPES.register(
            "crystal_entity",
            () -> BlockEntityType.Builder.of(
                AnimatedBlockEntity::new,
                CRYSTAL_BLOCKS.stream().map(RegistryObject::get).toArray(Block[]::new)
            ).build(null)
        );


/*     public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_BLACK_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity1",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_BLACK.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_BLUE_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity2",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_BLUE.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_BROWN_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity3",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_BROWN.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_CYAN_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity4",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_CYAN.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_GRAY_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity5",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_GRAY.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_GREEN_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity6",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_GREEN.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_LIGHT_BLUE_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity7",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_LIGHT_BLUE.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_LIGHT_GRAY_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity8",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_LIGHT_GRAY.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_LIME_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity9",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_LIME.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_MAGENTA_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity10",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_MAGENTA.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_ORANGE_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity11",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_ORANGE.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_PINK_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity12",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_PINK.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_PURPLE_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity13",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_PURPLE.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_RED_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity14",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_RED.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_WHITE_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity15",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_WHITE.get()
        ).build(null)
    );

    public static final RegistryObject<BlockEntityType<AnimatedBlockEntity>> CRYSTAL_YELLOW_ENTITY = BLOCK_ENTITY_TYPES.register(
        "crystal_entity16",
        () -> BlockEntityType.Builder.of(
            AnimatedBlockEntity::new,
            RegistryBlock.CRYSTAL_YELLOW.get()
        ).build(null)
    ); */

}
