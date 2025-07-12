package com.core.dream_sakura.blocks.entity.client;

import javax.annotation.Nullable;

import com.core.dream_sakura.blocks.entity.AnimatedBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class AnimatedBlockRender extends GeoBlockRenderer<AnimatedBlockEntity> {

    public AnimatedBlockRender(BlockEntityRendererProvider.Context context) {
        super(new AnimatedBlockModel());
    }

    @Override
    public RenderType getRenderType(AnimatedBlockEntity animatable, ResourceLocation texture,
            @Nullable MultiBufferSource bufferSource, float partialTick) {
        // return super.getRenderType(animatable, texture, bufferSource, partialTick);
        return RenderType.entityTranslucent(texture);
    }
}
