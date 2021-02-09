package magarin.hologram;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hologram extends JavaPlugin {

    @Override
    public void onEnable() {
        Hologram_Database.__Database_Create__();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("holo")) {
            if (args.length > 0) {
                Player __Player = (Player) sender;
                String __message = "";

                for (String arg : args) {
                    __message += " " + arg;
                }

                createHologram(__Player.getLocation(), __message);
            }
        }

        return false;
    }

    private void createHologram(Location l, String name) {
        ArmorStand am = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);

        am.setArms(false);
        am.setGravity(false);
        am.setVisible(false);
        am.setCustomName(name);
        am.setCustomNameVisible(true);
    }

}
