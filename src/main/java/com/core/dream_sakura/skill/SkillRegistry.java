package com.core.dream_sakura.skill;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.core.dream_sakura.dream_sakura;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = dream_sakura.MODID)
public class SkillRegistry {
    private static final List<SkillBinding> bindings = new CopyOnWriteArrayList<>();

    // 注册新技能
    public static void registerBinding(SkillBinding binding) {
        if (binding != null) {
            bindings.add(binding);
        }
    }
    
    // 获取所有绑定
    public static List<SkillBinding> getBindings() {
        return bindings;
    }
    
    // 清除所有绑定（用于重载）
    public static void clearBindings() {
        bindings.clear();
    }
}
