package com.core.dream_sakura.network;

import java.util.function.Supplier;

import com.core.dream_sakura.skill.SkillRegistry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SkillTriggerPacket {
    private final String itemId;

    public SkillTriggerPacket(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 编码
     * 
     * @param packet - 数据包
     * @param buffer - 数据缓存
    */
    public static void encode(SkillTriggerPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.itemId);
    }

    /**
     * 解码
     * 
     * @param buffer - 数据缓存
     * @return 数据包
    */
    public static SkillTriggerPacket decode(FriendlyByteBuf buffer) {
        return new SkillTriggerPacket(buffer.readUtf());
    }

    /**
     * 处理数据包
     * 
     * @param packet
     * @param ctx
    */
    public static void handle(SkillTriggerPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 在服务器端处理技能触发
            SkillRegistry.handleSkillTrigger(packet.itemId, ctx.get().getSender());
        });
        ctx.get().setPacketHandled(true);
    }
}
