package com.core.dream_sakura.network;

import java.util.function.Supplier;

import com.core.dream_sakura.sounds.MusicManager;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PlayMusicPacket {
    public final ResourceLocation musicResource;
    public final boolean play;

    public PlayMusicPacket(ResourceLocation musicResource, boolean play) {
        this.musicResource = musicResource;
        this.play = play;
    }

    public static void encode(PlayMusicPacket packet, FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(packet.musicResource);
        buffer.writeBoolean(packet.play);
    }

    public static PlayMusicPacket decode(FriendlyByteBuf buffer) {
        return new PlayMusicPacket(buffer.readResourceLocation(), buffer.readBoolean());
    }

    public static void handle(PlayMusicPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getReceptionSide().isServer()) {
                // 服务器端处理
                Player player = ctx.get().getSender();
                if (player == null) return;
                
                // 发送给所有相关客户端
                PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player);
                PacketHandler.INSTANCE.send(target, packet);
            } else {
                // 客户端处理
                Player player = Minecraft.getInstance().player;
                if (player == null) return;
                
                if (packet.play) {
                    SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(packet.musicResource);
                    if (sound != null) {
                        MusicManager.playMusicForPlayer(player, sound, true);
                    }
                } else {
                    MusicManager.stopMusicForPlayer(player);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}