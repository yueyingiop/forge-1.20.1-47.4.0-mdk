package com.core.dream_sakura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.core.dream_sakura.items.RegistryItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.GeckoLib;

// 这里的值应与 META-INF/mods.toml 文件中的条目相匹配
@Mod(dream_sakura.MODID)
public class dream_sakura
{
    public static final String MODID = "dream_sakura";
    public static final Logger LOGGER = LogManager.getLogger(dream_sakura.MODID); // 日志记录器

    // 注册创造物品栏
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> DREAM_SAKURA_TAB = CREATIVE_MODE_TABS.register("dream_sakura_tab", () -> CreativeModeTab.builder()
        .withTabsBefore(CreativeModeTabs.COMBAT)
        .title(Component.translatable("itemGroup.dream_sakura_tab"))
        .icon(() -> RegistryItem.DREAM_FINALE.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            output.accept(RegistryItem.DREAM_FINALE.get());
            output.accept(RegistryItem.BASIC_HALO.get());
        }).build()
    );

    public dream_sakura(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        CREATIVE_MODE_TABS.register(modEventBus);
        RegistryItem.ITEMS.register(modEventBus);

        // 注册自身到服务器和其他感兴趣的游戏事件
        MinecraftForge.EVENT_BUS.register(this);

        // 注册我们 mod 的 ForgeConfigSpec，以便 Forge 能为我们创建和加载配置文件
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        // GeckoLib
        GeckoLib.initialize();
    }

}
