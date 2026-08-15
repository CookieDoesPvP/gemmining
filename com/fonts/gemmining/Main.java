package com.fonts.gemmining;

import java.awt.List;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Main extends JavaPlugin {
	
	 public static Main instance = null;
	 public static boolean debug = false;
	 public static boolean worldguard = false;
	
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		
		if (getWorldGuard() != null) {
			worldguard = true;
			Bukkit.getConsoleSender().sendMessage("[Gem Mining] Hooked into WorldGuard.");
		} else {
			Bukkit.getConsoleSender().sendMessage("[Gem Mining] WorldGuard Dependency Missing.. ");
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			loadAccount(p);
		}
		
		/*Dye l = new Dye();
		l.setColor(DyeColor.BLUE);
		ItemStack test = l.toItemStack();
		ItemMeta meta = test.getItemMeta();
		meta.setDisplayName("&aStone");
		meta.setLore(Arrays.asList("Testing"));
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		test.setItemMeta(meta);
		getConfig().set("items.gem", test.serialize()); */
		
		
		//item = ItemStack.deserialize(getConfig().getConfigurationSection("test.stone").getValues(true));
		
		
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		getCommand("gem").setExecutor(new CommandHandler());
	}
	
	public void onDisable() {
		Main.getInstance().saveConfig();
	}
	
	public static Main getInstance() {
	    return instance;
	}	
	
	  public void loadAccount(Player p)
	  {
	    Gems gem = new Gems(p);
	    if (!getConfig().contains("ACCOUNTS." + p.getUniqueId() + ".GEMS"))
	    {
	      gem.setGems(0);
	      gem.setLevel(1);
	      sendDebugMessage("&c&lDEBUG &eCreated new account for " + p.getName() + " with balance of 0 gems, level 1.");
	      Main.getInstance().saveConfig();
	      return;
	    }
	    
	    int xp = getConfig().getInt("ACCOUNTS." + p.getUniqueId() + ".GEMS");
	    int level = getConfig().getInt("ACCOUNTS." + p.getUniqueId() + ".LEVEL");
	    sendDebugMessage("&c&lDEBUG &eLoaded account for " + p.getName() + " with balance of " + xp + " XP. Level: " + level);
	  }

	public static WorldGuardPlugin getWorldGuard()
	{
	  return (WorldGuardPlugin)getPlugin(WorldGuardPlugin.class);
		
	}
	  
	  public static void sendDebugMessage(String s)
	  {
	    for (Player p : Bukkit.getOnlinePlayers())
	    {
	      if (!debug) {
	        return;
	      }
	      if (p.getName().equalsIgnoreCase("Gake")) {
	        p.sendMessage(StringUtils.color(s));
	      }
	      Bukkit.getConsoleSender().sendMessage(StringUtils.color(s));
	    }
	    }
	  
	  
}
