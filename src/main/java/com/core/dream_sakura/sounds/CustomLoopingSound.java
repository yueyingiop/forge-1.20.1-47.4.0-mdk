package com.core.dream_sakura.sounds;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomLoopingSound extends AbstractSoundInstance {
    private final Player player;
    private final boolean looping;

    @OnlyIn(Dist.CLIENT)
    public CustomLoopingSound(Player player, SoundEvent sound, SoundSource source, boolean loop) {
        super(sound, source, RandomSource.create());
        this.player = player;
        this.looping = loop;
        this.volume = 1.0F; // 音量
        this.pitch = 1.0F; // 音调
        this.attenuation = Attenuation.LINEAR; // 线性衰减
        this.relative = false; // 绝对位置
        this.delay = 0; // 延迟时间

        updatePosition();
    }

    private void updatePosition() {
        if (player != null && player.isAlive()) {
            this.x = (float) player.getX();
            this.y = (float) player.getY();
            this.z = (float) player.getZ();
        }
    }

    // @Override
    // public void tick() {
    //     super.tick();
    //     updatePosition(); // 每tick更新位置
    // }
    
    @Override
    public boolean isLooping() {
        return this.looping;
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }
    

}
