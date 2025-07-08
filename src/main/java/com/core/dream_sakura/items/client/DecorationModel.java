package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.items.DecorationItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DecorationModel extends GeoModel<DecorationItem> {

    @Override
    public ResourceLocation getModelResource(DecorationItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "geo/"+itemId+".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DecorationItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "textures/item/"+itemId+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(DecorationItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "animations/"+itemId+".animation.json");
    }
    
}
