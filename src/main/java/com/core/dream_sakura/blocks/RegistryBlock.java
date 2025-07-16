package com.core.dream_sakura.blocks;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBlock {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, dream_sakura.MODID);

    //#region crystal系列
    public static final RegistryObject<Block> CRYSTAL = BLOCKS.register(
        "crystal", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false) // 不阻塞视觉
                .isSuffocating((state, world, pos) -> false)  // 不会令玩家窒息
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_BLACK = BLOCKS.register(
        "crystal_black", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_BLUE = BLOCKS.register(
        "crystal_blue", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_BROWN = BLOCKS.register(
        "crystal_brown", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_CYAN = BLOCKS.register(
        "crystal_cyan", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_GRAY = BLOCKS.register(
        "crystal_gray", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_GREEN = BLOCKS.register(
        "crystal_green", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_LIGHT_BLUE = BLOCKS.register(
        "crystal_light_blue", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_LIGHT_GRAY = BLOCKS.register(
        "crystal_light_gray", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_LIME = BLOCKS.register(
        "crystal_lime", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_MAGENTA = BLOCKS.register(
        "crystal_magenta", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_ORANGE = BLOCKS.register(
        "crystal_orange", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_PINK = BLOCKS.register(
        "crystal_pink", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_PURPLE = BLOCKS.register(
        "crystal_purple", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_RED = BLOCKS.register(
        "crystal_red", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_WHITE = BLOCKS.register(
        "crystal_white", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );

    public static final RegistryObject<Block> CRYSTAL_YELLOW = BLOCKS.register(
        "crystal_yellow", 
        ()-> new AnimatedBlock(
            BlockBehaviour.Properties.of()
                .noOcclusion()
                .isViewBlocking((state, world, pos) -> false)
                .isSuffocating((state, world, pos) -> false)
                .lightLevel(state -> 7)
        )
    );
    //#endregion

}
