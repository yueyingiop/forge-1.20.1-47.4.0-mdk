package com.core.dream_sakura.items;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import com.core.dream_sakura.items.client.AnimatedBlockItemRender;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class AnimatedBlockItem extends BlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final String itemId;

    public AnimatedBlockItem(String itemIDString, Block block, Properties properties) {
        super(block, properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
        this.itemId = itemIDString;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::handleAnimations));
    }

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation");


    private <T extends GeoAnimatable> PlayState handleAnimations(AnimationState<T> state) {
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
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private AnimatedBlockItemRender render;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.render == null) 
                    this.render = new AnimatedBlockItemRender();
                return this.render;
            }
        });
    }

    public String getItemId() {
        return this.itemId;
    }

}
