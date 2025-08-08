package com.core.dream_sakura.mixin;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.core.dream_sakura.items.RegistryItem;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

@Mixin(Player.class)
public abstract class PlayerFlightMixin {

    @Inject(method = "onUpdateAbilities", at = @At("HEAD"), cancellable = true)
    private void forceFlight(CallbackInfo ci) {
        Player player = (Player)(Object)this;
        // 检查是否佩戴 dream_finale
        if (player.getInventory().contains(new ItemStack(RegistryItem.DREAM_FINALE.get()))) {
            player.getAbilities().mayfly = true;
            ci.cancel(); // 阻止后续修改
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { 
        Player player = (Player)(Object) this;
        boolean hasDreamFinale = hasDreamFinaleCurio(player);
        
        if (hasDreamFinale) {
            cir.cancel();
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
