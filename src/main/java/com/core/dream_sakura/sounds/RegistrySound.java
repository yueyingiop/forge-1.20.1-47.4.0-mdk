package com.core.dream_sakura.sounds;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistrySound {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, dream_sakura.MODID);

    public static final RegistryObject<SoundEvent> DREAM_FINALE_MUSIC = SOUNDS.register(
        "dream_finale_music",
        ()-> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID,"dream_finale_music"))
    );
}
