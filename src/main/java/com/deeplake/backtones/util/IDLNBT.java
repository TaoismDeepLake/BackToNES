package com.deeplake.backtones.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

import static com.deeplake.backtones.util.IDLNBTDef.IDEALLAND;

public class IDLNBT {
	//Handle those which need to be stored into players permanently
	//PlayerData
	//--PERSISTED_NBT_TAG
	//  --IDEALLAND
	//    --KILL_COUNT,etc

	public static CompoundNBT getTagSafe(CompoundNBT tag, String key) {
		if(tag == null) {
			return new CompoundNBT();
		}

		return tag.getCompound(key);
	}

	public static CompoundNBT getPlyrIdlTagSafe(PlayerEntity player) {
		if(player == null) {
			return new CompoundNBT();
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getTagSafe(data, IDEALLAND);

		return idl_data;
	}

	public static CompoundNBT getPlayerIdeallandTagGroupSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getCompound(key);
	}

	public static int[] getPlayerIdeallandIntArraySafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getIntArray(key);
	}

	public static int getPlayerIdeallandIntSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getInt(key);
	}
	public static float getPlayerIdeallandFloatSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getFloat(key);
	}
	public static double getPlayerIdeallandDoubleSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getDouble(key);
	}
	public static boolean getPlayerIdeallandBoolSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getBoolean(key);
	}
	public static String getPlayerIdeallandStrSafe(PlayerEntity player, String key) {
		return getPlyrIdlTagSafe(player).getString(key);
	}
	public static BlockPos getPlayerIdeallandBlockPosSafe(PlayerEntity player, String key) {
		if (player == null)
		{
			return BlockPos.ZERO;
		}

		INBT inbt = getPlyrIdlTagSafe(player).get(key);
		if (inbt instanceof CompoundNBT)
		{
			return NBTUtil.readBlockPos((CompoundNBT) inbt);
		}
		return BlockPos.ZERO;
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, int value) {
		if (player == null)
		{
			return;
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.putInt(key, value);

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, int[] value) {
		if (player == null)
		{
			return;
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.putIntArray(key, value);

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, double value) {
		if (player == null)
		{
			return;
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.putDouble(key, value);

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, boolean value) {
		if (player == null)
		{
			return;
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.putBoolean(key, value);

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, String value) {
		if (player == null)
		{
			return;
		}

		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.putString(key, value);

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}

	public static void setPlayerIdeallandTagSafe(PlayerEntity player, String key, BlockPos value) {
		CompoundNBT playerData = player.getPersistentData();
		CompoundNBT data = getTagSafe(playerData, PlayerEntity.PERSISTED_NBT_TAG);
		CompoundNBT idl_data = getPlyrIdlTagSafe(player);

		idl_data.put(key, NBTUtil.writeBlockPos(value));

		data.put(IDEALLAND, idl_data);
		playerData.put(PlayerEntity.PERSISTED_NBT_TAG, data);
	}
}
