package com.core.dream_sakura.format;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.ParametersAreNonnullByDefault;

import org.joml.Matrix4f;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Tuple;

@ParametersAreNonnullByDefault
// @MethodsReturnNonnullByDefault
public class TrueRGBSimpleRenderer extends Font {
    private static final Map<ResourceLocation, ResourceLocation> FONT_ALIASES = Util.make(Maps.newHashMap(), (map) -> {
        map.put(ResourceLocation.fromNamespaceAndPath("minecraft", "alt"), ResourceLocation.fromNamespaceAndPath("minecraft", "default"));
        map.put(ResourceLocation.fromNamespaceAndPath("minecraft", "uniform"), ResourceLocation.fromNamespaceAndPath("minecraft", "default"));
    });
    private float posX;
    private int preColor = -1;

    public TrueRGBSimpleRenderer(Font font) {
        super(extractFonts(font), false);
    }

    @SuppressWarnings("unchecked")
    private static Function<ResourceLocation, FontSet> extractFonts(Font font) {
        try {
            Field fontsField = Font.class.getDeclaredField("fonts");
            fontsField.setAccessible(true);
            return (Function<ResourceLocation, FontSet>) fontsField.get(font);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("无法访问 Font.fonts 字段", e);
        }
    }

    public int drawString(GuiGraphics guiGraphics, String text, float x, float y, int color, boolean dropShadow) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        List<Tuple<String, RGBSettings>> settings = RGBSettings.split(text);
        int cColor = color;
        this.posX = x;

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        for (Tuple<String, RGBSettings> tuple : settings) {
            String s = tuple.getA();
            RGBSettings set = tuple.getB();

            if (s.isEmpty())
                continue;

            if (set.isFixedColor()) {
                cColor = Optional.ofNullable(set.getColorAt(0)).map(IColor::toInt).orElse(cColor);
                renderFormattedText(guiGraphics, set, s, this.posX, y, cColor, dropShadow);
                continue;
            }

            // 渐变颜色处理
            for (int i = 0; i < s.length(); i++) {
                IColor colorAt = set.getColorAt(i);
                if (colorAt != null) {
                    cColor = colorAt.toInt();
                }
                String charStr = String.valueOf(s.charAt(i));
                renderFormattedText(guiGraphics, set, charStr, this.posX, y, cColor, dropShadow);
            }
        }
        poseStack.popPose();
        return (int) this.posX;
    }

    private void renderFormattedText(
            GuiGraphics guiGraphics,
            RGBSettings settings,
            String text, float x, float y,
            int color, boolean dropShadow) {
        MutableComponent component = Component.literal(text)
                .withStyle(style -> {
                    Style newStyle = style;

                    if (settings.getBold() != null && settings.getBold()) {
                        newStyle = newStyle.withBold(true);
                    }
                    if (settings.getItalic() != null && settings.getItalic()) {
                        newStyle = newStyle.withItalic(true);
                    }
                    if (settings.getUnderlined() != null && settings.getUnderlined()) {
                        newStyle = newStyle.withUnderlined(true);
                    }
                    if (settings.getStrikethrough() != null && settings.getStrikethrough()) {
                        newStyle = newStyle.withStrikethrough(true);
                    }
                    if (settings.getObfuscated() != null && settings.getObfuscated()) {
                        newStyle = newStyle.withObfuscated(true);
                    }
                    return newStyle;
                })
                .withStyle(style -> style.withColor(color));

        FormattedCharSequence sequence = component.getVisualOrderText();
        guiGraphics.drawString(this, sequence, (int) x, (int) y, color, dropShadow);
        this.posX += this.width(text);
    }

    public int drawString(GuiGraphics guiGraphics, String text, float x, float y, int color) {
        return drawString(guiGraphics, text, x, y, color, false);
    }

    public int getStringWidth(String text) {
        String plainText = RGBSettings.split(text).stream()
                .map(Tuple::getA)
                .collect(Collectors.joining());
        return super.width(plainText);
    }

    @Override
    public int drawInBatch(FormattedCharSequence sequence, float x, float y, int color,
            boolean dropShadow, Matrix4f matrix4f, MultiBufferSource buffer, DisplayMode displayMode, int backgroundColor,
            int packedLight) {
        return super.drawInBatch(sequence, x, y, color, dropShadow, matrix4f, buffer, displayMode, backgroundColor, packedLight);
    }

    
}
