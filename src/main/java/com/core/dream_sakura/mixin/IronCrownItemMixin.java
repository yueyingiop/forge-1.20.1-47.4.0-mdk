package com.core.dream_sakura.mixin;

import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.entity.armor.GoldCrownModel;
import io.redspace.ironsspellbooks.item.armor.ExtendedArmorItem;
import io.redspace.ironsspellbooks.item.armor.ExtendedArmorMaterials;
import io.redspace.ironsspellbooks.item.armor.GoldCrownArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import com.google.common.collect.Multimap;

@Mixin(GoldCrownArmorItem.class)
public class IronCrownItemMixin extends ExtendedArmorItem implements ICurioItem {

    public IronCrownItemMixin(ArmorItem.Type slot, Item.Properties settings) {
        super(ExtendedArmorMaterials.DEV, slot, settings);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return "head".equals(slotContext.identifier());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid,
            ItemStack stack) {
        return getAttributeModifiers(EquipmentSlot.HEAD, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new GenericCustomArmorRenderer<>(new GoldCrownModel());
    }
}
