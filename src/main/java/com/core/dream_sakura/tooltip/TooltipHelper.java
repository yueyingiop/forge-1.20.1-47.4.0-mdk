package com.core.dream_sakura.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import com.core.dream_sakura.dream_sakura;

import java.util.List;



public class TooltipHelper {
    /**
     * 为物品添加通用的工具提示
     * 
     * @param itemId 物品ID（如 "dream_finale"）
     * @param stack 物品堆栈
     * @param level 世界
     * @param tooltip 工具提示列表
     * @param flag 工具提示标志
     * @param colorList 颜色列表（RGB格式）
     */
    public static void addTooltip(
        String itemId, 
        ItemStack stack, 
        @Nullable Level level, 
        List<Component> tooltip, 
        TooltipFlag flag,
        List<Integer> colorList) 
    {
        if (level == null || !level.isClientSide()) return; // 确保在客户端执行

        tooltip.add(Component.translatable("tooltip." + dream_sakura.MODID + "." + itemId + ".base")
            .withStyle(ChatFormatting.GRAY));  // 添加基础描述

        // 检查是否按下Shift键
        if(Screen.hasShiftDown()){
            // 添加详细描述
            if (colorList != null && !colorList.isEmpty()) {
                addDetailedTooltip(itemId, tooltip, "describe", colorList);
            } else {
                addDetailedTooltip(itemId, tooltip, "describe");
            }
        } else {
            // 添加shift提示
            addShiftPrompt(tooltip);
        }

        if (Screen.hasControlDown()) {
            if (colorList != null && !colorList.isEmpty()) {
                addDetailedTooltip(itemId, tooltip, "effect", colorList);
            } else {
                addDetailedTooltip(itemId, tooltip, "effect");
            }
        } else {
            addCtrlPrompt(tooltip);
        }
    }

    /**
     * 添加物品的Shift提示
     * 
     * @param itemId 物品ID（如 "dream_finale"）
     * @param stack 物品堆栈
     * @param level 世界
     * @param tooltip 工具提示列表
     * @param flag 工具提示标志
    */
     public static void addTooltip(
        String itemId, 
        ItemStack stack, 
        @Nullable Level level, 
        List<Component> tooltip, 
        TooltipFlag flag)
    {
        addTooltip(itemId, stack, level, tooltip, flag);
    }

    /**
     * 将颜色列表转换为RGB整数
     * @param colorList - 颜色列表，包含三个整数值，分别表示红色、绿色和蓝色分量
     * @return - 返回一个整数，表示RGB颜色值
    */
    private static int colorListParse(List<Integer> colorList){
        return colorList.get(0) << 16 | colorList.get(1) << 8 | colorList.get(2);
    }

    /**
     * 添加详细的工具提示
     * 
     * @param itemId 物品ID（如 "dream_finale"）
     * @param tooltip 工具提示列表
     * @param Type 类型（"describe" 或 "effect" ）
     * @param colorList 颜色列表（RGB格式）
    */
    private static void addDetailedTooltip(String itemId, List<Component> tooltip, String Type, List<Integer> colorList) {
        int line = 1;
        while (true) {
            String key = "tooltip." + dream_sakura.MODID + "." + itemId + "." + Type + ".line" + line;
            MutableComponent component = Component.translatable(key); // 获取多语言文本

            if (component.getString().equals(key)) break; // 如果没有更多行，退出循环

            tooltip.add(
                component.withStyle(
                    Style.EMPTY.withColor(
                        TextColor.fromRgb(colorListParse(colorList))
                    )
                )
            ); // 添加到工具提示
            line++;
        }
    }
    
    /**
     * 添加详细的工具提示
     * 
     * @param itemId 物品ID（如 "dream_finale"）
     * @param tooltip 工具提示列表
     * @param Type 类型（"describe" 或 "effect"）
    */
    private static void addDetailedTooltip(String itemId, List<Component> tooltip, String Type) {
        List<Integer> defaultColor = List.of(85,85,255); // 默认颜色为蓝色
        addDetailedTooltip(itemId, tooltip, Type, defaultColor);
    }

    /**
     * 添加Shift提示到工具提示列表
     * 
     * @param tooltip
    */
    private static void addShiftPrompt(List<Component> tooltip) {
        // 创建带颜色的SHIFT文本
        MutableComponent shiftText = Component.literal("[SHIFT]")
                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD);
        
        // 添加提示
        tooltip.add(Component.translatable("tooltip.dream_sakura.shift_prompt", shiftText)
                 .withStyle(ChatFormatting.DARK_GRAY));
    }

    /**
     * 添加Ctrl提示到工具提示列表
     * 
     * @param tooltip
    */
    private static void addCtrlPrompt(List<Component> tooltip) {
        // 创建带颜色的CTRL文本
        MutableComponent ctrlText = Component.literal("[CTRL]")
                .withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD);
        
        // 添加提示
        tooltip.add(Component.translatable("tooltip.dream_sakura.ctrl_prompt", ctrlText)
                 .withStyle(ChatFormatting.DARK_GRAY));
    }

    public static void addSkillsDescription(List<Component> tooltip, String description, String keyName){
        String displayKey;
        if (keyName.startsWith("key.mouse.")) {
            // 鼠标按键，提取数字
            String mouseNum = keyName.substring("key.mouse.".length());
            displayKey = "鼠标按键" + mouseNum;
        } else {
            // 键盘按键，去掉前缀
            displayKey = keyName.replaceFirst("^key\\.keyboard\\.(keypad\\.)?", "").toUpperCase();
        }
        MutableComponent SkillText = Component.literal(
            "["+ displayKey +"]"
        ).withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD);

        tooltip.add(Component.translatable("tooltip." + dream_sakura.MODID + "." + description.replace(' ', '_').toLowerCase() +".key", SkillText)
                 .withStyle(ChatFormatting.BLUE));
    }
}
