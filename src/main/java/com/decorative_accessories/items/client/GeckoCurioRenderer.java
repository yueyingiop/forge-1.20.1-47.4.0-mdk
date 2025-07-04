package com.decorative_accessories.items.client;

import com.mojang.blaze3d.vertex.PoseStack;
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
        matrixStack.pushPose();
        matrixStack.translate(0.0D, 0.1D, 0.0D);
        geoRenderer.renderByItem(stack, null, matrixStack, renderTypeBuffer, light, light);
        matrixStack.popPose();
    }

    
}