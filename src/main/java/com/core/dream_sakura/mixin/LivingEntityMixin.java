package com.core.dream_sakura.mixin;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.core.dream_sakura.items.RegistryItem;
import com.core.dream_sakura.util.ReflectionUtil;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


    @Inject(method = "die", at = @At("HEAD"), cancellable = true)
    private void onDie(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) {
            return;
        }

        boolean hasDreamFinale = hasDreamFinaleCurio(player);
        if(hasDreamFinale){
            player.setHealth(player.getMaxHealth());
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(entity instanceof Player player)) {
            return;
        }
        boolean hasDreamFinale = hasDreamFinaleCurio(player);

        if (hasDreamFinale) {
            ReflectionUtil.setHealthData(entity, player.getMaxHealth());
        }
    }

    private boolean hasDreamFinaleCurio(Player player) {
        AtomicBoolean hasCurio = new AtomicBoolean(false);
        // 获取玩家的饰品处理器
        Optional<ICuriosItemHandler> curiosHandler = CuriosApi.getCuriosInventory(player).resolve();

        curiosHandler.ifPresent(handler -> {
            // 检查所有饰品槽位
            handler.getCurios().forEach((id, handlerData) -> {
                for (int i = 0; i < handlerData.getStacks().getSlots(); i++) {
                    ItemStack stack = handlerData.getStacks().getStackInSlot(i);
                    // 检查是否是 dream_finale 饰品
                    if (stack.getItem() == RegistryItem.DREAM_FINALE.get()) {
                        hasCurio.set(true);
                        return;
                    }
                }
            });
        });

        return hasCurio.get();
    }
}
