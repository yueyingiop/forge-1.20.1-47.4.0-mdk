package com.core.dream_sakura.items.client;

import com.core.dream_sakura.items.RegistryItem;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = "dream_sakura", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        DecorationRenderer decorationRenderer = new DecorationRenderer();

        CuriosRendererRegistry.register(
            RegistryItem.DREAM_FINALE.get(), 
            () -> new GeckoCurioRenderer<>(decorationRenderer)
        );
    }
}
