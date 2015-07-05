package de.janhektor.community.utils;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class NBTItem {

	private ItemStack bukkititem;

	public NBTItem(ItemStack item) {
		this.bukkititem = item.clone();
	}

	public ItemStack getItem() {
		return this.bukkititem;
	}

	public void setString(String key, String s) {
		this.bukkititem = NBTReflection.setString(this.bukkititem, key, s);
	}

	public String getString(String key) {
		return NBTReflection.getString(this.bukkititem, key);
	}

	public void setInteger(String key, Integer i) {
		this.bukkititem = NBTReflection.setInt(this.bukkititem, key, i);
	}

	public Integer getInteger(String key) {
		return NBTReflection.getInt(this.bukkititem, key);
	}

	public void setDouble(String key, Double d) {
		this.bukkititem = NBTReflection.setDouble(this.bukkititem, key, d);
	}

	public Double getDouble(String key) {
		return NBTReflection.getDouble(this.bukkititem, key);
	}

	public void setBoolean(String key, Boolean b) {
		this.bukkititem = NBTReflection.setBoolean(this.bukkititem, key, b);
	}

	public Boolean getBoolean(String key) {
		return NBTReflection.getBoolean(this.bukkititem, key);
	}

	public Boolean hasKey(String key) {
		return NBTReflection.hasKey(this.bukkititem, key);
	}

	// ---------------------- //
	// NBT Classes & Methods  //
	// ---------------------- //
	private static class NBTReflection {

		static String VERSION;

		static {
			String path = Bukkit.getServer().getClass().getPackage().getName();
			VERSION = path.substring(path.lastIndexOf(".") + 1, path.length());
		}

		private static Class<?> getCraftItemstack() {
			try {
				return Class.forName("org.bukkit.craftbukkit" + VERSION + "inventory.CraftItemStack");
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

		private static Object getnewNBTTag() {
			try {
				Class<?> clazz = Class.forName("net.minecraft.server" + VERSION + "NBTTagCompound");
				return clazz.newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

		private static Object setNBTTag(Object NBTTag, Object NMSItem) {
			try {
				Method method;
				method = NMSItem.getClass().getMethod("setTag", NBTTag.getClass());
				method.invoke(NMSItem, NBTTag);
				return NMSItem;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		private static Object getNBTTagCompound(Object nmsitem) {
			Class<?> clazz = nmsitem.getClass();
			Method method;
			try {
				method = clazz.getMethod("getTag");
				return method.invoke(nmsitem);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private static Object getNMSItemStack(ItemStack item) {
			Class<?> clazz = getCraftItemstack();
			Method method;
			try {
				method = clazz.getMethod("asNMSCopy", ItemStack.class);
				return method.invoke(clazz, item);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private static ItemStack getBukkitItemStack(Object item) {
			Class<?> clazz = getCraftItemstack();
			Method method;
			try {
				method = clazz.getMethod("asBukkitCopy", item.getClass());
				Object answer = method.invoke(clazz, item);
				return (ItemStack) answer;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static ItemStack setString(ItemStack item, String key, String Text) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("setString", String.class, String.class);
				method.invoke(nbttag, key, Text);
				nmsitem = setNBTTag(nbttag, nmsitem);
				return getBukkitItemStack(nmsitem);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return item;
		}

		public static String getString(ItemStack item, String key) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("getString", String.class);
				return (String) method.invoke(nbttag, key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public static ItemStack setInt(ItemStack item, String key, Integer i) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("setInt", String.class, int.class);
				method.invoke(nbttag, key, i);
				nmsitem = setNBTTag(nbttag, nmsitem);
				return getBukkitItemStack(nmsitem);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return item;
		}

		public static Integer getInt(ItemStack item, String key) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("getInt", String.class);
				return (Integer) method.invoke(nbttag, key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public static ItemStack setDouble(ItemStack item, String key, Double d) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null)	nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("setDouble", String.class, double.class);
				method.invoke(nbttag, key, d);
				nmsitem = setNBTTag(nbttag, nmsitem);
				return getBukkitItemStack(nmsitem);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return item;
		}

		public static Double getDouble(ItemStack item, String key) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("getDouble", String.class);
				return (Double) method.invoke(nbttag, key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public static ItemStack setBoolean(ItemStack item, String key, Boolean d) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("setBoolean", String.class, boolean.class);
				method.invoke(nbttag, key, d);
				nmsitem = setNBTTag(nbttag, nmsitem);
				return getBukkitItemStack(nmsitem);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return item;
		}

		public static Boolean getBoolean(ItemStack item, String key) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null) nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("getBoolean", String.class);
				return (Boolean) method.invoke(nbttag, key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		public static Boolean hasKey(ItemStack item, String key) {
			Object nmsitem = getNMSItemStack(item);
			Object nbttag = getNBTTagCompound(nmsitem);
			if (nbttag == null)	nbttag = getnewNBTTag();
			Method method;
			try {
				method = nbttag.getClass().getMethod("hasKey", String.class);
				return (Boolean) method.invoke(nbttag, key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

	}

}
