package com.decorative_accessories.items.client;

import com.decorative_accessories.decorative_accessories.decorative_accessories;
import com.decorative_accessories.items.DecorationItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DecorationModel extends GeoModel<DecorationItem> {

    @Override
    public ResourceLocation getModelResource(DecorationItem animatable) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getModelResource'");
        return new ResourceLocation(decorative_accessories.MODID, "geo/test_decoration.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DecorationItem animatable) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getTextureResource'");
        return new ResourceLocation(decorative_accessories.MODID, "textures/item/test_decoration.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DecorationItem animatable) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAnimationResource'");
        // return new ResourceLocation(decorative_accessories.MODID, "animations/test_decoration.animation.json");
        return null;
    }
    
}
