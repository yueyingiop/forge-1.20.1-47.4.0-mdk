package com.core.dream_sakura.items;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.enums.DamageType;
import com.core.dream_sakura.skill.SkillBinding;

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

    // 技能
    private static final SkillBinding Dream_Final_Skill = new SkillBinding(
        GLFW.GLFW_KEY_K,
        "Dream Finale Skill", 
        30000, // 冷却120s
        "dream_finale",
        (player, stack)->{
            player.level().getEntitiesOfClass(
                LivingEntity.class, 
                player.getBoundingBox().inflate(36.0)
            ).forEach(entity -> {
                if (entity != player) { // 排除玩家自身
                    entity.discard(); // 删除实体
                }
            });
        }
    );

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
            PINK_GLOW,
            List.of(244,206,230), // 粉色工具提示颜色
            Dream_Final_Skill
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
            GOLD_GLOW,
            true,
            10
        )
    );

    /**
     * 随时间改变颜色rgb的函数
     * @param steps - 步骤数
     * @return
    */
    public static List<Integer> getRainbowColorList(int steps) {
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            float hue = (float) i / steps;
            int rgb = java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
            colorList.add(rgb);
        }
        return colorList;
    }
}
