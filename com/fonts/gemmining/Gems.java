package com.fonts.gemmining;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Gems {
	  private Player p;
	  
	  public Gems(Player player)
	  {
	    p = player;	    
	  }
	  
	  
	  public int getGems()
	  {
	    return Main.getInstance().getConfig().getInt("ACCOUNTS." + p.getUniqueId() + ".GEMS");
	    
	  }
	  
	  public void setGems(int i)
	  {
		  Main.getInstance().getConfig().set("ACCOUNTS." + p.getUniqueId() + ".GEMS", i);
		  Main.getInstance().saveConfig();
	  }
	  
	  public void addGems(int i)
	  {
		  Main.getInstance().getConfig().set("ACCOUNTS." + p.getUniqueId() + ".GEMS", (getGems() + i));
		  Main.getInstance().saveConfig();
	  }
	  
	  
	  
	  public Integer getLevel()
	  {
	    return Main.getInstance().getConfig().getInt("ACCOUNTS." + p.getUniqueId() + ".LEVEL");
	  }
	  
	  public void setLevel(int amount)
	  {
		  Main.getInstance().getConfig().set("ACCOUNTS." + p.getUniqueId() + ".LEVEL", amount);
		  Main.getInstance().saveConfig();
	  }
	  
	  public void addLevel()
	  {
		  Main.getInstance().getConfig().set("ACCOUNTS." + p.getUniqueId() + ".LEVEL", (getLevel()+1));
		  Main.getInstance().saveConfig();
	  }
	  
	  
	  public int getRequired() {
		if (p.hasPermission("gemmining.required.10000")) {
			return 10000;
		} else if (p.hasPermission("gemmining.required.11000")) {
			return 11000;
		} else if (p.hasPermission("gemmining.required.12000")) {
			return 12000;
		} else if (p.hasPermission("gemmining.required.13000")) {
			return 13000;
		} else if (p.hasPermission("gemmining.required.14000")) {
			return 14000;
		}
		return 15000;
		  
	  }
	
}