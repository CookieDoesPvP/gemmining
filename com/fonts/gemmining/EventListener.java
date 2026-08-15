package com.fonts.gemmining;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Main.getInstance().loadAccount(p);
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack() == null) {
			return;
		}

		ItemStack item = e.getItemDrop().getItemStack();
		ItemStack item1 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.stone").getValues(true));
		ItemStack item2 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_stone").getValues(true));
		ItemStack item3 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.shiny_stone").getValues(true));
		ItemStack item4 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_shiny_stone").getValues(true));
		
		String name = item.getItemMeta().getDisplayName();
		
		if (name == null) {
			return;
		}
		
		if (!(name.equals(ChatColor.translateAlternateColorCodes('&',item1.getItemMeta().getDisplayName())) 
				|| name.equals(ChatColor.translateAlternateColorCodes('&',item2.getItemMeta().getDisplayName()))
				|| name.equals(ChatColor.translateAlternateColorCodes('&',item3.getItemMeta().getDisplayName()))
				|| name.equals(ChatColor.translateAlternateColorCodes('&',item4.getItemMeta().getDisplayName())))) {
			return;
			
		}
		
		
		Player p = e.getPlayer();
		ItemMeta m = item.getItemMeta();
		List<String> lore = m.getLore();
		if (lore.get(lore.size()-1).equals(p.getName())) {
			return;
		}
		lore.add(p.getName());
		m.setLore(lore);
		item.setItemMeta(m);
	}
	
	@EventHandler
	public void onInvMoveEvent(InventoryPickupItemEvent e) {
		if (e.getInventory().getType() != InventoryType.HOPPER) {
			return;
		}
		
		Hopper hopper = (Hopper) e.getInventory().getHolder();
		Block h = (Block) hopper.getBlock();
		Item i = e.getItem();	
		ItemStack stack = i.getItemStack();
		
				for (String key : Main.getInstance().getConfig().getStringList("locations")) {
					String[] s = key.split(",");
					//Bukkit.broadcastMessage(s[0]+s[1]+s[2]+s[3]);
					
					if (h.getLocation().getWorld().getName().equals(s[0]) 
							&& h.getLocation().getBlockX() == Integer.valueOf(s[1]) 
							&& h.getLocation().getBlockY() == Integer.valueOf(s[2])
							&& h.getLocation().getBlockZ() == Integer.valueOf(s[3])) {
						
						Player p = Bukkit.getPlayer((stack.getItemMeta().getLore().get(stack.getItemMeta().getLore().size()-1)));
						
						double foo = Math.random() * 100;
						ItemStack item = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.stone").getValues(true));
						ItemStack item2 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_stone").getValues(true));
						ItemStack item3 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.shiny_stone").getValues(true));
						ItemStack item4 = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_shiny_stone").getValues(true));
						ItemStack gem = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.gem").getValues(true));
						ItemMeta m = gem.getItemMeta();
						m.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getDisplayName()));
						List<String> lore = m.getLore();
						for (String gems : lore) {
							lore.add(ChatColor.translateAlternateColorCodes('&', gems));
							lore.remove(gems);
						}
						m.setLore(lore);
						gem.setItemMeta(m);
						
						if (stack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',item.getItemMeta().getDisplayName()))) {
							i.remove();
							if (foo <= 30) {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Sift successful! You have recieved a gem!"));
								p.getInventory().addItem(gem);
							} else {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Nothing this time!"));
							}
						} else if (stack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',item2.getItemMeta().getDisplayName())))  {
							i.remove();
							if (foo <= 50) {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Sift successful! You have recieved a gem!"));
								p.getInventory().addItem(gem);
							} else {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Nothing this time!"));
							}
							
						} else if (stack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',item3.getItemMeta().getDisplayName())))  {
							i.remove();
							if (foo <= 70) {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Sift successful! You have recieved a gem!"));
								p.getInventory().addItem(gem);
							} else {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Nothing this time!"));
							}
						} else if (stack.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',item4.getItemMeta().getDisplayName()))) {
							i.remove();
							if (foo <= 90) {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Sift successful! You have recieved a gem!"));
								p.getInventory().addItem(gem);
							} else {
								p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Nothing this time!"));
							}
						}  
						
				    }

				}


			}
		
	
	 
	@EventHandler
	public void onBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Gems gems = new Gems(p);
		Block b = e.getBlock();

		if (gems.getGems() >= gems.getRequired()) {
			gems.setGems(0);
			gems.addLevel();
			p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have LEVELLED up. Your new level is: &b" + gems.getLevel()));				
			
			if (gems.getLevel() == 50) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("level-rewards.50").replace("{PLAYER}", p.getName()));
			} else if (gems.getLevel() == 100) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("level-rewards.100").replace("{PLAYER}", p.getName()));
			} else if (gems.getLevel() == 150) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("level-rewards.150").replace("{PLAYER}", p.getName()));
			} else if (gems.getLevel() == 200) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("level-rewards.200").replace("{PLAYER}", p.getName()));
			}
			
			double foo = Math.random() * 100;
			Main.sendDebugMessage("random num is: " + foo);
			if (foo >= Main.getInstance().getConfig().getInt("odds.chance_for_stone")) {
				ItemStack item = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.stone").getValues(true));
				ItemMeta m = item.getItemMeta();
				m.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getDisplayName()));
				List<String> lore = m.getLore();
				for (String s : lore) {
					lore.add(ChatColor.translateAlternateColorCodes('&', s));
					lore.remove(s);
				}
				m.setLore(lore);
				item.setItemMeta(m);				
				
				if(e.getPlayer().getInventory().firstEmpty() == -1){
					p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Your inventory is full, dropping reward to ground.."));
					World w = p.getWorld();
					w.dropItem(p.getLocation(), item);
					return;
				}
				
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have been given one &bstone."));
				p.getInventory().addItem(item);
			
			
			} else if (foo >= Main.getInstance().getConfig().getInt("odds.chance_for_largestone")) {
				ItemStack item = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_stone").getValues(true));
				ItemMeta m = item.getItemMeta();
				m.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getDisplayName()));
				List<String> lore = m.getLore();
				for (String s : lore) {
					lore.add(ChatColor.translateAlternateColorCodes('&', s));
					lore.remove(s);
				}
				m.setLore(lore);
				item.setItemMeta(m);
				
				if(e.getPlayer().getInventory().firstEmpty() == -1){
					p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Your inventory is full, dropping reward to ground.."));
					World w = p.getWorld();
					w.dropItem(p.getLocation(), item);
					return;
				}
				
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have been given one &blarge stone."));
				p.getInventory().addItem(item);
			    
			} else if (foo >= Main.getInstance().getConfig().getInt("odds.chance_for_shinystone")) {
				ItemStack item = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.shiny_stone").getValues(true));
				ItemMeta m = item.getItemMeta();
				m.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getDisplayName()));
				List<String> lore = m.getLore();
				for (String s : lore) {
					lore.add(ChatColor.translateAlternateColorCodes('&', s));
					lore.remove(s);
				}
				m.setLore(lore);
				item.setItemMeta(m);
				
				if(e.getPlayer().getInventory().firstEmpty() == -1){
					p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Your inventory is full, dropping reward to ground.."));
					World w = p.getWorld();
					w.dropItem(p.getLocation(), item);
					return;
				}
				
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have been given one &bshiny stone."));
				p.getInventory().addItem(item);
			
			} else if (foo >= Main.getInstance().getConfig().getInt("odds.chance_for_largeshinystone")) {
				ItemStack item = ItemStack.deserialize(Main.getInstance().getConfig().getConfigurationSection("items.large_shiny_stone").getValues(true));
				ItemMeta m = item.getItemMeta();
				m.setDisplayName(ChatColor.translateAlternateColorCodes('&', m.getDisplayName()));
				List<String> lore = m.getLore();
				for (String s : lore) {
					lore.add(ChatColor.translateAlternateColorCodes('&', s));
					lore.remove(s);
				}
				m.setLore(lore);
				item.setItemMeta(m);
				
				
				if(e.getPlayer().getInventory().firstEmpty() == -1){
					p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7Your inventory is full, dropping reward to ground.."));
					World w = p.getWorld();
					w.dropItem(p.getLocation(), item);
					return;
					}
				
				p.sendMessage(StringUtils.color("&5[&dGem Mining&5] &7You have been given one &blarge shiny stone."));
				p.getInventory().addItem(item);
				
			}
		}
		if (e.getPlayer().getItemInHand() != null) {
			Material fucku = e.getPlayer().getItemInHand().getType();
			if (fucku == Material.DIAMOND_PICKAXE || fucku == Material.IRON_PICKAXE || fucku == Material.GOLD_PICKAXE || fucku == Material.STONE_PICKAXE || fucku == Material.WOOD_PICKAXE) {
				if (e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
					return;
				}	
			}
		}
		
		if (e.isCancelled()) {
			return;
		}
		
		if (Main.worldguard) {
			if (!Main.getWorldGuard().canBuild(p, e.getBlock())) {
				return;
			}
			
		}

		
		switch (b.getType()) {
		
		case STONE:
			gems.addGems(1);
		    Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		case COAL_ORE:
			gems.addGems(5);
			Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		//case IRON_ORE:
			//gems.addGems(19);
			//Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			//break;
		case REDSTONE_ORE:
		case GLOWING_REDSTONE_ORE:
			gems.addGems(20);
			Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		case LAPIS_ORE:
			gems.addGems(20);
			Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		//case GOLD_ORE:
			//gems.addGems(40);
			////Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
		//	break;
		case DIAMOND_ORE:
			gems.addGems(50);
			Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		case EMERALD_ORE:
			gems.addGems(95);
			Main.sendDebugMessage("&c&lDEBUG &eAdded GEMS for " + p.getName() + " New Balance: " + gems.getGems() + " GEMS.");
			break;
		default:
			break;
		
		}
		
		
		
		
		
	}
	
}
