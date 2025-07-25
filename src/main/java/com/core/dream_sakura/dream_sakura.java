package com.core.dream_sakura;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.core.dream_sakura.blocks.RegistryBlock;
import com.core.dream_sakura.blocks.entity.RegistryBlockEntity;
import com.core.dream_sakura.format.TrueRGBSimpleRenderer;
import com.core.dream_sakura.items.RegistryItem;
import com.core.dream_sakura.network.PacketHandler;
import com.core.dream_sakura.sounds.RegistrySound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
            output.accept(RegistryItem.CRYSTAL_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_BLACK_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_BLUE_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_BROWN_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_CYAN_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_GRAY_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_GREEN_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_LIGHT_BLUE_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_LIGHT_GRAY_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_LIME_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_MAGENTA_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_ORANGE_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_PINK_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_PURPLE_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_RED_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_WHITE_ITEM.get());
            output.accept(RegistryItem.CRYSTAL_YELLOW_ITEM.get());
            output.accept(RegistryItem.TEST_MUSIC_DISC_ITEM.get());
            output.accept(RegistryItem.PSIONIC_SCEPTER.get());
            output.accept(RegistryItem.ENDER_SLAYER.get());
        }).build()
    );

    public dream_sakura(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        CREATIVE_MODE_TABS.register(modEventBus);  // 注册创造模式物品栏
        RegistryItem.ITEMS.register(modEventBus);  // 注册物品
        RegistryBlock.BLOCKS.register(modEventBus);  // 注册方块
        RegistryBlockEntity.BLOCK_ENTITY_TYPES.register(modEventBus);  // 注册方块实体
        RegistrySound.SOUNDS.register(modEventBus);  // 注册声音

        // 注册自身到服务器和其他感兴趣的游戏事件
        MinecraftForge.EVENT_BUS.register(this);
        PacketHandler.register();

        // 注册我们 mod 的 ForgeConfigSpec，以便 Forge 能创建和加载配置文件
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        // GeckoLib
        GeckoLib.initialize();
    }


    // 保存原始字体渲染器引用
    private static Font originalFontRenderer;
    // 自定义渲染器实例
    private static TrueRGBSimpleRenderer customFontRenderer;
    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                Minecraft minecraft = Minecraft.getInstance();
                
                // 保存原始字体渲染器
                originalFontRenderer = minecraft.font;
                
                // 使用反射替换字体渲染器
                replaceFontRenderer();
                
                LOGGER.info("已替换 Minecraft 字体渲染器为 TrueRGBSimpleRenderer");
            } catch (Exception e) {
                LOGGER.error("替换字体渲染器失败", e);
            }
        });
    }

    @SubscribeEvent
    public void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        try {
            // 确保在资源重载后重新替换渲染器
            replaceFontRenderer();
            LOGGER.info("资源重载后重新替换字体渲染器");
        } catch (Exception e) {
            LOGGER.error("资源重载后替换字体渲染器失败", e);
        }
    }

    private void replaceFontRenderer() throws Exception {
        Minecraft minecraft = Minecraft.getInstance();
        
        // 如果已经替换过，跳过
        if (minecraft.font instanceof TrueRGBSimpleRenderer) {
            return;
        }
        
        // 创建自定义渲染器
        TrueRGBSimpleRenderer customFontRenderer = new TrueRGBSimpleRenderer(originalFontRenderer);
        try {
            Field fontField = Minecraft.class.getDeclaredField("font");
            fontField.setAccessible(true);
            
            // 移除 final 修饰符
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(
                fontField, 
                fontField.getModifiers() & ~Modifier.FINAL
            );
            
            fontField.set(minecraft, customFontRenderer);
        } catch (Exception e) {
            LOGGER.error("反射修改字体字段失败", e);
            throw e;
        }
    }
}
