package com.core.dream_sakura.items;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.core.dream_sakura.enums.DamageType;
import com.core.dream_sakura.items.client.DecorationRenderer;
import com.core.dream_sakura.items.client.IGlowingItem;
import com.core.dream_sakura.listener.IDamageImmunity;
import com.core.dream_sakura.tooltip.TooltipHelper;

import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class DecorationItem extends Item implements ICurioItem, GeoItem, IDamageImmunity, IGlowingItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final String itemId;
    private final BiConsumer<SlotContext, ItemStack> curioEquipCallback;
    private final Function<ItemStack, Set<DamageType>> immunityProvider;
    private final float[] glowColor; // 发光颜色


    /**
     * 构造函数
     * @param itemIDString - 物品ID字符串
     * @param properties - 物品属性
     * @param curioTickCallback - 装备时的tick回调函数
     * @param immunityProvider - 伤害免疫提供者
     * @param glowColor - 发光颜色，RGB数组
    */ 
    public DecorationItem(
        String itemIDString ,
        Properties properties, 
        BiConsumer<SlotContext, ItemStack> curioTickCallback,
        Function<ItemStack, Set<DamageType>> immunityProvider,
        float[] glowColor
        )
    {
        super(properties.stacksTo(1));
        this.itemId = itemIDString; // 设置物品ID
        this.curioEquipCallback = curioTickCallback; // 设置装备时的tick回调函数
        this.immunityProvider = immunityProvider; // 设置伤害免疫提供者
        this.glowColor = glowColor != null ? glowColor : new float[]{1.0f, 0.84f, 0.0f}; // 默认金色
    }

    // 重载构造函数，默认免疫提供者为空
    public DecorationItem(String itemIDString, Properties properties, 
                         BiConsumer<SlotContext, ItemStack> curioTickCallback) {
        this(itemIDString, properties, curioTickCallback, stack -> Collections.emptySet(), new float[]{1.0f, 0.84f, 0.0f});
    }

    // 重载构造函数，可以指定发光颜色
    public DecorationItem(String itemIDString, Properties properties, 
                         BiConsumer<SlotContext, ItemStack> curioTickCallback,
                         float[] glowColor) {
        this(itemIDString, properties, curioTickCallback, stack -> Collections.emptySet(), glowColor);
    }


    // 实现IGlowingItem接口
    @Override
    public float[] getGlowColor() {
        return glowColor;
    }

    @Override
    public float getGlowIntensity() {
        return 1.0f;
    }


    /**
     * 核心动画逻辑：定义动画状态机
     * - 来自于geckolib
     * @param controllers - 动画控制器注册器 
    */ 
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::handleAnimations));
    }


    // 创建一个RawAnimation对象，用于定义物品的闲置动画
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation");

    /**
     * 处理动画状态
     * - 来自于geckolib
     * @param state - 动画状态
     * @return - 播放状态
    */
    private <T extends GeoItem> PlayState handleAnimations(AnimationState<T> state) {
        if (state.getController().getAnimationState() == AnimationController.State.STOPPED) {
            state.getController().setAnimation(IDLE);
        }
        return PlayState.CONTINUE;
    }


    
    @Override
    /**
     * 获取动画实例缓存
     * - 来自于geckolib
     * @return - 动画实例缓存
    */
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    } 


    /**
     * 获取当前tick
     * - 来自于geckolib
     * @param ItemStack - 物品栈
     * @return - 当前tick
    */ 
    @Override
    public double getTick(Object ItemStack) {
        return RenderUtils.getCurrentTick();
    }

    
    /**
     * 初始化客户端扩展
     * - 来自于geckolib
     * @param consumer - 客户端扩展消费者
     * @return - 无返回值
    */
    @Override
    public void initializeClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DecorationRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new DecorationRenderer();
                }
                return this.renderer;
            }

        });
    }

    /**
     * 判断物品是否可以装备
     * - 来自于Curios API
     * @param slotContext - 物品上下文
     * @param stack - 物品栈
     */
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack){
        return "halo".equals(slotContext.identifier());
    }

    /**
     * 是否允许右键直接装备 
     * - 来自于Curios API
     * @param slotContext - 物品上下文
     * @param stack - 物品栈
     * @return - 返回true表示允许右键直接装备
    */ 
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }


    /**
     * tick事件
     * - 来自Curios API
     * @param slotContext - 物品上下文
     * @param stack - 物品栈
     */
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        // 执行callback
        if (curioEquipCallback != null) {
            curioEquipCallback.accept(slotContext, stack);
        }
    }


    /**
     * 添加文本提示
     * - 来自minecraft
     * @param stack - 物品栈
     * @param level - 游戏世界
     * @param tooltip - 提示文本列表
     * @param flag - 提示标志
    */
    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag){
        super.appendHoverText(stack, level, tooltip, flag);
        TooltipHelper.addShiftTooltip(itemId, stack, level, tooltip, flag);
    }

    /**
     * 获取物品ID
     * - 来自自定义
     * @return - 物品ID字符串
    */
    public String getItemId(){
        return itemId;
    }

    /**
     * 获取物品的伤害免疫类型
     * - 来自自定义IDamageImmunity接口
     * @param stack - 物品栈
     * @return - 伤害类型集合
    */
    @Override
    public Set<DamageType> getImmunities(ItemStack stack) {
        return immunityProvider.apply(stack);
    }

}
