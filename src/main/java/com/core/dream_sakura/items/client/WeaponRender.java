package com.core.dream_sakura.items.client;

import javax.annotation.Nullable;

import com.core.dream_sakura.items.WeaponItem;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponRender extends GeoItemRenderer<WeaponItem>{
    public WeaponRender() {
        super(new WeaponModel());
    }

    @Override
    public RenderType getRenderType(WeaponItem animatable, ResourceLocation texture,
            @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
