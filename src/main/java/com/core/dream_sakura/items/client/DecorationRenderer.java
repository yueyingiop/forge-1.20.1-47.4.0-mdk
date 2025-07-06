package com.core.dream_sakura.items.client;

import com.core.dream_sakura.items.DecorationItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DecorationRenderer extends GeoItemRenderer<DecorationItem> {
    public DecorationRenderer() {
        super(new DecorationModel());
    }
}
