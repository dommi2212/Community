package de.janhektor.community.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class NBTHelper {

	private Object nmsItemStack;
	private Object nbtTagCompound;
	
	private final String VERSION;
	
	private Class<?> getCraftItemStackClass() {
		try {
			return Class.forName("org.bukkit.craftbukkit." + this.VERSION + ".inventory.CraftItemStack");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Object getNewNBTTagCompound() {
		try {
			return Class.forName("net.minecraft.server." + this.VERSION + ".NBTTagCompound").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Object setNBTTagCompound(Object nmsItem, Object nbtTag) {
		Method setTag;
		try {
			setTag = nmsItem.getClass().getMethod("setTag", nbtTag.getClass());
			setTag.invoke(nmsItem, nbtTag);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		return nmsItem;
	}
	
	private Object getNMSItemStack(ItemStack itemStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
			return asNMSCopy.invoke(craftItemStack, itemStack);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ItemStack getBukkitItemStack(Object nmsItemStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", nmsItemStack.getClass());
			return (ItemStack) asBukkitCopy.invoke(craftItemStack, nmsItemStack);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Object getNBTTagCompound(Object nmsItem) {
		Class<?> nmsItemStack = nmsItem.getClass();
		try {
			Method getTag = nmsItemStack.getMethod("getTag");
			return getTag.invoke(nmsItem);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A useful class to handle NBT-Tag-Compounds in ItemStacks
	 * @param itemStack The itemstack
	 */
	public NBTHelper(ItemStack itemStack) {
		this.VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
		this.nmsItemStack = this.getNMSItemStack(itemStack);
		this.nbtTagCompound = this.getNBTTagCompound(this.nmsItemStack);
		
		if(this.nbtTagCompound == null) {
			this.nbtTagCompound = this.getNewNBTTagCompound();
		}
	}
	
	/**
	 * Sets a string with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setString(String key, String value) {
		try {
			Method setString = this.nbtTagCompound.getClass().getMethod("setString", String.class, String.class);
			setString.invoke(this.nbtTagCompound, key, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the string associated with the given key
	 * @param key The key
	 * @return The string
	 */
	public String getString(String key) {
		try {
			Method getString = this.nbtTagCompound.getClass().getMethod("getString", String.class);
			return (String) getString.invoke(this.nbtTagCompound, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a integer with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setInteger(String key, Integer value) {
		try {
			Method setInt = this.nbtTagCompound.getClass().getMethod("setInt", String.class, int.class);
			setInt.invoke(this.nbtTagCompound, key, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the integer associated with the given key
	 * @param key The key
	 * @return The integer
	 */
	public Integer getInteger(String key) {
		try {
			Method getInt = this.nbtTagCompound.getClass().getMethod("getInt", String.class);
			return (Integer) getInt.invoke(this.nbtTagCompound, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a double with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setDouble(String key, Double value) {
		try {
			Method setDouble = this.nbtTagCompound.getClass().getMethod("setDouble", String.class, double.class);
			setDouble.invoke(this.nbtTagCompound, key, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the double associated with the given key
	 * @param key The key
	 * @return The double
	 */
	public Double getDouble(String key) {
		try {
			Method getDouble = this.nbtTagCompound.getClass().getMethod("getDouble", String.class);
			return (Double) getDouble.invoke(this.nbtTagCompound, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a boolean with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setBoolean(String key, Boolean value) {
		try {
			Method setBoolean = this.nbtTagCompound.getClass().getMethod("setBoolean", String.class, boolean.class);
			setBoolean.invoke(this.nbtTagCompound, key, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the boolean associated with the given key
	 * @param key The key
	 * @return The boolean
	 */
	public Boolean getBoolean(String key) {
		try {
			Method getBoolean = this.nbtTagCompound.getClass().getMethod("getBoolean", String.class);
			return (Boolean) getBoolean.invoke(this.nbtTagCompound, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a byte with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setByte(String key, Byte value) {
		try {
			Method setByte = this.nbtTagCompound.getClass().getMethod("setByte", String.class, byte.class);
			setByte.invoke(this.nbtTagCompound, key, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the byte associated with the given key
	 * @param key The key
	 * @return The byte
	 */
	public Byte getByte(String key) {
		try {
			Method getByte = this.nbtTagCompound.getClass().getMethod("getByte", String.class);
			return (Byte) getByte.invoke(this.nbtTagCompound, key);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a float with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setFloat(String key, Float value) {
		try {
			Method setFloat = this.nbtTagCompound.getClass().getMethod("setFloat", String.class, float.class);
			setFloat.invoke(this.nbtTagCompound, key, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the float associated with the given key
	 * @param key The key
	 * @return The byte
	 */
	public Float getFloat(String key) {
		try {
			Method getFloat = this.nbtTagCompound.getClass().getMethod("getFloat", String.class);
			return (Float) getFloat.invoke(this.nbtTagCompound, key);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a long with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setLong(String key, Long value) {
		try {
			Method setLong = this.nbtTagCompound.getClass().getMethod("setLong", String.class, long.class);
			setLong.invoke(this.nbtTagCompound, key, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the long associated with the given key
	 * @param key The key
	 * @return The byte
	 */
	public Long getLong(String key) {
		try {
			Method getLong = this.nbtTagCompound.getClass().getMethod("getLong", String.class);
			return (Long) getLong.invoke(this.nbtTagCompound, key);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets a short with the key and value in the NBT tag
	 * @param key The key
	 * @param value The value
	 * @return The NBTModifier itself
	 */
	public NBTHelper setShort(String key, Short value) {
		try {
			Method setShort = this.nbtTagCompound.getClass().getMethod("setShort", String.class, short.class);
			setShort.invoke(this.nbtTagCompound, key, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Gets the short associated with the given key
	 * @param key The key
	 * @return The byte
	 */
	public Short getShort(String key) {
		try {
			Method getShort = this.nbtTagCompound.getClass().getMethod("getShort", String.class);
			return (Short) getShort.invoke(this.nbtTagCompound, key);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Modifies the itemstack
	 * @return The modified itemstack
	 */
	public ItemStack modify() {
		this.setNBTTagCompound(this.nmsItemStack, this.nbtTagCompound);
		return this.getBukkitItemStack(this.nmsItemStack);
	}
	
}