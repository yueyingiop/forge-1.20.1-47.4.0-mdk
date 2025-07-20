package com.core.dream_sakura.sounds;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MusicManager {
    public static final Map<Player, SoundInstance> activeMusic = new HashMap<>(); // 播放中的音乐

    @OnlyIn(Dist.CLIENT)
    public static void playMusicForPlayer(Player player, SoundEvent sound, Boolean loop) {
        if (player == null || sound == null) return;

        if (activeMusic.containsKey(player)) {
            stopMusicForPlayer(player);
        }  // 停止当前正在播放的音乐
        // 关闭背景音乐
        Minecraft.getInstance().getSoundManager().stop(null, SoundSource.MUSIC);
        CustomLoopingSound music = new CustomLoopingSound(
            player,
            sound, 
            SoundSource.MUSIC,
            loop
        ); // 创建音乐实例
        Minecraft.getInstance().getSoundManager().play(music); // 播放音乐
        activeMusic.put(player, music); // 记录正在播放的音乐
    }

    @OnlyIn(Dist.CLIENT)
    public static void stopMusicForPlayer(Player player) {
        if (player == null || !activeMusic.containsKey(player)) return;
        
        SoundInstance music = activeMusic.get(player);
        if (music != null) {
            Minecraft.getInstance().getSoundManager().stop(music);
        }
        activeMusic.remove(player);
    }
}
