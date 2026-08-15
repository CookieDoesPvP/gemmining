# GemMining

GemMining is a commissioned Minecraft Bukkit/Spigot plugin developed in 2018, providing a custom mining progression and reward system.

Players earn **gem points** by mining different block types, progress through levels, receive configurable milestone rewards and can obtain special mining rewards that are processed through designated in-game **sifters** for a chance to receive gems.

## Project history

This plugin was originally developed as a commissioned piece in 2018, before I routinely used Git/GitHub for version control.

This repository was created retrospectively from the surviving source code, so the Git history does not represent the project's original development timeline.

## Features

* **Custom mining progression**
  Mining supported blocks awards different quantities of gem points depending on the block type.

* **Persistent player progression**
  Each player's current gem point balance and mining level are stored against their UUID.

* **Level system**
  Reaching the required number of gem points resets the player's current points and increases their mining level.

* **Permission-based progression requirements**
  The number of gem points required to reach the next level can vary according to player permissions.

* **Configurable milestone rewards**
  Reaching configured levels can execute server console commands, allowing the mining system to integrate with other server functionality such as the economy.

* **Special mining rewards**
  Levelling provides a chance to receive one of several special stone items with different rarities.

* **Interactive sifter system**
  Administrators can designate hopper blocks as sifters. Players can drop special stones into these hoppers to process them for a chance of receiving a gem.

* **Different sifting probabilities**
  Different types of special stone have different chances of successfully producing a gem.

* **Player-linked sifting**
  Special stones are associated with the player who drops them, allowing the resulting reward and feedback to be returned to the correct player when processed by a sifter.

* **Inventory handling**
  Mining rewards are added directly to the player's inventory where possible and dropped into the world if their inventory is full.

* **Silk Touch handling**
  Mining with a Silk Touch pickaxe is excluded from the gem-point processing logic.

* **WorldGuard integration**
  When WorldGuard is available, gem points are only awarded where the player has permission to build.

* **Configurable custom items**
  Special stones and gems are represented by configurable Bukkit `ItemStack` definitions, including display names, lore and enchantments.

## Mining progression

The archived implementation awards gem points for the following blocks:

| Block        | Gem Points |
| ------------ | ---------: |
| Stone        |          1 |
| Coal Ore     |          5 |
| Redstone Ore |         20 |
| Lapis Ore    |         20 |
| Diamond Ore  |         50 |
| Emerald Ore  |         95 |

When the player's gem balance reaches their required threshold, their balance is reset and their mining level increases by one.

## Progression requirements

The archived implementation supports different level requirements through permissions:

| Permission                 | Required Points |
| -------------------------- | --------------: |
| `gemmining.required.10000` |          10,000 |
| `gemmining.required.11000` |          11,000 |
| `gemmining.required.12000` |          12,000 |
| `gemmining.required.13000` |          13,000 |
| `gemmining.required.14000` |          14,000 |
| Default                    |          15,000 |

This allowed progression speed to be varied between different player groups or ranks.

## Sifter system

GemMining includes a custom physical reward-processing mechanic using Minecraft hoppers.

An administrator can look at a hopper and use:

`/gem setsifter`

The hopper's location is then saved to the plugin configuration and recognised as a sifter.

Special stone items can subsequently be dropped into the hopper. The plugin identifies the player associated with the item and performs a probability check based on the type of stone being processed.

In the archived implementation, the sifting success chances are:

| Item              | Gem Chance |
| ----------------- | ---------: |
| Stone             |        30% |
| Large Stone       |        50% |
| Shiny Stone       |        70% |
| Large Shiny Stone |        90% |

A successful sift gives the associated player a custom **Gem** item.

## Commands

### `/gem points`

Displays the player's current gem points, points required for their next level and current mining level.

### `/gem setsifter`

Administrative command used to register the hopper currently being targeted as a sifter.

## Level rewards

The plugin can execute configurable console commands at specific mining levels.

The surviving configuration contains reward hooks for levels:

* 50
* 100
* 150
* 200

This allows progression rewards to interact with other server systems rather than requiring the rewards themselves to be hard-coded.

## Technologies

* Java
* Bukkit / Spigot API
* Bukkit event system
* Bukkit player statistics and inventories
* YAML configuration
* UUID-based player persistence
* WorldGuard API

## Compatibility

> **Note:** This project was developed against Bukkit/Spigot and WorldGuard APIs in use around 2018. It is retained as a historical portfolio project and may require modification to run against current Minecraft server versions and APIs.
