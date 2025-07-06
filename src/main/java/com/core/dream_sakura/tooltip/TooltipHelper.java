package com.core.dream_sakura.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import com.core.dream_sakura.dream_sakura;

import java.util.List;

public class TooltipHelper {

    /**
     * 为物品添加通用的工具提示（按住Shift显示详情）
     * 
     * @param itemId 物品ID（如 "dream_finale"）
     * @param stack 物品堆栈
     * @param level 世界
     * @param tooltip 工具提示列表
     * @param flag 工具提示标志
     */
    public static void addShiftTooltip(
        String itemId, 
        ItemStack stack, 
        @Nullable Level level, 
        List<Component> tooltip, 
        TooltipFlag flag) 
    {
        if (level == null || !level.isClientSide()) return; // 确保在客户端执行

        tooltip.add(Component.translatable("tooltip." + dream_sakura.MODID + "." + itemId + ".base")
            .withStyle(ChatFormatting.GRAY));  // 添加基础描述

        // 检查是否按下Shift键
        if(Screen.hasShiftDown()){
            // 添加详细描述
            addDetailedTooltip(itemId, tooltip);
        } else {
            // 添加shift提示
            addShiftPrompt(tooltip);
        }
    }


    private static void addDetailedTooltip(String itemId, List<Component> tooltip) {
        int line = 1;
        while (true) {
            String key = "tooltip." + dream_sakura.MODID + "." + itemId + ".line" + line;
            MutableComponent component = Component.translatable(key); // 获取多语言文本

            if (component.getString().equals(key)) break; // 如果没有更多行，退出循环

            tooltip.add(component.withStyle(ChatFormatting.BLUE)); // 添加到工具提示
            line++;
        }
    }

    private static void addShiftPrompt(List<Component> tooltip) {
        // 创建带颜色的SHIFT文本
        MutableComponent shiftText = Component.literal("[SHIFT]")
                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD);
        
        // 添加提示
        tooltip.add(Component.translatable("tooltip.dream_sakura.shift_prompt", shiftText)
                 .withStyle(ChatFormatting.DARK_GRAY));
    }
}
