package com.core.dream_sakura.listener;

import java.util.List;
import java.util.Set;

import com.core.dream_sakura.enums.DamageType;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.EventPriority;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

public interface IDamageImmunity {
    // 获取物品的免疫伤害类型
    Set<DamageType> getImmunities(ItemStack stack);
    // 监听器
    @Mod.EventBusSubscriber
    public class ImmunityListener {

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onLivingAttack(LivingAttackEvent event) {
            LivingEntity entity = event.getEntity();
            DamageSource source = event.getSource();
            
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                List<SlotResult> equippedCurios = handler.findCurios(stack -> true);

                for (SlotResult slotResult : equippedCurios) {
                    ItemStack stack = slotResult.stack();
                    if (stack.getItem() instanceof IDamageImmunity immunityItem) {
                        Set<DamageType> immunities = immunityItem.getImmunities(stack);
                        if (shouldImmune(immunities, source)) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            });
        }


        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onLivingHurt(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity(); // 获取受伤实体
            DamageSource source = event.getSource(); // 获取伤害来源
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                // 遍历饰品
                List<SlotResult> equippedCurios  = handler.findCurios(stack -> true);

                for (SlotResult slotResult : equippedCurios){
                    ItemStack stack = slotResult.stack();
                    if (stack.getItem() instanceof IDamageImmunity immunityItem) {
                        // 获取物品的免疫伤害类型
                        Set<DamageType> immunities = immunityItem.getImmunities(stack);
                        // 检查伤害来源是否在免疫列表中
                        if (shouldImmune(immunities, source)) {
                            event.setCanceled(true);
                            return;
                        }
                        
                    }
                }
            });
        }

        /**
         * 检查伤害来源是否在免疫列表中
         * 
         * @param immunities - 免疫列表
         * @param source     - 伤害来源
         * @return - 是否免疫伤害
        */
        private static boolean shouldImmune(Set<DamageType> immunities, DamageSource source) {
            if (immunities.contains(DamageType.ALL)) {
                return true; // 如果包含ALL，则免疫所有伤害
            }

            for (DamageType type : immunities) {
                if (type.shouldImmune(source)) {
                    return true; // 如果免疫列表中包含与伤害来源匹配的类型，则免疫伤害
                }
            }

            return false; // 否则不免疫伤害
        }
    }
}
