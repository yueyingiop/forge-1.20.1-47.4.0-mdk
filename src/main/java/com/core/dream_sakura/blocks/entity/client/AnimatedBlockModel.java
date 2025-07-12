package com.core.dream_sakura.blocks.entity.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.blocks.entity.AnimatedBlockEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {

    private String getBlockId(AnimatedBlockEntity animatable) {
        Block block = animatable.getBlockState().getBlock();
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    @Override
    public ResourceLocation getModelResource(AnimatedBlockEntity animatable) {
        String blockId = getBlockId(animatable);
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "geo/"+blockId+".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockEntity animatable) {
        String blockId = getBlockId(animatable);
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "textures/block/"+blockId+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockEntity animatable) {
        String blockId = getBlockId(animatable);
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "animations/"+blockId+".animation.json");
    }

}
