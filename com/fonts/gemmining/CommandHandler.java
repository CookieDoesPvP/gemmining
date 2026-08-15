package com.fonts.gemmining;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		Gems gems = new Gems(p);
		
	/*	if (args.length == 0) {
			p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Correct usage: /gem points + /gem setsifter"));
			return false;
		}
		
		
		if (args[0].equalsIgnoreCase("setpoints")) {
			if (!p.hasPermission("*")) {
				p.sendMessage(ChatColor.RED + "You do not have permission to exectute this command.");
				return false;
			}
			
			if (args.length != 3) {
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Correct usage: /gem setpoints <player> <amount>"));
				
			} else {
				if (Bukkit.getPlayer(args[1]) != null) {
						try{
						  Integer.parseInt(args[2]);
						}catch(NumberFormatException e){
						  p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7This is not a valid amount"));
						}
						
						new Gems(Bukkit.getPlayer(args[1])).setGems(Integer.parseInt(args[2]));
						p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Set" + "&b" + args[1] + "'s &7points to &b" + args[2]));
					 
				} else {
					
				}
			}
			
		} */
		
		
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("points")) {
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have &b" + gems.getGems() + " &8/ &b" + gems.getRequired() + " &7gem points. Level (&b" + gems.getLevel() + "&7)"));
			} else if (args[0].equalsIgnoreCase("setsifter")) {
				if (p.hasPermission("*")) {
					if (p.getTargetBlock((HashSet<Byte>) null, 50).getType().equals(Material.HOPPER)) {
						Block b = p.getTargetBlock((HashSet<Byte>) null, 50);
						Location loc = b.getLocation();
						
						if (Main.getInstance().getConfig().getStringList("locations").contains(loc.getWorld().getName() + "," + loc.getBlockX() + "," +  loc.getBlockY() + "," +  loc.getBlockZ() + "," + loc.getPitch() + "," + loc.getYaw())) {
							p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7This block is already a sifter."));
						} else {
							List<String> list = Main.getInstance().getConfig().getStringList("locations");
							
							list.add((loc.getWorld().getName() + "," + loc.getBlockX() + "," +  loc.getBlockY() + "," +  loc.getBlockZ() + "," + loc.getPitch() + "," + loc.getYaw()));
							Main.getInstance().getConfig().set("locations", list);
							p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Block set as sifter."));
						}
						
					     Main.getInstance().saveConfig();
						
					} else {
						p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &cYou must be looking at a hopper to set it as a SIFTER"));
					}
				} else {
					p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You do not have permission."));
				}
				
			} 
			
			
		} else {
			p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Correct usage: /gem points + /gem setsifter"));
		}
		
		
		
		return false;
	}

}
