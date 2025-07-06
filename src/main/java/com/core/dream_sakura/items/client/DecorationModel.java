package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.items.DecorationItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DecorationModel extends GeoModel<DecorationItem> {

    @Override
    public ResourceLocation getModelResource(DecorationItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "geo/dream_finale.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DecorationItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "textures/item/dream_finale.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DecorationItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "animations/dream_finale.animation.json");
    }
    
}
