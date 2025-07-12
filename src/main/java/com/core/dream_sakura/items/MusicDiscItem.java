package com.core.dream_sakura.items;

import java.util.function.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

public class MusicDiscItem extends RecordItem {

    public MusicDiscItem(
        int comparatorValue, 
        Supplier<SoundEvent> soundSupplier, 
        Properties builder,
        int lengthInTicks
    ) {
        super(comparatorValue, soundSupplier, builder, lengthInTicks);
    }
}
