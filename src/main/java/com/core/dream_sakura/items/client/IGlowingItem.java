package com.core.dream_sakura.items.client;

/**
 * 物品发光效果接口
 * 实现此接口的物品可以自定义发光颜色
 */
public interface IGlowingItem {
    /**
     * 获取物品的发光颜色
     * @return float数组，包含RGB三个分量，值范围0.0-1.0
     */
    float[] getGlowColor();
    
    /**
     * 获取发光强度
     * @return 发光强度，默认为1.0
     */
    default float getGlowIntensity() {
        return 1.0f;
    }
} 