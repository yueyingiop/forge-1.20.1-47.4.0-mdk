package com.core.dream_sakura.items;

import java.util.ArrayList;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryItem {
    // 创建注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, dream_sakura.MODID);
    // dream_finale
    // 注册饰品
    public static final RegistryObject<Item> DREAM_FINALE = ITEMS.register(
        "dream_finale", 
        () -> new DecorationItem(
            "dream_finale",
            new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON),
            (slotContext, stack) ->{
                LivingEntity entity = slotContext.entity();
                
                // 每5tick移除所有效果
                if (!entity.level().isClientSide() && entity.tickCount % 5 == 0) new ArrayList<>(entity.getActiveEffectsMap().keySet()).forEach(entity::removeEffect);

            }
        )
    );
}
