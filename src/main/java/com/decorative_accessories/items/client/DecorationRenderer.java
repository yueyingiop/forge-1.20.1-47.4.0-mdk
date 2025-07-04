package com.decorative_accessories.items.client;

import com.decorative_accessories.items.DecorationItem;

import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DecorationRenderer extends GeoItemRenderer<DecorationItem> {
    public DecorationRenderer() {
        super(new DecorationModel());
    }
}
