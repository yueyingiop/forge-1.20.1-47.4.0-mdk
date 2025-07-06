package com.core.dream_sakura.items;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.core.dream_sakura.items.client.DecorationRenderer;
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

public class DecorationItem extends Item implements ICurioItem, GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final String itemId;
    private final BiConsumer<SlotContext, ItemStack> curioEquipCallback;

    /**
     * 构造函数
     * @param itemIDString - 物品ID字符串
     * @param properties - 物品属性
     * @param curioTickCallback - 装备时的tick回调函数
    */ 
    public DecorationItem(String itemIDString ,Properties properties, BiConsumer<SlotContext, ItemStack> curioTickCallback) {
        super(properties.stacksTo(1));
        this.itemId = itemIDString; // 设置物品ID
        this.curioEquipCallback = curioTickCallback;
    }

    // 核心动画逻辑：定义动画状态机
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::handleAnimations));
    }

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation");
    private <T extends GeoItem> PlayState handleAnimations(AnimationState<T> state) {
        if (state.getController().getAnimationState() == AnimationController.State.STOPPED) {
            state.getController().setAnimation(IDLE);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object ItemStack) {
        return RenderUtils.getCurrentTick();
    }

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
     * @param slotContext - 物品上下文
     * @param stack - 物品栈
     */
    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack){
        return "halo".equals(slotContext.identifier());
    }

    // 允许右键直接装备
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    // 添加文本
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag){
        super.appendHoverText(stack, level, tooltip, flag);
        TooltipHelper.addShiftTooltip(itemId, stack, level, tooltip, flag);
    }

    /**
     * tick事件
     * 
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
}
