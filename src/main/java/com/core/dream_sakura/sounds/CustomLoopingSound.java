package com.core.dream_sakura.sounds;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;

public class CustomLoopingSound extends AbstractSoundInstance {
    private final Player player;
    private final boolean looping;

    public CustomLoopingSound(Player player, SoundEvent sound, SoundSource source, boolean loop) {
        super(sound, source, RandomSource.create());
        this.player = player;
        this.looping = loop;
        this.volume = 1.0F; // 音量
        this.pitch = 1.0F; // 音调
        this.attenuation = Attenuation.NONE; // 无衰减
        this.relative = true; // 相对位置
        this.delay = 0; // 延迟时间
    }
    
    @Override
    public boolean isLooping() {
        return this.looping;
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }
}
