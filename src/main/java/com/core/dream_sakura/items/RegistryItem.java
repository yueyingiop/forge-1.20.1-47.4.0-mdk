package com.core.dream_sakura.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Supplier;

import org.lwjgl.glfw.GLFW;

import com.core.dream_sakura.Config;
import com.core.dream_sakura.dream_sakura;
import com.core.dream_sakura.blocks.RegistryBlock;
import com.core.dream_sakura.enums.DamageType;
import com.core.dream_sakura.skill.SkillBinding;
import com.core.dream_sakura.sounds.RegistrySound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryItem {
    // 创建注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, dream_sakura.MODID);
    
    // 定义发光颜色
    private static final float[] NULL_GLOW = {}; // 无色
    private static final float[] PINK_GLOW = {0.956863f,0.807843f,0.901961f}; // 粉色
    private static final float[] GOLD_GLOW = {0.956863f,0.992157f,0.329412f}; // 金色

    // 技能
    private static final Supplier<SkillBinding> Dream_Final_Skill = ()->{
        if (FMLEnvironment.dist == Dist.CLIENT) {
            return new SkillBinding(
                GLFW.GLFW_KEY_K,
                "Dream Finale Skill", 
                Config.dreamFinaleCooldown, // 冷却120s
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
        }
        return null;
    };
    
    



    // 注册饰品
    public static final RegistryObject<Item> TEST_HALO = ITEMS.register(
        "test_halo", 
        () -> new DecorationItem(
            "test_halo",
            new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON),
            (slotContext, stack) ->{},
            NULL_GLOW
        )
    );
    
    public static final RegistryObject<Item> DREAM_FINALE = ITEMS.register(
        "dream_finale", 
        () -> new DecorationItem(
            "dream_finale",
            new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON),
            (slotContext, stack) ->{
                LivingEntity entity = slotContext.entity();
                
                // 每tick移除所有效果
                if (!entity.level().isClientSide()){
                    // 检测正在活动的负面效果
                    Collection<MobEffectInstance> activeEffects = entity.getActiveEffects();  // 获取所有效果
                    List<MobEffect> effectsToRemove = new ArrayList<>();  // 负面效果列表
                    for (MobEffectInstance effectInstance : activeEffects) {
                        if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) {
                            effectsToRemove.add(effectInstance.getEffect());  // 将负面效果添加到列表中
                        }
                    }

                    // 移除负面效果
                    for (MobEffect effect : effectsToRemove) {
                        entity.removeEffect(effect);
                    }

                    // 添加飞行效果
                    if(entity instanceof Player player){
                        Abilities abilities = player.getAbilities();
                        abilities.mayfly = true;

                        if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                            serverPlayer.onUpdateAbilities();
                        }
                    }
                }
            },
            stack -> {
                // 免疫所有伤害
                return EnumSet.of(DamageType.ALL);
            },
            PINK_GLOW,
            List.of(244,206,230), // 粉色工具提示颜色
            Dream_Final_Skill.get(),
            ResourceLocation.fromNamespaceAndPath(dream_sakura.MODID, "dream_finale_music")
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



    // 注册方块物品
    //#region crystal系列物品
    public static final RegistryObject<Item> CRYSTAL_ITEM = ITEMS.register(
        "crystal", 
        () -> new AnimatedBlockItem(
            "crystal",
            RegistryBlock.CRYSTAL.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_BLACK_ITEM = ITEMS.register(
        "crystal_black", 
        () -> new AnimatedBlockItem(
            "crystal_black",
            RegistryBlock.CRYSTAL_BLACK.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_BLUE_ITEM = ITEMS.register(
        "crystal_blue", 
        () -> new AnimatedBlockItem(
            "crystal_blue",
            RegistryBlock.CRYSTAL_BLUE.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_BROWN_ITEM = ITEMS.register(
        "crystal_brown", 
        () -> new AnimatedBlockItem(
            "crystal_brown",
            RegistryBlock.CRYSTAL_BROWN.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_CYAN_ITEM = ITEMS.register(
        "crystal_cyan", 
        () -> new AnimatedBlockItem(
            "crystal_cyan",
            RegistryBlock.CRYSTAL_CYAN.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_GRAY_ITEM = ITEMS.register(
        "crystal_gray", 
        () -> new AnimatedBlockItem(
            "crystal_gray",
            RegistryBlock.CRYSTAL_GRAY.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_GREEN_ITEM = ITEMS.register(
        "crystal_green", 
        () -> new AnimatedBlockItem(
            "crystal_green",
            RegistryBlock.CRYSTAL_GREEN.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_LIGHT_BLUE_ITEM = ITEMS.register(
        "crystal_light_blue", 
        () -> new AnimatedBlockItem(
            "crystal_light_blue",
            RegistryBlock.CRYSTAL_LIGHT_BLUE.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_LIGHT_GRAY_ITEM = ITEMS.register(
        "crystal_light_gray", 
        () -> new AnimatedBlockItem(
            "crystal_light_gray",
            RegistryBlock.CRYSTAL_LIGHT_GRAY.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_LIME_ITEM = ITEMS.register(
        "crystal_lime", 
        () -> new AnimatedBlockItem(
            "crystal_lime",
            RegistryBlock.CRYSTAL_LIME.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_MAGENTA_ITEM = ITEMS.register(
        "crystal_magenta", 
        () -> new AnimatedBlockItem(
            "crystal_magenta",
            RegistryBlock.CRYSTAL_MAGENTA.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_ORANGE_ITEM = ITEMS.register(
        "crystal_orange", 
        () -> new AnimatedBlockItem(
            "crystal_orange",
            RegistryBlock.CRYSTAL_ORANGE.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_PINK_ITEM = ITEMS.register(
        "crystal_pink", 
        () -> new AnimatedBlockItem(
            "crystal_pink",
            RegistryBlock.CRYSTAL_PINK.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_PURPLE_ITEM = ITEMS.register(
        "crystal_purple", 
        () -> new AnimatedBlockItem(
            "crystal_purple",
            RegistryBlock.CRYSTAL_PURPLE.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_RED_ITEM = ITEMS.register(
        "crystal_red", 
        () -> new AnimatedBlockItem(
            "crystal_red",
            RegistryBlock.CRYSTAL_RED.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_WHITE_ITEM = ITEMS.register(
        "crystal_white", 
        () -> new AnimatedBlockItem(
            "crystal_white",
            RegistryBlock.CRYSTAL_WHITE.get(), 
            new Item.Properties()
        )
    );

    public static final RegistryObject<Item> CRYSTAL_YELLOW_ITEM = ITEMS.register(
        "crystal_yellow", 
        () -> new AnimatedBlockItem(
            "crystal_yellow",
            RegistryBlock.CRYSTAL_YELLOW.get(), 
            new Item.Properties()
        )
    );

    //#endregion

    
    
    // 注册唱片
    public static final RegistryObject<Item> TEST_MUSIC_DISC_ITEM = ITEMS.register(
        "test_music_disc", 
        () -> new MusicDiscItem(
            5, 
            () -> RegistrySound.DREAM_FINALE_MUSIC.get(), 
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 
            155 * 20 // 时长(tick)
        )
    );

    // 注册武器
    public static final RegistryObject<Item> PSIONIC_SCEPTER = ITEMS.register(
        "psionic_scepter",
        () -> new WeaponItem(
            "psionic_scepter", 
            new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
        )
    );

    public static final RegistryObject<Item> ENDER_SLAYER = ITEMS.register(
        "ender_slayer",
        () -> new WeaponItem(
            "ender_slayer", 
            new Item.Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
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
