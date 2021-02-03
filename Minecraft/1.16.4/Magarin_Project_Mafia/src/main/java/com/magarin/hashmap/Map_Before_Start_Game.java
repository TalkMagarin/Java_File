package com.magarin.hashmap;

import java.util.HashMap;

/**
<pre>
Before Start Game
HashMap_Interface = Support Class
</pre>
 *
 * 2021-02-03
 * @author Talk_Magarin
 */
public class Map_Before_Start_Game implements HashMap_Interface{

    private static Map_Before_Start_Game __Map_Before_Start_Game = null;
    public static Map_Before_Start_Game __Instance__() {
        if (__Map_Before_Start_Game == null)
            __Map_Before_Start_Game = new Map_Before_Start_Game();
        return __Map_Before_Start_Game;
    }

    // 플레이어가 게임에 참여하기 전 플레이어를 보관할 리스트
    // String = Player name, String = Chief of the room player name
    private HashMap<String, String> __Before_Start_Game_Player_List__ = null;

    public Map_Before_Start_Game() {
        this.__Before_Start_Game_Player_List__ = new HashMap<>();
    }

    /**
     *
     * @param __in_Player_name Player name
     * @return boolean
     */
    public boolean __Before__Check_join_player__(String __in_Player_name) {
        if (this.__Before_Start_Game_Player_List__.get(__in_Player_name) == null)
            return false;
        else
            return true;
    }

    public Integer __Before__Player_List_Count() {
        return this.__Before_Start_Game_Player_List__.size();
    }

    /**
     *
     * @param __in_Player_name Player name
     * @param __in_Jobs_name Jobs name
     */
    public void __Before__Join_Player__(String __in_Player_name, String __in_Jobs_name) {
        this.__Before_Start_Game_Player_List__.put(__in_Player_name, __in_Jobs_name);
    }

    /**
     *
     * @param __in_Player_name Player name
     * @return String
     */
    @Override
    public String __Interface_Get_HashMap__(String __in_Player_name) {
        return this.__Before_Start_Game_Player_List__.get(__in_Player_name);
    }

    /**
     *
     * @param __in_Player_name Player name
     * @param __in_Replace_value  Set value
     */
    @Override
    public void __Interface_Set_HashMap__(String __in_Player_name, String __in_Replace_value) {
        this.__Before_Start_Game_Player_List__.replace(__in_Player_name, __in_Replace_value);
    }
}
