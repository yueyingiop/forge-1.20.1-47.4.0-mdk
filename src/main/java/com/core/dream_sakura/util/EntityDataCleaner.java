package com.core.dream_sakura.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;

public class EntityDataCleaner {
    private static final Set<String> LEGITIMATE_PACKAGES = Set.of(
        "net.minecraft",
        "com.core",
        "net.minecraftforge",
        "java.lang"
    );

    public static void cleanEntityData(LivingEntity entity) {
        try {
            Class<?> clazz = entity.getClass();

            while (clazz != null && clazz != Object.class) {
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);

                    // 筛选出 EntityDataAccessor 类型的字段
                    if (!EntityDataAccessor.class.isAssignableFrom(field.getType()) ||
                        !Modifier.isStatic(field.getModifiers())
                    ) {
                        continue;
                    }

                    // 获取字段
                    EntityDataAccessor<?> accessor = (EntityDataAccessor<?>) field.get(null);
                    if (accessor == null) continue; // 忽略 null

                    // 获取字段值
                    Object currentValue = entity.getEntityData().get(accessor); 
                    if (currentValue == null) continue; // 忽略 null

                    String className = field.getDeclaringClass().getName(); // 获取字段所在类名
                    // 检查字段所在类名是否合法
                    if (!isLegitimatePackage(className)) {
                        // 清理数值类型数据
                        if (currentValue instanceof Float) {
                            entity.getEntityData().set((EntityDataAccessor<Float>) accessor, 0.0f);
                        } else if (currentValue instanceof Integer) {
                            entity.getEntityData().set((EntityDataAccessor<Integer>) accessor, 0);
                        } else if (currentValue instanceof Double) {
                            entity.getEntityData().set((EntityDataAccessor<Double>) accessor, 0.0);
                        }
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            dream_sakura.LOGGER.error("Failed to clean entity data: " + e.getMessage());
        }
    }

    // 判断字段是否合法
    private static boolean isLegitimatePackage(String className) {
        for (String pkg : LEGITIMATE_PACKAGES) {
            if (className.startsWith(pkg)) return true;
        }
        return false;
    }
}
