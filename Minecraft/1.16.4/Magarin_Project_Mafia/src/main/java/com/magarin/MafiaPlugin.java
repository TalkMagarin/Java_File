package com.magarin;

import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

        __Instance_Class__();
    }

    /**
     * 서버가 종료될때 코드가 실행됩니다.
     * This code runs when the server shuts down.
     */
    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {}
        else {
            if (label.equalsIgnoreCase("mafia")) {

            }
        }
        return false;
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
        saveResource("__Mafia_game_Jobs_info.yml", false);
        saveResource("__Mafia_game_Command.yml", false);
        saveResource("__Mafia_game_Message.yml", false);
    }

    private Map_Before_Start_Game __Map_Before_Start_Game = null;
    private Map_After_Start_Game __Map_After_Start_Game = null;

    /**
<pre>
Instance 를 생성하는 메소드 입니다.
Create Instance class
</pre>
     */
    private void  __Instance_Class__() {
        __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
        __Map_After_Start_Game = Map_After_Start_Game.__Instance__();
    }

}
