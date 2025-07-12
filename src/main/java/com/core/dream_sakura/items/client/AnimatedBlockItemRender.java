package com.core.dream_sakura.items.client;

import javax.annotation.Nullable;

import com.core.dream_sakura.items.AnimatedBlockItem;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AnimatedBlockItemRender extends GeoItemRenderer<AnimatedBlockItem> {

    public AnimatedBlockItemRender() {
        super(new AnimatedBlockItemModel());
    }

    @Override
    public RenderType getRenderType(AnimatedBlockItem animatable, ResourceLocation texture,
            @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
