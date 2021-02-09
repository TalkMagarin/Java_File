package magarin.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Hologram_Handler {

    public static void __Hologram_Create__(Player __in_Player__) {
        String __Player_name__ = __in_Player__.getName();
        String __Player_UUID__ = __in_Player__.getUniqueId().toString();
        String __Player_Prefix__ = "[없음]";

        Hologram_Database.__Database__Add_Player_data__(__Player_name__, __Player_UUID__, __Player_Prefix__);
    }

    public static void __Hologram_Show__(Location __in_Player_Location__) {
        ArmorStand __ArmorStand__ = (ArmorStand) __in_Player_Location__.getWorld().spawnEntity(
                __in_Player_Location__, EntityType.ARMOR_STAND
        );

        String __Custom_Name__ = "";

        __ArmorStand__.setArms(false);
        __ArmorStand__.setGravity(false);
        __ArmorStand__.setVisible(false);
        __ArmorStand__.setCustomName(__Custom_Name__);
        __ArmorStand__.setCustomNameVisible(true);
    }

}
