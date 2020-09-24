package com.gildedgames.the_aether.addon.tile_entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.gildedgames.the_aether.addon.blocks.BlockAetherionChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

/**
 * @see TileEntityChest
 */
public class TileEntityAetherionChest extends TileEntity implements IInteractionObject, ITickable {
	private HashMap<UUID, ChestInventory> contents = new HashMap<>();
	/** The current angle of the lid (between 0 and 1) */
	public float lidAngle;
	/** The angle of the lid last tick */
	public float prevLidAngle;
	/** The number of players currently using this chest */
	public int numPlayersUsing;
	/** Server sync counter (once per 20 ticks) */
	private int ticksSinceSync;
	private String customName;

	public IInventory getInventoryFor(EntityPlayer player) {
		ChestInventory inv = contents.get(player.getUniqueID());
		if (inv == null) {
			contents.put(player.getUniqueID(), inv = new ChestInventory());
		}
		return inv;
	}
	
	public Map<UUID, ChestInventory> getContents() {
		return contents;
	}

	/**
	 * Get the name of this object. For players this returns their username
	 */
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.aetherion_chest";
	}

	@Override
	public boolean hasCustomName() {
		return customName != null && !customName.isEmpty();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerChest(playerInventory, getInventoryFor(playerIn), playerIn);
	}

	@Override
	public String getGuiID() {
		return "minecraft:chest";
	}
	
	public boolean isEmpty() {
		if (!contents.isEmpty()) {
			for (ChestInventory list : contents.values()) {
				if (!list.isEmpty())
					return false;
			}
		}
		
		return true;
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	@SuppressWarnings("unused")
	public void update() {
		int i = this.pos.getX();
		int j = this.pos.getY();
		int k = this.pos.getZ();
		++this.ticksSinceSync;

		if (!this.world.isRemote && this.numPlayersUsing != 0
				&& (this.ticksSinceSync + i + j + k) % 200 == 0) {
			this.numPlayersUsing = 0;
			float f = 5.0F;

			for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class,
					new AxisAlignedBB((double) ((float) i - 5.0F), (double) ((float) j - 5.0F),
							(double) ((float) k - 5.0F), (double) ((float) (i + 1) + 5.0F),
							(double) ((float) (j + 1) + 5.0F),
							(double) ((float) (k + 1) + 5.0F)))) {
				if (entityplayer.openContainer instanceof ContainerChest) {
					IInventory iinventory = ((ContainerChest) entityplayer.openContainer)
							.getLowerChestInventory();

					if (iinventory == this) {
						++this.numPlayersUsing;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		float f1 = 0.1F;

		if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
			double d1 = (double) i + 0.5D;
			double d2 = (double) k + 0.5D;

			this.world.playSound((EntityPlayer) null, d1, (double) j + 0.5D, d2,
					SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F,
					this.world.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F
				|| this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
			float f2 = this.lidAngle;

			if (this.numPlayersUsing > 0) {
				this.lidAngle += 0.1F;
			} else {
				this.lidAngle -= 0.1F;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f3 = 0.5F;

			if (this.lidAngle < 0.5F && f2 >= 0.5F) {
				double d3 = (double) i + 0.5D;
				double d0 = (double) k + 0.5D;

				this.world.playSound((EntityPlayer) null, d3, (double) j + 0.5D, d0,
						SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F,
						this.world.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}
	
	public int getSizeInventory() {
		return 27;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.contents = new HashMap<>();

		NBTTagCompound players = compound.getCompoundTag("Inventories");
		
		for(String key : players.getKeySet()) {
			NBTTagList nbttaglist = players.getTagList(key, 10);
			if (nbttaglist.tagCount() != 0) {
				NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
				loadAllItems(nbttaglist, list);
				this.contents.put(UUID.fromString(key), new ChestInventory(list));
			}
		}
		
		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
	}

	private static void loadAllItems(NBTTagList nbttaglist, NonNullList<ItemStack> list) {
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < list.size()) {
				list.set(j, new ItemStack(nbttagcompound));
			}
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagCompound players = new NBTTagCompound();
		
		for (Map.Entry<UUID, ChestInventory> entry : contents.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				NBTTagList items = saveAllItems(entry.getValue().items);
				players.setTag(entry.getKey().toString(), items);
			}
		}
		
		compound.setTag("Inventories", players);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}

		return compound;
	}
	
	private static NBTTagList saveAllItems(NonNullList<ItemStack> list) {
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < list.size(); ++i) {
			ItemStack itemstack = list.get(i);

			if (!itemstack.isEmpty()) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				itemstack.writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		return nbttaglist;
	}

	public class ChestInventory implements IInventory {
		private NonNullList<ItemStack> items;
		
		public ChestInventory(NonNullList<ItemStack> items) {
			this.items = items;
		}
		
		public ChestInventory() {
			this.items = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
		}

		@Override
		public String getName() {
			return TileEntityAetherionChest.this.getName();
		}

		@Override
		public boolean hasCustomName() {
			return TileEntityAetherionChest.this.hasCustomName();
		}

		@Override
		public ITextComponent getDisplayName() {
			return TileEntityAetherionChest.this.getDisplayName();
		}

		@Override
		public int getSizeInventory() {
			return TileEntityAetherionChest.this.getSizeInventory();
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack itemstack : this.items) {
				if (!itemstack.isEmpty()) {
					return false;
				}
			}

			return true;
		}

		@Override
		public ItemStack getStackInSlot(int index) {
			return items.get(index);
		}

		@Override
		public ItemStack decrStackSize(int index, int count) {
			ItemStack itemstack = ItemStackHelper.getAndSplit(this.items, index, count);

			if (!itemstack.isEmpty()) {
				markDirty();
			}

			return itemstack;
		}

		@Override
		public ItemStack removeStackFromSlot(int index) {
			return ItemStackHelper.getAndRemove(this.items, index);
		}

		@Override
		public void setInventorySlotContents(int index, ItemStack stack) {
			this.items.set(index, stack);

	        if (stack.getCount() > this.getInventoryStackLimit()) {
	            stack.setCount(this.getInventoryStackLimit());
	        }

	        this.markDirty();
		}

		@Override
		public int getInventoryStackLimit() {
			return 64;
		}

		@Override
		public void markDirty() {
			TileEntityAetherionChest.this.markDirty();
		}

		@Override
		public boolean isUsableByPlayer(EntityPlayer player) {
			if (world.getTileEntity(pos) != TileEntityAetherionChest.this) {
				return false;
			} else {
				return player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D;
			}
		}

		@Override
		public void openInventory(EntityPlayer player) {
			if (!player.isSpectator()) {
				if (numPlayersUsing < 0) {
					numPlayersUsing = 0;
				}

				++numPlayersUsing;
				world.addBlockEvent(pos, getBlockType(), 1, numPlayersUsing);
				world.notifyNeighborsOfStateChange(pos, getBlockType(), false);
			}
		}

		@Override
		public void closeInventory(EntityPlayer player) {
			if (!player.isSpectator() && getBlockType() instanceof BlockAetherionChest) {
				--numPlayersUsing;
				world.addBlockEvent(pos, getBlockType(), 1, numPlayersUsing);
				world.notifyNeighborsOfStateChange(pos, getBlockType(), false);
			}
		}

		@Override
		public boolean isItemValidForSlot(int index, ItemStack stack) {
			return true;
		}

		@Override
		public int getField(int id) {
			return 0;
		}

		@Override
		public void setField(int id, int value) { }

		@Override
		public int getFieldCount() {
			return 0;
		}

		@Override
		public void clear() {
			items.clear();
		}
	}

}
