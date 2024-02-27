package com.lgmrszd.lgm_pol_pg;

import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlock;
import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlockEntity;
import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlockItem;
import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTracker;
import com.lgmrszd.lgm_pol_pg.polymer.unsafe.MyPolymerInit;
//import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LgmsPolymerPlayground implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("lgms-polymer-playground");
	public static final String MOD_ID = "lgms-polymer-playground";

	private static boolean polymerLoaded = false;

	public static boolean isPolymerLoaded() {
		return polymerLoaded;
	}

//	public static final Identifier NOTIFY_SERVER = new Identifier(MOD_ID, "notify_server");

	public static final MagicSlabBlock MAGIC_SLAB_BLOCK = new MagicSlabBlock(FabricBlockSettings.create().strength(4.0f));
	public static final BlockEntityType<MagicSlabBlockEntity> MAGIC_SLAB_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier(MOD_ID, "magic_slab_block"),
			FabricBlockEntityTypeBuilder.create(MagicSlabBlockEntity::new, MAGIC_SLAB_BLOCK).build()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "magic_slab_block"), MAGIC_SLAB_BLOCK);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "magic_slab_block"), new MagicSlabBlockItem(MAGIC_SLAB_BLOCK, new FabricItemSettings()));
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "debugger"), new Item(new FabricItemSettings()));

		polymerLoaded = FabricLoader.getInstance().isModLoaded("polymer-core");
		if (polymerLoaded) MyPolymerInit.init();


		ClientTracker.init();
		ModCommands.init();
	}
}