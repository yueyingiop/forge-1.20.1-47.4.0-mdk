package com.decorative_accessories.items;

import java.util.function.Consumer;
import javax.annotation.Nonnull;
import com.decorative_accessories.items.client.DecorationRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
// import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
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
    // private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // 构造函数
    public DecorationItem(Properties properties) {
        super(properties.stacksTo(1));
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
        return "decoration".equals(slotContext.identifier());
    }

    // 允许右键直接装备
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
