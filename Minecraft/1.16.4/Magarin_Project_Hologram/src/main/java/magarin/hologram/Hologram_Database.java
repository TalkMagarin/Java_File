package magarin.hologram;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.*;

public class Hologram_Database {

    private static Plugin __Plugin__ = Hologram.getPlugin(Hologram.class);

    private static String __Filename__ = "__Hologram__.db";

    private static String __Connection_Path__ = String.format(
            "jdbc:sqlite:%s\\%s", __Plugin__.getDataFolder().getAbsolutePath(), __Filename__);

    public static void __Database_Create__() {
        if (!__Plugin__.getDataFolder().exists())
            __Plugin__.getDataFolder().mkdirs();

        File __File_Database__ = new File(__Plugin__.getDataFolder(), __Filename__);
        try {
            if (!__File_Database__.exists()) {
                __File_Database__.createNewFile();
                __Database__Tabel_Create__();
            }
        } catch (Exception __Exception__) { }
    }

    private static void __Database__Tabel_Create__() {
        String __Query__ = "CREATE TABLE IF NOT EXISTS holograms (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	player_name TEXT NOT NULL UNIQUE,\n"
                + "	player_uuid TEXT NOT NULL UNIQUE,\n"
                + "	nickname TEXT NOT NULL,\n"
                + "	prefix TEXT\n"
                + ");";

        try (Connection __Connection__ = DriverManager.getConnection(__Connection_Path__);
             Statement __Statement__ = __Connection__.createStatement()) {
            __Statement__.execute(__Query__);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean __Database__is_Player_data__(String __in_Player_UUID__) {
        String __Query__ = String.format("SELECT COUNT(*) AS is_player FROM holograms WHERE player_uuid='%s';", __in_Player_UUID__);

        try (Connection __Connection__ = DriverManager.getConnection(__Connection_Path__);
             Statement __Statement__ = __Connection__.createStatement()) {
            ResultSet __ResultSet__ = __Statement__.executeQuery(__Query__);
            if (__ResultSet__.getInt("is_player") == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void __Database__Add_Player_data__(String __in_Player_name__, String __in_Player_UUID__
            , String __in_Player_prefix__) {
        String __Query__ = "INSERT INTO holograms(player_name, player_uuid, nickname, prefix) VALUES(?, ?, ?, ?);";

        try (Connection __Connection__ = DriverManager.getConnection(__Connection_Path__);
             PreparedStatement __PreparedStatement__ = __Connection__.prepareStatement(__Query__)) {
            __PreparedStatement__.setString(1, __in_Player_name__);
            __PreparedStatement__.setString(2, __in_Player_UUID__);
            __PreparedStatement__.setString(3, __in_Player_name__);
            __PreparedStatement__.setString(4, __in_Player_prefix__);

            __PreparedStatement__.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
