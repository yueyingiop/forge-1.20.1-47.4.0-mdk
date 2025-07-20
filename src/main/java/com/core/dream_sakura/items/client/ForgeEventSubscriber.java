package com.core.dream_sakura.items.client;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.items.DecorationItem;
import com.core.dream_sakura.sounds.MusicManager;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.event.CurioChangeEvent;

@Mod.EventBusSubscriber(modid = dream_sakura.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEventSubscriber {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onCurioEquip(CurioChangeEvent event) {
        dream_sakura.LOGGER.info("[DEBUG] CurioEquipEvent" + event.getTo().getItem().getDescription().getString());
        if (FMLEnvironment.dist != Dist.CLIENT) return;
        if (event.getEntity() instanceof Player player && event.getTo().getItem() instanceof DecorationItem item) {
            dream_sakura.LOGGER.info("[DEBUG] Equipping music item" + item.getItemId());
            dream_sakura.LOGGER.info("[DEBUG] player ID" + player.getName());
            if (item.isPlayingMusic()) {
                SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(item.getMusicResource());
                if (sound != null) {
                    dream_sakura.LOGGER.info("[DEBUG] Playing music" + sound.getLocation());
                    MusicManager.playMusicForPlayer(player, sound, true);
                } else {
                   dream_sakura.LOGGER.info("[DEBUG] Music resource not found" + item.getMusicResource()); 
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onCurioUnequip(CurioChangeEvent event) {
        if (FMLEnvironment.dist != Dist.CLIENT) return;
        if (event.getEntity() instanceof Player player && 
            event.getFrom().getItem() instanceof DecorationItem item) {
            
            if (item.isPlayingMusic()) {
                MusicManager.stopMusicForPlayer(player);
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onCurioEvent(CurioChangeEvent event) {
        // dream_sakura.LOGGER.info("Client received CurioChangeEvent");
    }
}
