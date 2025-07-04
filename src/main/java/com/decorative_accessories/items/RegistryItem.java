package com.decorative_accessories.items;

import com.decorative_accessories.decorative_accessories.decorative_accessories;
// import com.decorative_accessories.items.DecorationItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryItem {
    // 创建注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, decorative_accessories.MODID);
    
    // 注册饰品
    public static final RegistryObject<Item> DECORATION_ITEM = ITEMS.register(
        "test_decoration", 
        () -> new DecorationItem(
            new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.UNCOMMON)
        )
    );
}
