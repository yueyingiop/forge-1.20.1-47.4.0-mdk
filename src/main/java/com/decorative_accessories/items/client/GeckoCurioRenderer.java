package com.decorative_accessories.items.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class GeckoCurioRenderer<T extends LivingEntity> implements ICurioRenderer {
    private final GeoItemRenderer<?> geoRenderer;

    public GeckoCurioRenderer(GeoItemRenderer<?> geoRenderer) {
        this.geoRenderer = geoRenderer;
    }

    @Override
    public <
        T extends LivingEntity, 
        M extends EntityModel<T>
    > void render(
            ItemStack stack, 
            SlotContext slotContext,
            PoseStack matrixStack, 
            RenderLayerParent<T, M> renderLayerParent, 
            MultiBufferSource renderTypeBuffer,
            int light, 
            float limbSwing, 
            float limbSwingAmount, 
            float partialTicks, 
            float ageInTicks, 
            float netHeadYaw,
            float headPitch
        ) {
            LivingEntity entity = slotContext.entity();
            float haedY = entity.getEyeHeight() - (entity.isBaby() ? 0.1F : 0.0F);
            matrixStack.pushPose();
            matrixStack.translate(-0.5D, -(haedY - 0.4D), -0.5D);
            matrixStack.mulPose(Axis.YP.rotationDegrees(netHeadYaw));
            matrixStack.mulPose(Axis.XP.rotationDegrees(headPitch));
            // if (entity.isCrouching()) {
            //     matrixStack.translate(0.0F, 0.15F, 0.0F);
            // }else if (entity.isSwimming()) {
            //     matrixStack.translate(0.0F, 0.2F, 0.1F);
            // }
            geoRenderer.renderByItem(stack, null, matrixStack, renderTypeBuffer, light, light);
            matrixStack.popPose();
    }

    
}