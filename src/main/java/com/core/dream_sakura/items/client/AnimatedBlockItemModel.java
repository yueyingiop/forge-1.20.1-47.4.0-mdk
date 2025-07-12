package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.items.AnimatedBlockItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockItemModel extends GeoModel<AnimatedBlockItem> {

    @Override
    public ResourceLocation getModelResource(AnimatedBlockItem animatable) {
        String blockId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "geo/"+blockId+".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockItem animatable) {
        String blockId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "textures/block/"+blockId+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockItem animatable) {
        String blockId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "animations/"+blockId+".animation.json");
    }

}
