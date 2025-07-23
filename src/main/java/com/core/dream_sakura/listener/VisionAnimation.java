package com.core.dream_sakura.listener;

import java.util.Arrays;
import java.util.List;

import com.core.dream_sakura.Config;
import com.core.dream_sakura.items.DecorationItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class VisionAnimation {

    public static class ItemAttribute {
        public final String itemId;
        public final List<String> attributes;
        
        public ItemAttribute(String itemId, List<String> attributes) {
            this.itemId = itemId;
            this.attributes = attributes;
        }
    }

    public static ItemAttribute parseItemAttribute(String input) {
        // 检查输入是否包含属性部分
        int braceIndex = input.indexOf('{');
        if (braceIndex == -1) {
            // 没有属性部分，直接返回整个字符串作为 itemId
            return new ItemAttribute(input.trim(), List.of());
        }
        
        // 分割出 itemId 部分
        String itemId = input.substring(0, braceIndex).trim();
        
        // 提取属性部分并分割
        String attributesPart = input.substring(braceIndex);
        if (!attributesPart.startsWith("{") || !attributesPart.endsWith("}")) {
            throw new IllegalArgumentException("Invalid attribute format: " + input);
        }
        
        // 去除大括号并按逗号分割属性
        String inner = attributesPart.substring(1, attributesPart.length() - 1).trim();
        List<String> attributes = inner.isEmpty() ? 
            List.of() : 
            Arrays.asList(inner.split("\\s*,\\s*"));
        
        return new ItemAttribute(itemId, attributes);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderOverlay(RenderGuiOverlayEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        // ItemAttribute test = parseItemAttribute(Config.visualAnimation.get(0));
        // dream_sakura.LOGGER.info("[DEBUG]"+test.itemId+test.attributes);

        Config.visualAnimation.forEach(value -> { 
            ItemAttribute itemAttribute = parseItemAttribute(value);
            
            boolean hasDreamFinale = CuriosApi.getCuriosInventory(player).map(inv -> 
                inv.findCurios(
                    stack -> {
                        String itemID = itemAttribute.itemId.toString();
                        return stack.getItem() instanceof DecorationItem && itemID.equals(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString());
                    }
                ).size() > 0
            ).orElse(false);

            if (hasDreamFinale) {
                // 取消冰冻效果
                if (itemAttribute.attributes.contains("ice")) {                    
                    // 重置冰冻进度
                    player.setTicksFrozen(0);
                }
                
                // 取消着火动画
                if (itemAttribute.attributes.contains("fire")) {
                    player.clearFire();
                    player.setRemainingFireTicks(0);
                    player.setSecondsOnFire(0);
                }
            }
        });
    }
}
