package com.core.dream_sakura.network;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static int id = 0;
    
    public static void register() {
        INSTANCE.registerMessage(id++, 
                SkillTriggerPacket.class, 
                SkillTriggerPacket::encode, 
                SkillTriggerPacket::decode, 
                SkillTriggerPacket::handle);
        INSTANCE.registerMessage(id++, 
                PlayMusicPacket.class, 
                PlayMusicPacket::encode, 
                PlayMusicPacket::decode, 
                PlayMusicPacket::handle);
    }
    
    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
