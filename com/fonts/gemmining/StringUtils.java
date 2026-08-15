package com.fonts.gemmining;


import net.md_5.bungee.api.ChatColor;

public class StringUtils
{
  public static String color(String msg)
  {
    return ChatColor.translateAlternateColorCodes('&', msg);
  }
}
