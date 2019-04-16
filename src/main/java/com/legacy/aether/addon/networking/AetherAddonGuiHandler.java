package com.legacy.aether.addon.networking;

import com.legacy.aether.AetherLogger;
import com.legacy.aether.addon.client.gui.GuiAetherionChest;
import com.legacy.aether.addon.client.gui.inventory.GuiAetherCrafting;
import com.legacy.aether.addon.containers.ContainerSkyrootWorkbench;
import com.legacy.aether.addon.tile_entities.TileEntityAetherionChest;
import com.legacy.aether.networking.AetherGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @see AetherGuiHandler
 */
public class AetherAddonGuiHandler implements IGuiHandler {

	public static final int crafting = 1, aetherion_chest = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == crafting) {
			return new ContainerSkyrootWorkbench(player.inventory, world, new BlockPos(x, y, z));
		} else if(ID == aetherion_chest) {
			AetherLogger.log.info("[server] Opening aetherion chest");
			return new ContainerChest(player.inventory, ((TileEntityAetherionChest) world.getTileEntity(new BlockPos(x, y, z))).getInventoryFor(player), player);
		}

		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == crafting) {
			return new GuiAetherCrafting(player.inventory, world);
		} else if (ID == aetherion_chest) {
			AetherLogger.log.info("[client] Opening aetherion chest");
			return new GuiAetherionChest(player.inventory, (TileEntityAetherionChest) world.getTileEntity(new BlockPos(x, y, z)));
		}

		return null;
	}
}
