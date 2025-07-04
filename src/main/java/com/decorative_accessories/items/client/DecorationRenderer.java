package com.decorative_accessories.items.client;

import com.decorative_accessories.items.DecorationItem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class DecorationRenderer extends GeoItemRenderer<DecorationItem> {
    public DecorationRenderer() {
        super(new DecorationModel());
    }
}
