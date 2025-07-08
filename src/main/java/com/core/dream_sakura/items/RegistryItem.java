package com.core.dream_sakura.items;

import java.util.ArrayList;
import java.util.EnumSet;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.enums.DamageType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryItem {
    // 创建注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, dream_sakura.MODID);
    
    // 定义发光颜色
    private static final float[] PINK_GLOW = {0.956863f,0.807843f,0.901961f}; // 粉色
    private static final float[] GOLD_GLOW = {0.956863f,0.992157f,0.329412f}; // 金色
    


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
                if (!entity.level().isClientSide()) new ArrayList<>(entity.getActiveEffectsMap().keySet()).forEach(entity::removeEffect);
            },
            stack -> {
                // 免疫所有伤害
                return EnumSet.of(DamageType.ALL);
            },
            PINK_GLOW
        )
        
    );

    public static final RegistryObject<Item> BASIC_HALO = ITEMS.register(
        "basic_halo",
        () -> new DecorationItem(
            "basic_halo",
            new Item.Properties()
            .stacksTo(1).
            rarity(Rarity.COMMON),
            (slotContext, stack) ->{
                
            },
            GOLD_GLOW
        )
    );
}
