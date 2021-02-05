package com.talk_magarin.setting_durability;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Setting_Durability extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            // Player hand setting durability.
            if (label.equalsIgnoreCase("mphsd")) {
                Player __Player = (Player)sender;

                if (args.length != 0) {
                    if (__Player.getItemInHand().getType() != Material.AIR) {
                        ItemStack __ItemStack = __Player.getItemInHand();
                        if (__ItemStack.getType().getMaxDurability() > 0) {
                            __ItemStack.setDurability(
                                    (short)(__ItemStack.getType().getMaxDurability() - Integer.getInteger(args[0]))
                            );
                        }
                    }
                }
            }
        }
        return false;
    }
}
