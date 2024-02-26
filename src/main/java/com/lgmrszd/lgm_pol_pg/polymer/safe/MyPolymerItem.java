package com.lgmrszd.lgm_pol_pg.polymer.safe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public interface MyPolymerItem {
    Item my$getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player);
}
