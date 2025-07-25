package com.core.dream_sakura.skill;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = dream_sakura.MODID)
public class SkillRegistry {
    private static final List<SkillBinding> bindings = new CopyOnWriteArrayList<>();

    // 注册新技能
    public static void registerBinding(SkillBinding binding) {
        if (binding != null) {
            bindings.add(binding);
        }
    }
    
    // 获取所有绑定
    public static List<SkillBinding> getBindings() {
        return bindings;
    }
    
    // 清除所有绑定（用于重载）
    public static void clearBindings() {
        bindings.clear();
    }

    /**
     * 触发技能
     * @param itemId - 技能物品ID
     * @param player - 玩家
    */
    public static void handleSkillTrigger(String itemId, ServerPlayer player){
        for (SkillBinding binding : bindings) {
            if (binding.getItemId().equals(itemId)) {
                if (!binding.isWearingBoundItem(player)) return;

                if (binding.isCooledDown()) {
                    binding.triggerSkill(player);
                    binding.setLastUsedTime(System.currentTimeMillis());
                } else {
                    // 显示冷却信息
                    long remaining = binding.getCooldown() - (System.currentTimeMillis() - binding.getLastUsedTime());
                    long seconds = remaining / 1000;
                    long milliseconds = remaining % 1000;
                    Component message = Component.translatable("skill.cooldown")
                            .append(": ")
                            .append(Component.literal(
                                    String.format("%d.%03ds", seconds, milliseconds))
                                    .withStyle(ChatFormatting.RED));
                    player.displayClientMessage(message, true);
                }
                break;
            }
        }
    }
}
