package com.lemon.cookieextras.util;

import com.lemon.cookieextras.CookieExtras;
import com.lemon.cookieextras.item.ModItems;
import com.lemon.cookieextras.item.custom.SteelArrowItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class ModItemModelProperties {
    public static void makeBow(Item item) {
        ItemModelsProperties.registerProperty(item, new ResourceLocation(CookieExtras.MOD_ID, "pull"), (stack, world, living) -> {
            if (living == null) {
                return 0.0F;
            } else {
                return living.getActiveItemStack() != stack ? 0.0F : (float)(stack.getUseDuration() - living.getItemInUseCount()) / 20.0F;
            }
        });

        ItemModelsProperties.registerProperty(item, new ResourceLocation(CookieExtras.MOD_ID,"pulling"), (stack, world, living) -> {

            if (living != null && living.isHandActive() && living.getActiveItemStack() == stack) {
                return 1.0F;
            } else {
                return 0.0F;
            }
        });


        ItemModelsProperties.registerProperty(item, new ResourceLocation(CookieExtras.MOD_ID,"steel"), (stack, world, living) -> {
            if (living instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) living;
                if (playerEntity.findAmmo(stack).getItem() instanceof SteelArrowItem) {
                    return 1.0f;
                }
            }
            return 0.0f;
        });
    }
}
