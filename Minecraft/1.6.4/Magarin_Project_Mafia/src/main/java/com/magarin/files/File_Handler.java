package com.magarin.files;

import com.magarin.MafiaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * YML File - Support Class
 *
 * 2021-01-21
 * @author Talk_Magarin
 */
public class File_Handler {

    /*
     * MafiaPlugin 클래스의 특정 기능을 사용하도록 선언합니다.
     * Declare to use specific features of the MafiaPlugin class
     */
    protected static Plugin m_Plugin = MafiaPlugin.getPlugin(MafiaPlugin.class);

    /**
     * 플러그인 폴더에 있는 지정된 파일을 불러옵니다.
     * Recalls the specified files that exist in the plug-in folder.
     *
     * @param in_Filename 파일명 / Filename
     * @return the specified files
     */
    protected static FileConfiguration __load_Yml_File__(String in_Filename) {
        File _File = new File(m_Plugin.getDataFolder(), in_Filename);

        if (_File.exists())
            return YamlConfiguration.loadConfiguration(new File(m_Plugin.getDataFolder(), in_Filename));
        else
            return null;
    }

    /**
     * 플러그인 폴더에 있는 지정된 파일을 저장합니다.
     * Saves the specified file in the plug-in folder.
     *
     * @param in_YmlConfigFile the specified files
     * @param in_Filename 파일명 / Filename
     */
    protected static void __save_Yml_File__(FileConfiguration in_YmlConfigFile, String in_Filename) {
        try {
            in_YmlConfigFile.save(new File(m_Plugin.getDataFolder(), in_Filename));
        } catch (Exception _Exception) {
            System.out.println("[Magarin Plugin] " + in_Filename + " 파일을 저장하는 도중 오류가 발생 되었습니다.");
        }
    }

}
