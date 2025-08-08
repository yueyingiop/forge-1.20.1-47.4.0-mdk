package com.core.dream_sakura.util;

import java.lang.reflect.Field;

import com.core.dream_sakura.dream_sakura;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;

public class ReflectionUtil {
    private static EntityDataAccessor<Float> FE_GET_HEALTH_DATA = null;
    private static boolean reflectionFailed = false;

    static {
        try {
            // 假设 EntityASMUtil 在 com.example.othermod.util 包中
            Class<?> entityASMUtilClass = Class.forName("com.mega.uom.util.entity.EntityASMUtil");
            
            // 获取 FE_GET_HEALTH_DATA 字段
            Field field = entityASMUtilClass.getDeclaredField("FE_GET_HEALTH_DATA");
            field.setAccessible(true); // 允许访问私有字段
            
            // 获取静态字段的值
            FE_GET_HEALTH_DATA = (EntityDataAccessor<Float>) field.get(null);
            
            dream_sakura.LOGGER.info("Successfully accessed FE_GET_HEALTH_DATA from other mod");
        } catch (ClassNotFoundException e) {
            dream_sakura.LOGGER.error("EntityASMUtil class not found. Is the required mod installed?", e);
            reflectionFailed = true;
        } catch (NoSuchFieldException e) {
            dream_sakura.LOGGER.error("FE_GET_HEALTH_DATA field not found in EntityASMUtil", e);
            reflectionFailed = true;
        } catch (IllegalAccessException e) {
            dream_sakura.LOGGER.error("Failed to access FE_GET_HEALTH_DATA field", e);
            reflectionFailed = true;
        }
    }

    public static void setHealthData(LivingEntity entity, float value) {
        if (reflectionFailed || FE_GET_HEALTH_DATA == null) {
            return;
        }
        
        try {
            entity.getEntityData().set(FE_GET_HEALTH_DATA, value);
        } catch (Exception e) {
            dream_sakura.LOGGER.error("Failed to set FE_GET_HEALTH_DATA value", e);
        }
    }
}
