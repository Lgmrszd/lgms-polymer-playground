package com.lgmrszd.lgm_pol_pg.polymer.unsafe;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;

import static com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground.MAGIC_SLAB_BLOCK_ENTITY;

public class MyPolymerInit {
    public static void init() {
        PolymerBlockUtils.registerBlockEntity(MAGIC_SLAB_BLOCK_ENTITY);
    }
}
