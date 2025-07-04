package com.decorative_accessories.items.client;

import com.decorative_accessories.items.RegistryItem;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = "decorative_accessories", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        DecorationRenderer decorationRenderer = new DecorationRenderer();

        CuriosRendererRegistry.register(
            RegistryItem.DECORATION_ITEM.get(), 
            () -> new GeckoCurioRenderer<>(decorationRenderer)
        );
    }
}
