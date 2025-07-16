package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.items.WeaponItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponModel  extends GeoModel<WeaponItem> {
    
    @Override
    public ResourceLocation getModelResource(WeaponItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "geo/weapon/"+itemId+".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "textures/item/weapon/"+itemId+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponItem animatable) {
        String itemId = animatable.getItemId();
        return ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "animations/weapon/"+itemId+".animation.json");
    }
}
