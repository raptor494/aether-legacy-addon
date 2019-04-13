package com.legacy.aether.addon.networking;

import com.legacy.aether.addon.client.gui.inventory.GuiAetherCrafting;
import com.legacy.aether.addon.containers.ContainerSkyrootWorkbench;
import com.legacy.aether.networking.AetherGuiHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @see AetherGuiHandler
 */
public class AetherAddonGuiHandler implements IGuiHandler {

	public static final int crafting = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == crafting) {
			return new ContainerSkyrootWorkbench(player.inventory, world, new BlockPos(x, y, z));
		}

		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == crafting) {
			return new GuiAetherCrafting(player.inventory, world);
		}

		return null;
	}
}
