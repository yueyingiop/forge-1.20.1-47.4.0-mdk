package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.blocks.entity.RegistryBlockEntity;
import com.core.dream_sakura.blocks.entity.client.AnimatedBlockRender;
import com.core.dream_sakura.items.RegistryItem;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = dream_sakura.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        DecorationRenderer decorationRenderer = new DecorationRenderer();

        CuriosRendererRegistry.register(
            RegistryItem.DREAM_FINALE.get(), 
            () -> new GeckoCurioRenderer<>(decorationRenderer)
        );
        CuriosRendererRegistry.register(
            RegistryItem.BASIC_HALO.get(), 
            () -> new GeckoCurioRenderer<>(decorationRenderer)
        );


        BlockEntityRenderers.register(
            RegistryBlockEntity.ANIMATED_BLOCK_ENTITY.get(),
            AnimatedBlockRender::new
        );
    }

}
