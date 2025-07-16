package com.core.dream_sakura.items;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import com.core.dream_sakura.items.client.WeaponRender;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class WeaponItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final String itemId;

    public WeaponItem(String itemId, Properties properties) {
        super(properties.stacksTo(1));
        this.itemId = itemId;
    }

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
            private WeaponRender renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new WeaponRender();
                }
                return this.renderer;
            }

        });
    }

    public String getItemId(){
        return itemId;
    }

}
