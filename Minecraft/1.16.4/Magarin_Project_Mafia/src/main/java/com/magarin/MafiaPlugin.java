package com.magarin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Mafia game Plugin
 *
 * Start Project Day : 2021-01-21
 * @author Talk_Magarin
 */
public final class MafiaPlugin extends JavaPlugin {

    /**
     * 서버가 실행될때 코드가 실행됩니다.
     * The code runs when the server runs.
     */
    @Override
    public void onEnable() {
        on_Load_Event_Syatem();
        on_Create_Yaml_file();
    }

    /**
     * 서버가 종료될때 코드가 실행됩니다.
     * This code runs when the server shuts down.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
<pre>
플러그인의 이벤트 기능을 사용합니다.
Use the event features of the plug-in.
</pre>
     */
    private void on_Load_Event_Syatem() {
        PluginManager _PluginManager = Bukkit.getPluginManager();
        _PluginManager.registerEvents(new MafiaPlugin_Events(), this);
    }

    /**
<pre>
커스텀으로 제작한 Yaml 파일을 생성합니다.
Creates a custom Yaml file.
</pre>
     */
    private void on_Create_Yaml_file() {
        // 플러그인 폴더를 확인합니다.
        // Check plugin directory
        if (!this.getDataFolder().exists())
            // 폴더 생성
            // Create directory
            this.getDataFolder().mkdirs();

        // 파일이 존재하지 않을 경우 파일 생성
        // Create a file if it does not exist.
        saveResource("__Mafia_game_Setting.yml", false);
        saveResource("__Mafia_game_Join_info_.yml", false);
    }

}
