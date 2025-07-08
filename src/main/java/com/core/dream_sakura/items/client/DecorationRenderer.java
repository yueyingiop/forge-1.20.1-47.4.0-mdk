package com.core.dream_sakura.items.client;

import com.core.dream_sakura.items.DecorationItem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DecorationRenderer extends GeoItemRenderer<DecorationItem> {
    private float gameTime = 0.0f; // 用于动画的游戏时间
    public DecorationRenderer() {
        super(new DecorationModel());
    }
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, 
            PoseStack poseStack, MultiBufferSource bufferSource, 
            int packedLight, int packedOverlay) {
        
        if (!(stack.getItem() instanceof DecorationItem item)) {
            return;
        }
        
        // 更新游戏时间
        gameTime += Minecraft.getInstance().getDeltaFrameTime() * 0.1f;
        
        // 创建发光渲染缓冲
        MultiBufferSource.BufferSource bufferSource2 = Minecraft.getInstance().renderBuffers().bufferSource();
        
        // 保存当前状态
        poseStack.pushPose();
        
        try {
            // 首先正常渲染物品
            super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
            
            // 启用混合
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            
            // 使用最高光照值
            int fullBrightLight = 0xF000F0;
            
            // 获取物品的发光颜色
            float[] glowColor = {1.0f, 0.84f, 0.0f}; // 默认金色
            float glowIntensity = 1.0f;
            
            // 如果物品实现了IGlowingItem接口，使用其提供的颜色
            if (item instanceof IGlowingItem) {
                IGlowingItem glowingItem = (IGlowingItem) item;
                glowColor = glowingItem.getGlowColor();
                glowIntensity = glowingItem.getGlowIntensity();
            }
            
            // 设置发光颜色
            RenderSystem.setShaderColor(
                glowColor[0], 
                glowColor[1], 
                glowColor[2], 
                glowIntensity
            );
            
            // 渲染发光层
            super.renderByItem(stack, transformType, poseStack, bufferSource2, fullBrightLight, OverlayTexture.NO_OVERLAY);
            
            // 确保立即渲染
            bufferSource2.endBatch();
        } finally {
            // 恢复状态
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
            poseStack.popPose();
        }
    }
}
